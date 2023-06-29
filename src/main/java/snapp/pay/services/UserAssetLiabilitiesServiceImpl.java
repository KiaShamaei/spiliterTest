package snapp.pay.services;

import snapp.pay.dto.AssetLiabilitiesDto;
import snapp.pay.dto.GroupExpensesDto;
import snapp.pay.dto.UserNetWorthDto;
import snapp.pay.dto.UsersAllGangsDto;
import snapp.pay.entities.BillUserGroup;
import snapp.pay.enums.BillStatus;
import snapp.pay.repositories.BillUserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
/**
 *  UserAssetLiabilitiesService do business of user asset for pay share
 * @Author Kiarash Shamaei 2023-06-25
 */
@Service
public class UserAssetLiabilitiesServiceImpl implements UserAssetLiabilitiesService {

    @Autowired
    private BillUserGroupRepository billUserGroupRepository;


    //this method find net value of each user by id
    @Override
    public UserNetWorthDto getMyNetWorth(Long userId) {

        List<AssetLiabilitiesDto> assets = new ArrayList<>();
        List<AssetLiabilitiesDto> liabilities = new ArrayList<>();
        Double netWorth = 0.0;
        List<BillUserGroup> billUserGroupList = billUserGroupRepository.findByUserId(userId, BillStatus.ACTIVE);
        for (BillUserGroup billUserGroup : billUserGroupList) {

            netWorth = Double.sum(netWorth, billUserGroup.getShare());
            AssetLiabilitiesDto assetLiabilitiesDto = AssetLiabilitiesDto.builder()
                    .billName(billUserGroup.getBill().getName())
                    .share(billUserGroup.getShare())
                    .build();
            if (billUserGroup.getShare() < 0) {
                liabilities.add(assetLiabilitiesDto);
            } else {
                assets.add(assetLiabilitiesDto);
            }
        }

        UserNetWorthDto userNetWorthDto = UserNetWorthDto.builder()
                .assets(assets)
                .liabilities(liabilities)
                .netWorth(netWorth)
                .id(userId)
                .build();
        return userNetWorthDto;
    }

    //this method find the status of payment users in group  user
    @Override
    public UsersAllGangsDto getMyGroupwiseNetWorth(Long userId) {

        List<GroupExpensesDto> expensesDtos = new ArrayList<>();
        List<BillUserGroup> billUserGroupList = billUserGroupRepository.findByUserId(userId, BillStatus.ACTIVE);
        for (BillUserGroup billUserGroup : billUserGroupList) {

            GroupExpensesDto groupExpensesDto = GroupExpensesDto.builder()
                    .groupName(billUserGroup.getBill().getGang().getName())
                    .share(billUserGroup.getShare())
                    .build();
            expensesDtos.add(groupExpensesDto);
        }

        UsersAllGangsDto usersAllGangsDto = UsersAllGangsDto.builder()
                .id(userId)
                .name(billUserGroupList.get(0).getUser().getName())
                .groupExpenses(expensesDtos)
                .build();
        return usersAllGangsDto;
    }
}
