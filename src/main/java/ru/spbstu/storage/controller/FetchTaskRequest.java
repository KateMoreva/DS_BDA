package java.ru.spbstu.storage.controller;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class FetchTaskRequest implements TaskRequest {

    private String dateFrom;
    private String dateTo;
    private String specialisationId;
    private int limitPerPage;
    private int fromPage;
    private int toPage;

}
