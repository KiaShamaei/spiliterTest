package snapp.pay.services;


import snapp.pay.dto.BillRequestDto;

/**
 *
 * @Author Kiarash Shamaei 2023-06-25
 */

public interface BillService {

    void addBill(BillRequestDto billRequestDto);

    void addBillToGroup(BillRequestDto billRequestDto);

    BillRequestDto getBillDetails(Long billId);

}
