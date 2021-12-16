package com.perennial.doctorappointmentbooking.controller;

import com.perennial.doctorappointmentbooking.entity.Payment;
import com.perennial.doctorappointmentbooking.helper.PaymentHelper;
import com.perennial.doctorappointmentbooking.responsemessage.ResponseMessage;
import com.perennial.doctorappointmentbooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<ResponseMessage> uploadExcelFileOfPayment(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (PaymentHelper.checkExcelFormatOfPayment(file)) {
            try {
                paymentService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @PostMapping("/payments")
    @ResponseBody
    public Payment addPayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }


    @PutMapping("/payments/update")
    public Payment updatePayment(Payment payment) {
        return this.paymentService.updatePayment(payment);
    }


    @DeleteMapping("/payments/delete")
    public ResponseEntity<?> deletePayment(@PathVariable int payment_id) {
        try {
            this.paymentService.deletePayment(payment_id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
