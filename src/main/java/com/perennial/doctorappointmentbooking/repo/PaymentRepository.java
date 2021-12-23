package com.perennial.doctorappointmentbooking.repo;
import com.perennial.doctorappointmentbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByPaymentId(int payment_id);

}
