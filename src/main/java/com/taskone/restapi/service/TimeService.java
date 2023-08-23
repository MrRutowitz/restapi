package com.taskone.restapi.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeService {

    @Value("${app.date.time.format:yyyy-MM-dd}")
    private String timeFormat;

    public String currentTime() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(timeFormat);
        return localDate.format(dtf);
    }
}
