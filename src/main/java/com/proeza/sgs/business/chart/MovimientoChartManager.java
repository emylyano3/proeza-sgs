package com.proeza.sgs.business.chart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.DatatypeConverter;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.util.date.comparator.MonthRangeDateComparator;

import static java.util.Calendar.*;
import static org.apache.commons.lang.StringUtils.*;

public abstract class MovimientoChartManager extends MultiDataSetChartManager<Movimiento> {

    private Map<Date, List<Movimiento>> groupedData;

    private List<Movimiento>            source         = new ArrayList<>();

    private String                      dateFormat     = "MMMMM yyyy";

    private Integer                     dataLimit      = 10;

    private Comparator<Date>            dateComparator = new MonthRangeDateComparator();

    @Override
    protected void init (List<Movimiento> source) {
        this.source = source;
        groupByDate();
    }

    @Override
    protected List<String> buildLabels () {
        List<String> labels = new ArrayList<>(this.groupedData.size());
        if (!this.groupedData.keySet().isEmpty()) {
            DateFormat pattern = new SimpleDateFormat(this.dateFormat);
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
        return limitData(labels);
    }

    private <T> List<T> limitData (List<T> labels) {
        return labels.size() > this.dataLimit ? labels.subList(labels.size() - this.dataLimit, labels.size()) : labels;
    }

    @Override
    protected List<?> buildData () {
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
        return limitData(data);
    }

    private void groupByDate () {
        this.groupedData = new TreeMap<>(this.dateComparator);
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

    public String getDateFormat () {
        return this.dateFormat;
    }

    public void setDateFormat (String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Comparator<Date> getDateComparator () {
        return this.dateComparator;
    }

    public void setDateComparator (Comparator<Date> dateComparator) {
        this.dateComparator = dateComparator;
    }

    public Integer getDataLimit () {
        return this.dataLimit;
    }

    public void setDataLimit (Integer dataLimit) {
        this.dataLimit = dataLimit;
    }
}