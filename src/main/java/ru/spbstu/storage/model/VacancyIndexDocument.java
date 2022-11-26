package ru.spbstu.storage.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date createdAt;
    private ScheduleIndexDocument schedule;
    private String specialization_sf;
    private String field_sf;
    private String subdomain_sf;
    private String level_sf;
    private String language_sf;

}
