package snapp.pay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = " Name of user must not be blank! ")
    private String name;

    @NotBlank(message = " Email of user must not be blank! ")
    private String email;

    private String contact;

    @NotBlank(message = " Password of user must not be blank! ")
    private String password;

}
