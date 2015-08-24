package com.proeza.sgs.business.chart.articulo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.util.date.DateUtil;
import com.proeza.core.util.date.comparator.MonthRangeDateComparator;
import com.proeza.sgs.business.chart.MultiDataSetChartDefinition;
import com.proeza.sgs.business.chart.MultiDataSetChartManager;
import com.proeza.sgs.business.dao.IArticuloDao;

import static java.util.Calendar.*;
import static org.apache.commons.lang.StringUtils.*;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HistorialPrecioChartManager extends MultiDataSetChartManager<Movimiento> {

    private Map<Date, List<Movimiento>> groupedData = new TreeMap<Date, List<Movimiento>>(new MonthRangeDateComparator());
    private List<Movimiento>            source      = new ArrayList<Movimiento>();

    @Autowired
    private IArticuloDao                articuloDao;

    @Override
    protected void init (List<Movimiento> source) {
        this.source = source;
        sortAscByDate();
        groupByDate();
    }

    @Override
    protected List<String> buildLabels () {
        List<String> labels = new ArrayList<>(this.groupedData.size());
        if (!this.groupedData.keySet().isEmpty()) {
            DateFormat pattern = new SimpleDateFormat("MMMMM yyyy");
            Calendar calendar = new GregorianCalendar();
            Date before = this.groupedData.keySet().iterator().next();
            for (Date after : this.groupedData.keySet()) {
                if (after == before) {
                    labels.add(capitalize(pattern.format(after)));
                } else {
                    calendar.setTime(after);
                    int next = calendar.get(MONTH) + calendar.get(YEAR) * 12;
                    calendar.setTime(before);
                    int prev = calendar.get(MONTH) + calendar.get(YEAR) * 12;
                    int curr = prev;
                    while (curr < next) {
                        ++curr;
                        calendar.add(MONTH, 1);
                        labels.add(capitalize(pattern.format(calendar.getTime())));
                    }
                    before = after;
                }
            }
        }
        return labels;
    }

    @Override
    protected List<Double> buildData () {
        List<Double> data = new ArrayList<>(this.groupedData.size());
        if (!this.groupedData.keySet().isEmpty()) {
            Calendar calendar = new GregorianCalendar();
            Date before = this.groupedData.keySet().iterator().next();
            for (Date after : this.groupedData.keySet()) {
                if (after == before) {
                    data.add(getLastValue(this.groupedData.get(after)));
                } else {
                    calendar.setTime(after);
                    int next = calendar.get(MONTH) + calendar.get(YEAR) * 12;
                    calendar.setTime(before);
                    int prev = calendar.get(MONTH) + calendar.get(YEAR) * 12;
                    int curr = prev;
                    Double lastVal = getLastValue(this.groupedData.get(before));
                    while (++curr < next) {
                        calendar.add(MONTH, 1);
                        data.add(lastVal);
                    }
                    calendar.add(MONTH, 1);
                    data.add(getLastValue(this.groupedData.get(calendar.getTime())));
                    before = after;
                }
            }
        }
        return data;
    }

    private void sortAscByDate () {
        Collections.sort(this.source, new MovimientoDateComparator());
    }

    private void groupByDate () {
        for (Movimiento mov : this.source) {
            if (this.groupedData.get(mov.getFecha()) == null) {
                this.groupedData.put(mov.getFecha(), new ArrayList<Movimiento>());
            }
            this.groupedData.get(mov.getFecha()).add(mov);
        }
    }

    private Double getLastValue (List<Movimiento> movs) {
        return DatatypeConverter.parseDouble(movs.get(movs.size() - 1).getValorPost());
    }

    class MovimientoDateComparator implements Comparator<Movimiento> {
        @Override
        public int compare (Movimiento m1, Movimiento m2) {
            if (m1 == null && m2 == null) {
                return 0;
            }
            if (m1 == null) {
                return -1;
            }
            if (m2 == null) {
                return 1;
            }
            return m1.getFecha().compareTo(m2.getFecha());
        }
    }

    @Override
    protected MultiDataSetChartDefinition buildDefinition (List<String> labels, List<?> data) {
        HistorialPrecioChartDefinition definition = new HistorialPrecioChartDefinition(labels, data);
        definition.setUpdates(getUpdates());
        definition.setStock(getStock());
        definition.setLastPriceUpdate(getLastPriceUpdate());
        definition.setLastStockUpdate(getLastStockUpdate());
        return definition;
    }

    private Integer getStock () {
        return 5;
    }

    private Date getLastStockUpdate () {
        return DateUtil.createNow();
    }

    private Date getLastPriceUpdate () {
        return this.source.get(this.source.size() - 1).getFecha();
    }

    private Integer getUpdates () {
        return this.source.size();
    }
}