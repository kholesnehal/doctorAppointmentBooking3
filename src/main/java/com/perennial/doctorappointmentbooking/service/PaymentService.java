package com.perennial.doctorappointmentbooking.service;

import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.entity.Payment;
import com.perennial.doctorappointmentbooking.helper.PatientHelper;
import com.perennial.doctorappointmentbooking.helper.PaymentHelper;
import com.perennial.doctorappointmentbooking.repo.PatientRepo;
import com.perennial.doctorappointmentbooking.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepo paymentRepo;

    public void save(MultipartFile file) {
        try {
            List<Payment> payments= PaymentHelper.convertExcelToListOfPPayment(file.getInputStream());
            try {
               payments.forEach(l -> paymentRepo.save(l));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch ( IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Payment> getAllPayment()
    {
        return paymentRepo.findAll();
    }
    public Payment addPayment(Payment payment)
    {
        return paymentRepo.save(payment);
    }

    public Payment updatePayment(Payment payment) {
         Payment update = paymentRepo.findByPaymentId((int) payment.getPaymentId());
        update.setPaymentDate(payment.getPaymentDate());
        update.setPaymentTime(payment.getPaymentTime());
        update.setPaymentMode(payment.getPaymentMode());
        update.setAmmount(payment.getAmmount());
        return paymentRepo.save(update);
    }


    public String deletePayment(int payment_id) {
        Payment payment = paymentRepo.findByPaymentId(payment_id);
        if (!ObjectUtils.isEmpty(payment)) {
            paymentRepo.delete(payment);
            return " Record deleted = " + payment;
        } else
            return "Record is not available and not deleted";
    }
}
