package ru.spbstu.storage.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class AddressIndexDocument {

    @Field(type = FieldType.Text)
    private String city;
    @Field(type = FieldType.Text)
    private String street;
    @Field(type = FieldType.Text)
    private String building;
    @Field(type = FieldType.Object)
    private StationIndexDocument station;
    @GeoPointField
    private GeoPoint geo;
    @Field(type = FieldType.Text)
    private String raw;

}
