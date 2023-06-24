package snapp.pay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String contact;

}
