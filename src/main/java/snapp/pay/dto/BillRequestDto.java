package snapp.pay.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import snapp.pay.entities.Contribution;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 *  BillRequestDto data for add Bill from user
 *  @Author Kiarash Shamaei 2023-06-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String billName;
    private Double amount;
    private Long grpId;
    //get user id and paid value per
    private Map<Long, Contribution> userContriPaid;
    private Map<Long, Contribution> userContriOwe;
    public BillRequestDto(String billName, Double amount, Long grpId) {
        this.billName = billName;
        this.amount = amount;
        this.grpId = grpId;
    }

}
