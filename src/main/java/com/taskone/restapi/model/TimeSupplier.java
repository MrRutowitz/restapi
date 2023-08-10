package com.taskone.restapi.model;

@FunctionalInterface
public interface TimeSupplier<String> {
    String getTime();
}
