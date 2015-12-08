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

    private List<Movimiento>            source          = new ArrayList<>();

    private String                      labelDateFormat = "MMMMM yyyy";

    private Integer                     dataLimit       = 10;

    private Comparator<Date>            dateComparator  = new MonthRangeDateComparator();

    private Double                      lowestValue;

    private Date                        lowestValueDate;

    private Double                      highestValue;

    private Date                        highestValueDate;

    @Override
    protected void init(List<Movimiento> source) {
        this.lowestValue = 0D;
        this.highestValue = 0D;
        this.lowestValueDate = null;
        this.highestValueDate = null;
        this.source = source;
        groupByDate();
    }

    @Override
    protected List<String> buildLabels() {
        List<String> labels = new ArrayList<>(this.groupedData.size());
        if (!this.groupedData.keySet().isEmpty()) {
            DateFormat pattern = new SimpleDateFormat(this.labelDateFormat);
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

    @Override
    protected List<?> buildData() {
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

    protected String formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat pattern = new SimpleDateFormat(format);
        return pattern.format(date);
    }

    private <T> List<T> limitData(List<T> labels) {
        return labels.size() > this.dataLimit ? labels.subList(labels.size() - this.dataLimit, labels.size()) : labels;
    }

    private void groupByDate() {
        this.groupedData = new TreeMap<>(this.dateComparator);
        for (Movimiento mov : this.source) {
            if (this.groupedData.get(mov.getFecha()) == null) {
                this.groupedData.put(mov.getFecha(), new ArrayList<Movimiento>());
            }
            updateLowest(mov);
            this.groupedData.get(mov.getFecha()).add(mov);
        }
    }

    private void updateLowest(Movimiento mov) {
        Double movValue = DatatypeConverter.parseDouble(mov.getValorPost());
        if (this.lowestValue == null || this.lowestValue > movValue) {
            this.lowestValue = movValue;
            this.lowestValueDate = mov.getFecha();
        }
        if (this.highestValue == null || this.highestValue < movValue) {
            this.highestValue = movValue;
            this.highestValueDate = mov.getFecha();
        }
    }

    private Double getLastValue(List<Movimiento> movs) {
        return DatatypeConverter.parseDouble(movs.get(movs.size() - 1).getValorPost());
    }

    class MovimientoDateComparator implements Comparator<Movimiento> {
        @Override
        public int compare(Movimiento m1, Movimiento m2) {
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

    public String getDateFormat() {
        return this.labelDateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.labelDateFormat = dateFormat;
    }

    public Comparator<Date> getDateComparator() {
        return this.dateComparator;
    }

    public void setDateComparator(Comparator<Date> dateComparator) {
        this.dateComparator = dateComparator;
    }

    public Integer getDataLimit() {
        return this.dataLimit;
    }

    public void setDataLimit(Integer dataLimit) {
        this.dataLimit = dataLimit;
    }

    public Double getLowestValue() {
        return this.lowestValue;
    }

    public void setLowestValue(Double lowestValue) {
        this.lowestValue = lowestValue;
    }

    public Date getLowestValueDate() {
        return this.lowestValueDate;
    }

    public void setLowestValueDate(Date lowestValueDate) {
        this.lowestValueDate = lowestValueDate;
    }

    public Double getHighestValue() {
        return this.highestValue;
    }

    public void setHighestValue(Double highestValue) {
        this.highestValue = highestValue;
    }

    public Date getHighestValueDate() {
        return this.highestValueDate;
    }

    public void setHighestValueDate(Date highestValueDate) {
        this.highestValueDate = highestValueDate;
    }
}