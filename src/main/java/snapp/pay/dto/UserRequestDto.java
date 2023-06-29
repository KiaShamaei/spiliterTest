package snapp.pay.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = " Name of user must not be blank! ")
    @NotNull(message = " Name of user must not be null! ")
    private String name;

    @NotBlank(message = " Email of user must not be blank! ")
    @Email
    private String email;

    private String contact;

    @NotBlank(message = " Password of user must not be blank! ")
    private String password;

}
