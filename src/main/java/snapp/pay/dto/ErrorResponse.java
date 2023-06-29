package snapp.pay.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private int errorCode;
    private Map<String, List<String>> errors ;
    private String message ;
    private LocalDateTime time;
}
