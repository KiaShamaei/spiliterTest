package snapp.pay.services;

import lombok.extern.slf4j.Slf4j;
import snapp.pay.dto.BillRequestDto;
import snapp.pay.entities.*;
import snapp.pay.enums.BillStatus;
import snapp.pay.exceptions.BillNotFoundException;
import snapp.pay.exceptions.GroupNotFoundException;
import snapp.pay.repositories.BillRepository;
import snapp.pay.repositories.BillUserGroupRepository;
import snapp.pay.repositories.GroupRepository;
import snapp.pay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 *  BillService do business of Bill
 * @Author Kiarash Shamaei 2023-06-25
 */

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private BillUserGroupRepository billUserGroupRepository;


    @Override
    public void addBillToGroup(BillRequestDto billRequestDto) {

        Group grp = null;
        if (billRequestDto.getGrpId() != null) {
            grp = groupRepository.findById(billRequestDto.getGrpId()).get();
            if (grp == null) {
                throw new GroupNotFoundException("Group with id " + billRequestDto.getGrpId() + " does not exists");
            }
        }
        List<BillUserGroup> billUserGroupList = calculateShares(billRequestDto, grp);
        Bill bill = Bill.builder()
                .name(billRequestDto.getBillName())
                .billAmount(billRequestDto.getAmount())
                .status(BillStatus.ACTIVE)
                .gang(grp)
                .build();
        billRepository.save(bill);
        for (BillUserGroup billUserGroup : billUserGroupList) {
            billUserGroup.setBill(bill);
        }
        billUserGroupRepository.saveAll(billUserGroupList);
    }

    @Override
    public void addBillToGroup(Long billId, Long grpId) {
        var bill = billRepository.findById(billId).orElseThrow(
                ()-> new BillNotFoundException("Bill with id " + billId + " does not exists"));
        var gang = groupRepository.findById(grpId).orElseThrow(
                ()-> new GroupNotFoundException("Group with id " + grpId + " does not exists")
        );
        bill.setGang(gang);
        billRepository.save(bill);
    }

    @Override
    @Transactional
    public void balanceBillWithId(Long billId , Long userId) {
        var billUser = billUserGroupRepository
                .findByUserBill(userId,billId,BillStatus.ACTIVE)
                .orElseThrow(()->new BillNotFoundException("NO bill match with this id and user with state Active"));
        billUser.getBill().setStatus(BillStatus.SETTLED);
        billUserGroupRepository.save(billUser);
    }


    @Override
    public void addBill(BillRequestDto billRequestDto) {

        List<BillUserGroup> billUserGroupList = this.calculateShares(billRequestDto, null);
        Bill bill = Bill.builder()
                .name(billRequestDto.getBillName())
                .billAmount(billRequestDto.getAmount())
                .status(BillStatus.ACTIVE)
                .build();
        billRepository.save(bill);
        for (BillUserGroup billUserGroup : billUserGroupList) {
            billUserGroup.setBill(bill);
        }
        billUserGroupRepository.saveAll(billUserGroupList);
    }

    private List<BillUserGroup> calculateShares(BillRequestDto billRequestDto, Group grp) {

        List<BillUserGroup> billUserGroups ;
        if (grp != null) {
            billUserGroups = splitGroupUserShares(billRequestDto, grp);
        } else {
            billUserGroups = splitUserShares(billRequestDto);
        }
        return billUserGroups;
    }

    private List<BillUserGroup> splitGroupUserShares(BillRequestDto billRequestDto, Group grp) {

        List<User> userList = getUsersInBill(billRequestDto);//prepareGroupUserList(grp); <Use this afterwards. Needs fixing>
        List<BillUserGroup> billUserGroups = calculateUserShares(billRequestDto, userList);
        return billUserGroups;
    }

    private List<User> prepareGroupUserList(Group grp) {

        List<User> idList = new ArrayList<>();
        for (UserGroup usrGrp : grp.getUserGroups()) {
            idList.add(usrGrp.getUser());
        }
        return idList;
    }

    private List<BillUserGroup> splitUserShares(BillRequestDto billRequestDto) {

        List<User> userList = getUsersInBill(billRequestDto);
        List<BillUserGroup> billUserGroups = calculateUserShares(billRequestDto, userList);
        return billUserGroups;
    }

    private List<User> getUsersInBill(BillRequestDto billRequestDto) {

        Set<Long> oweIds = billRequestDto.getUserContriOwe().keySet();
        Set<Long> paidIds = billRequestDto.getUserContriPaid().keySet();
        Set<Long> ids = new HashSet<>();
        ids.addAll(oweIds);
        ids.addAll(paidIds);
        List<Long> idList = new ArrayList<>();
        idList.addAll(ids);
        List<User> userList = userRepository.findAllById(idList);
        return userList;
    }

    private List<BillUserGroup> calculateUserShares(BillRequestDto billRequestDto, List<User> users) {

        Double paidAmt = -1.0, owedAmt = -1.0, paidPer = 0.0, owedPer = 0.0;
        List<BillUserGroup> billUserGroups = new ArrayList<>();
        for (User user : users) {
            // check if data is supplied for correct user share calculations
            Map<Long, Contribution> userContriPaid = billRequestDto.getUserContriPaid();
            Map<Long, Contribution> userContriOwe = billRequestDto.getUserContriOwe();
            Contribution userPaidContribution = userContriPaid.get(user.getId());
            Contribution userOwedContribution = userContriOwe.get(user.getId());
            if ((userPaidContribution != null && userPaidContribution.getShareAmount() == null && userPaidContribution.getSharePercentage() == null)
                    || (userOwedContribution != null && userOwedContribution.getShareAmount() == null && userOwedContribution.getSharePercentage() == null)) {
                log.info("No share data supplied");
                System.exit(0);
            } else {
                if (userPaidContribution != null) {
                    if (userPaidContribution.getShareAmount() != null) {
                        paidAmt = userPaidContribution.getShareAmount();
                    } else {
                        paidPer = userPaidContribution.getSharePercentage();
                    }
                }
                if (userOwedContribution != null) {
                    if (userOwedContribution.getShareAmount() != null) {
                        owedAmt = userOwedContribution.getShareAmount();
                    } else {
                        owedPer = userOwedContribution.getSharePercentage();
                    }
                }
            }
            Double userPaid = paidAmt != -1.0 ? paidAmt : paidPer * 0.01 * billRequestDto.getAmount();
            Double userOwe = owedAmt != -1.0 ? owedAmt : owedPer * 0.01 * billRequestDto.getAmount();
            BillUserGroup billUserGroup = new BillUserGroup(user, userPaid - userOwe);
            billUserGroups.add(billUserGroup);
            paidAmt = -1.0;
            owedAmt = -1.0;
        }
        return billUserGroups;
    }

    public BillRequestDto getBillDetails(Long billId) {

        Bill bill = billRepository.findById(billId).get();
        Long grpId = bill.getGang() != null ? bill.getGang().getId() : null;
        BillRequestDto billRequestDto = new BillRequestDto(bill.getName(), bill.getBillAmount(), grpId);
        List<BillUserGroup> billUserGroups = billUserGroupRepository.findByBillId(billId);
        Map<Long, Contribution> owe = new HashMap<>();
        Map<Long, Contribution> paid = new HashMap<>();
        billRequestDto.setUserContriOwe(owe);
        billRequestDto.setUserContriPaid(paid);
        for (BillUserGroup billUserGroup : billUserGroups) {
            Contribution contribution = new Contribution(billUserGroup.getShare(), null);
            if (billUserGroup.getShare() > 0) {
                paid.put(billUserGroup.getUser().getId(), contribution);
            } else {
                owe.put(billUserGroup.getUser().getId(), contribution);
            }
        }
        return billRequestDto;
    }
}
