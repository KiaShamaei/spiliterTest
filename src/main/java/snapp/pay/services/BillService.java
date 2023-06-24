package snapp.pay.services;


import snapp.pay.dto.BillRequestDto;


public interface BillService {

    void addBill(BillRequestDto billRequestDto);

    void addBillToGroup(BillRequestDto billRequestDto);

    BillRequestDto getBillDetails(Long billId);

}
