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

    @Builder.Default
    private List<Violation> violations = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Violation {
        private final String fieldName;
        private final String message;
    }
}
