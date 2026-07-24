package org.example.springboot.secondtaskapitest.models.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionResponse {

    Long id;

    @Builder.Default
    @JsonProperty("additional_info")
    String additionalInfo = "Дополнительные сведения";

    @Builder.Default
    @JsonProperty("additional_number")
    int additionalNumber = 123 ;
}
