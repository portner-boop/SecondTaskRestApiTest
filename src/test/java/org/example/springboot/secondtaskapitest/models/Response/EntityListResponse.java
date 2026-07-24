package org.example.springboot.secondtaskapitest.models.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityListResponse {
    @JsonProperty("entity")
    List<EntityResponse> entities;
}
