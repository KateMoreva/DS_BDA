package ru.spbstu.storage.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(
        indexName = "vacancy_idx"
)
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VacancyIndexDocument {

    @Id
    private long id;
    private String name;
    private String url;
    private String description;
    private AreaIndexDocument area;
    private SalaryIndexDocument salary;
    private AddressIndexDocument address;
    private EmployerIndexDocument employer;
    private Date createdAt;
    private ScheduleIndexDocument schedule;

}
