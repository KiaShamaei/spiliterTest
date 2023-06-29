package snapp.pay.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import snapp.pay.dto.BillRequestDto;
import snapp.pay.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BillController
 * @Author Kiarash Shamaei 2023-06-25
 */
@RestController
@RequestMapping(value = "/api/v1")
@Tag(name = "Bill Api")
public class BillController {

    @Autowired
    private BillService billServiceImpl;

    @Operation(summary = "this method add New Bill with Group " ,description = "ok status")
    @PostMapping(value = "/bill/group")
    public ResponseEntity<?> addNewBillWithGroup(@RequestBody BillRequestDto billRequestDto) {

        billServiceImpl.addBillToGroup(billRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "this method add Bill to Group  with id" ,description = "ok status")
    @GetMapping(value = "/bill/group-id/{grpId}/bill-id/{billId}")
    public ResponseEntity<?> addBillToGroup(@PathVariable(value = "billId") Long billId ,
                                                 @PathVariable(value = "grpId") Long grpId) {

        billServiceImpl.addBillToGroup(billId,grpId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/bill")
    @Operation(summary = "this method make a new bill" ,description = "ok status")
    public ResponseEntity<?> addNewBill(@RequestBody BillRequestDto billRequestDto) {

        billServiceImpl.addBill(billRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/bill/{billId}")
    @Operation(summary = "this methood return a bill" ,description = "ok billResponse")
    public ResponseEntity<BillRequestDto> getBill(@PathVariable(value = "billId") Long billId) {

        BillRequestDto billResponse = billServiceImpl.getBillDetails(billId);
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }
    @GetMapping(value = "/bill/{billId}/paid/user/{userId}")
    @Operation(summary = "paid a bill with balance " ,description = "ok")
    public ResponseEntity<BillRequestDto> paid(@PathVariable(value = "billId") Long billId,
                                               @PathVariable(value = "userId") Long userId) {

        billServiceImpl.balanceBillWithId(billId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
