package com.internet.banking.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class DateUtil {

    public static LocalDate toLocalDate(final String date) {
        return LocalDate.parse(date);
    }
}
