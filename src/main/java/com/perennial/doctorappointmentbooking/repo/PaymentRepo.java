package com.perennial.doctorappointmentbooking.repo;
import com.perennial.doctorappointmentbooking.entity.Patient;
import com.perennial.doctorappointmentbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
    Payment findByPaymentId(int payment_id);

}
