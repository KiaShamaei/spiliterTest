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
public class AssetLiabilitiesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String billName;
    private Double share;

}
