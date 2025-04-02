package org.example.springboot.secondtaskapitest.models.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityResponse {
    Long id;
    AdditionResponse addition;
    @Builder.Default
    @JsonProperty("important_numbers")
    List<Integer> importantNumbers = new ArrayList<>(Arrays.asList(42, 87, 15));
    @Builder.Default
    String title = "Заголовок сущности";
    @Builder.Default
    boolean verified = true;
}
