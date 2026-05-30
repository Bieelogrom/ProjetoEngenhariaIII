package com.fatec.zl.ads.infra.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private DateUtils(){
        throw new UnsupportedOperationException("Esta é uma classe utilitária e não pode ser instanciada");
    }

    public static LocalDate formatarData(String data){
        return LocalDate.parse(data, FORMATTER);
    }
}
