package org.example.springboot.secondtaskapitest.models.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionRequest {
    @Builder.Default
    @JsonProperty("additional_info")
    String additionalInfo = "Дополнительные сведения";
    @Builder.Default
    @JsonProperty("additional_number")
    int additionalNumber = 123 ;

}
