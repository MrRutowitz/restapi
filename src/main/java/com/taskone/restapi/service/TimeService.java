package com.taskone.restapi.service;

import com.taskone.restapi.model.TimeSupplier;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeService {

    //        @Value("${date.time.format:yyyy-MM-dd}")
    //        public String timeFormat;

    public TimeSupplier<String> currentTime() {
        return () -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            return dtf.format(localDate);
        };
    }
}
