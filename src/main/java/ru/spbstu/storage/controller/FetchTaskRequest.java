package ru.spbstu.storage.controller;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class FetchTaskRequest {

    private String dateFrom;
    private String dateTo;
    private String specialisationId;
    private int limitPerPage;
    private int fromPage;
    private int toPage;

}
