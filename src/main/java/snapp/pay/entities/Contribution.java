package snapp.pay.entities;

import lombok.*;


/**
 * Contribution Keep share part
 * @Author Kiarash Shamaei 2023-06-25
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contribution  extends SuperEntity {

    private Double shareAmount;
    private Double sharePercentage;

}
