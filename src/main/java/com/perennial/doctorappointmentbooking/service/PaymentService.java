package com.perennial.doctorappointmentbooking.service;

import com.perennial.doctorappointmentbooking.entity.Payment;
import com.perennial.doctorappointmentbooking.helper.PaymentHelper;
import com.perennial.doctorappointmentbooking.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public void save(MultipartFile file) {
        try {
            List<Payment> payments = PaymentHelper.convertExcelToListOfPPayment(file.getInputStream());
            try {
                payments.forEach(l -> paymentRepository.save(l));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        Payment update = paymentRepository.findByPaymentId((int) payment.getPaymentId());
        update.setPaymentDate(payment.getPaymentDate());
        update.setPaymentTime(payment.getPaymentTime());
        update.setPaymentMode(payment.getPaymentMode());
        update.setAmmount(payment.getAmmount());
        return paymentRepository.save(update);
    }


    public String deletePayment(int payment_id) {
        Payment payment = paymentRepository.findByPaymentId(payment_id);
        if (!ObjectUtils.isEmpty(payment)) {
            paymentRepository.delete(payment);
            return " Record deleted = " + payment;
        } else
            return "Record is not available and not deleted";
    }
}
