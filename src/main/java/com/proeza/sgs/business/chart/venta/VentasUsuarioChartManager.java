package com.proeza.sgs.business.chart.venta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.proeza.core.util.date.comparator.MonthRangeDateComparator;
import com.proeza.sgs.business.chart.MultiValueChartData;
import com.proeza.sgs.business.chart.MultiDataSetChartManager;
import com.proeza.sgs.business.entity.Venta;

import static java.util.Calendar.*;
import static org.apache.commons.lang.StringUtils.*;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class VentasUsuarioChartManager extends MultiDataSetChartManager<Venta> {

    private Map<Date, List<Venta>> groupedData  = new TreeMap<>(new MonthRangeDateComparator());
    private Map<Date, Double>      dataRecipent = new TreeMap<>(new MonthRangeDateComparator());
    private List<Venta>            source       = new ArrayList<>();

    private Date                   fromDate;
    private Date                   toDate;
    private String                 userAlias;

    public void fromDate (Date from) {
        this.fromDate = from;
    }

    public void toDate (Date to) {
        this.toDate = to;
    }

    public void userAlias (String alias) {
        this.userAlias = alias;
    }

    @Override
    protected void init (List<Venta> source) {
        this.source = source;
        buildRecipent();
        sortAscByDate();
        groupByDate();
    }

    private void buildRecipent () {
        MonthRangeDateComparator comparator = new MonthRangeDateComparator();
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.fromDate);
        while (comparator.compare(cal.getTime(), this.toDate) <= 0) {
            this.dataRecipent.put(cal.getTime(), 0D);
            cal.set(MONTH, cal.get(MONTH) + 1);
        }
    }

    @Override
    protected List<String> buildLabels () {
        List<String> labels = new ArrayList<>(this.groupedData.size());
        DateFormat pattern = new SimpleDateFormat("MMMMM yyyy");
        for (Date date : this.dataRecipent.keySet()) {
            labels.add(capitalize(pattern.format(date)));
        }
        return labels;
    }

    @Override
    protected List<Double> buildData () {
        for (Map.Entry<Date, List<Venta>> data : this.groupedData.entrySet()) {
            List<Venta> ventasMes = data.getValue();
            Double acum = 0D;
            for (Venta venta : ventasMes) {
                acum += venta.getImporte().doubleValue();
            }
            this.dataRecipent.put(data.getKey(), acum);
        }
        return new ArrayList<Double>(this.dataRecipent.values());
    }

    private void sortAscByDate () {
        Collections.sort(this.source, new VentaDateComparator());
    }

    private void groupByDate () {
        for (Venta mov : this.source) {
            if (this.groupedData.get(mov.getFecha()) == null) {
                this.groupedData.put(mov.getFecha(), new ArrayList<Venta>());
            }
            this.groupedData.get(mov.getFecha()).add(mov);
        }
    }

    class VentaDateComparator implements Comparator<Venta> {
        @Override
        public int compare (Venta m1, Venta m2) {
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
    protected MultiValueChartData buildDefinition (List<String> labels, List<?> data) {
        UserSalesChartDefinition definition = new UserSalesChartDefinition(labels, data);
        definition.setUserAlias(this.userAlias);
        return definition;
    }
}