package com.sfaci.concesionario.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Métodos de utilidad
 *
 * @author Santiago Faci
 * @version curso 2016-2017
 */
public class Util {

    public static Date parseFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        return sdf.parse(fecha);
    }

    public static String formatFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        return sdf.format(fecha);
    }
}
