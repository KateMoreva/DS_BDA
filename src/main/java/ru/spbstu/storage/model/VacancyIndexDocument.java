package ru.spbstu.storage.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(
        indexName = "vacancy_idx"
)
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
    private long createdAt;
    private ScheduleIndexDocument schedule;

}
