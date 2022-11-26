package ru.spbstu.storage.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployerIndexDocument {

    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String url;
    @Field(type = FieldType.Object)
    private LogoUrlsIndexDocument logoUrls;
    @Field(type = FieldType.Boolean)
    private boolean trusted;

}
