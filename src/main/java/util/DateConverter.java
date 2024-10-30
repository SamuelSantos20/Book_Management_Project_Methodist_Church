package util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Classe utilitária para conversão de tipos de data.
 */
public class DateConverter {

    /**
     * Converte um LocalDate em um Date.
     *
     * @param localDate o LocalDate a ser convertido.
     * @return o objeto Date correspondente ao LocalDate fornecido, ou null se o LocalDate for nulo.
     */
    public static Date convertLocalDateToDate(LocalDate localDate) {
        if (localDate == null) {
            return null; // Retorna null se o LocalDate for nulo
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
