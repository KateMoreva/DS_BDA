package ru.spbstu.storage.controller;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class FetchTaskRequest {

    private final String dateFrom;
    private final String dateTo;
    private final String specialisationId;
    private final int limitPerPage;
    private final int fromPage;
    private final int toPage;

}
