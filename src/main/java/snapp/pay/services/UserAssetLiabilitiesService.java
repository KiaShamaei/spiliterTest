package snapp.pay.services;

import snapp.pay.dto.UserNetWorthDto;
import snapp.pay.dto.UsersAllGangsDto;
import org.springframework.stereotype.Component;

/**
 *
 * @Author Kiarash Shamaei 2023-06-25
 */

@Component
public interface UserAssetLiabilitiesService {

    UserNetWorthDto getMyNetWorth(Long userId);

    UsersAllGangsDto getMyGroupwiseNetWorth(Long userId);

}
