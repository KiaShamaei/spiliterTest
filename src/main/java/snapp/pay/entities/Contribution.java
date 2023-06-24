package snapp.pay.entities;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contribution  extends SuperEntity {

    private Double shareAmount;
    private Double sharePercentage;

}
