package snapp.pay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private List<String> userEmails;

}
