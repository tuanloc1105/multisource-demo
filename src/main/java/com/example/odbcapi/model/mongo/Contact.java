package com.example.odbcapi.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "contact")
public class Contact {

    @Id
    private UUID id;

    @Field(name = "name")
    private String name;

    @Field(name = "age")
    private Integer age;

    public boolean isNew() {
        return this.getId() == null;
    }

}
