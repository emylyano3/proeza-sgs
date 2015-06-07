package com.proeza.sgs.business.chart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.proeza.core.tracking.entity.Movimiento;

import static javax.xml.bind.DatatypeConverter.*;
import static org.apache.commons.lang.StringUtils.*;

@Component
public class HistorialPrecioLineChartManager extends MultiDataSetChartManager<Movimiento, String, Double> {

    @Override
    protected List<String> buildLabels (List<Movimiento> source) {
        List<String> labels = new ArrayList<String>();
        DateFormat pattern = new SimpleDateFormat("MMMMM yyyy");
        for (Movimiento mov : source) {
            labels.add(capitalize(pattern.format(mov.getFechaMovimiento())));
        }
        return labels;
    }

    @Override
    protected List<Double> buildData (List<Movimiento> source) {
        List<Double> data = new ArrayList<>(source.size() + 1);
        for (Iterator<Movimiento> it = source.iterator(); it.hasNext();) {
            Movimiento mov = it.next();
            data.add(parseDouble(mov.getValorAnte()));
            if (!it.hasNext()) {
                data.add(parseDouble(mov.getValorPost()));
            }
        }
        return data;
    }
}