package com.proeza.core.tracking.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proeza.core.persistence.Identifiable;

import static com.proeza.core.util.DataTypeConverter.*;
import static com.proeza.core.util.date.DateUtil.*;

public abstract class Trackeable implements Identifiable {

    private Map<String, List<Movimiento>> mapMovimientos = new HashMap<>(0);

    protected abstract void addMovimiento (Movimiento m);

    protected abstract String getTipoEntidad ();

    protected abstract boolean removeMovimiento (Movimiento m);

    /**
     * La idea de track es que se persista un movimiento para dejar rastro de los cambios realizados entre transacciones
     * sobre la entidad trackeada. <br>
     * Para evitar que se genere mas de un movimiento por transaccion, en caso de que se invoque N veces a este metodo,
     * lo que se hace es llevar cuenta de cuantos movimientos sin id (es decir id con valor cero) hay para cada tipo de
     * movimiento. En caso de encontrar uno previo, se lo elimina.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Movimiento track (String tipoMov, Comparable valorAnte, Comparable valorPoste) {
        if (getId() == null || valorAnte == null || valorAnte == valorPoste || valorAnte.equals(valorPoste) || valorAnte.compareTo(valorPoste) == 0) {
            return null;
        }
        return track(tipoMov, tostring(valorAnte), tostring(valorPoste));
    }

    /**
     * La idea de track es que se persista un movimiento para dejar rastro de los cambios realizados entre transacciones
     * sobre la entidad trackeada. <br>
     * Para evitar que se genere mas de un movimiento por transaccion, en caso de que se invoque N veces a este metodo,
     * lo que se hace es llevar cuenta de cuantos movimientos sin id (es decir id con valor cero) hay para cada tipo de
     * movimiento. En caso de encontrar uno previo, se lo elimina.
     */
    protected Movimiento track (String tipoMov, String valorAnte, String valorPoste) {
        if (getId() == null || valorAnte == null || valorAnte.equals(valorPoste)) {
            return null;
        }
        String valorAnteFinal = getAntiguoValorPrevio(tipoMov, valorAnte);
        if (!equivalentValue(valorPoste, valorAnteFinal)) {
            Movimiento mov = new Movimiento();
            mov.setFecha(toTimestamp(createNow()));
            mov.setIdEntidad(getId());
            mov.setTipoMov(tipoMov);
            mov.setTipoEntidad(getTipoEntidad());
            mov.setValorAnte(("" + valorAnteFinal).trim());
            mov.setValorPost(("" + valorPoste).trim());
            guardarNuevo(mov);
            return mov;
        }
        return null;
    }

    private boolean equivalentValue (String valorPoste, String valorAnteFinal) {
        if (valorPoste == null) {
            if (valorAnteFinal == null) {
                return true;
            }
            return false;
        } else {
            if (valorAnteFinal == null) {
                return false;
            }
            return valorAnteFinal.equals(valorPoste);
        }
    }

    private void guardarNuevo (Movimiento mov) {
        addMovimiento(mov);
        List<Movimiento> movs = this.mapMovimientos.get(mov.getTipoMov());
        if (movs == null) {
            movs = new ArrayList<>();
            this.mapMovimientos.put(mov.getTipoMov(), movs);
        }
        movs.add(mov);
    }

    /**
     * Chequea si existe un movimiento con el tipo recibido, que no tenga id (que tenga id cero). Si lo encuentra
     * entonces se cambia el valor a ser tenido en cuenta como anterior, en vez de tomar el recibido, se toma el valor
     * anterior del movimiento previo.<br>
     * Finalmente se elimina el movimiento encontrado de los movimientos del articulo. Esto sirve para evitar generar
     * varios movimientos dentro de una misma transaccion.
     */
    private String getAntiguoValorPrevio (String tipoMov, String valorAnte) {
        Movimiento mov;
        String valorAnteFinal = valorAnte;
        if ((mov = getUnpersistedPrevious(tipoMov)) != null) {
            valorAnteFinal = mov.getValorAnte();
            this.mapMovimientos.get(tipoMov).remove(mov);
            removeMovimiento(mov);
        }
        return valorAnteFinal;
    }

    /**
     * Devuelve (si es que existe) la unica instancia que debiera existir de un tipo de movimiento para la entidad
     * trackeada que tenga <tt>null</tt> como id (es decir que aun no ha sido persistida)
     */
    private Movimiento getUnpersistedPrevious (String tipoMov) {
        List<Movimiento> movs = this.mapMovimientos.get(tipoMov);
        if (movs != null) {
            for (Movimiento mov : movs) {
                if (mov.getId() == null) {
                    return mov;
                }
            }
        }
        return null;
    }
}