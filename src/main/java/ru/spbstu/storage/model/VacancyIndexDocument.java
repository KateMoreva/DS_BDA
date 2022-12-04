package java.ru.spbstu.storage.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
    @Field(type = FieldType.Text)
    private String vacancyId;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String url;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Object)
    private AreaIndexDocument area;
    @Field(type = FieldType.Object)
    private SalaryIndexDocument salary;
    @Field(type = FieldType.Object)
    private AddressIndexDocument address;
    @Field(type = FieldType.Object)
    private EmployerIndexDocument employer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @Field(type = FieldType.Date, format = DateFormat.custom,  pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @Field(type = FieldType.Date, format = DateFormat.custom,  pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date publishedAt;
    @Field(type = FieldType.Object)
    private ScheduleIndexDocument schedule;
    @Field(type = FieldType.Text)
    private String specialization_sf;
    @Field(type = FieldType.Text)
    private String field_sf;
    @Field(type = FieldType.Text)
    private String subdomain_sf;
    @Field(type = FieldType.Text)
    private String level_sf;
    @Field(type = FieldType.Text)
    private String language_sf;
    private List<String> tech_sf;
}
