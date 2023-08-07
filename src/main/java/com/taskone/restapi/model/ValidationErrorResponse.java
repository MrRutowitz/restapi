package com.taskone.restapi.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Violation {
        private String fieldName;
        private String message;
    }
}
