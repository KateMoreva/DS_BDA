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
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StationIndexDocument {

    @Id
    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Object)
    private MetroLineIndexDocument line;
    @Field(type = FieldType.Integer)
    private Integer order;
    @GeoPointField
    private GeoPoint geo;

}
