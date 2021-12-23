package com.perennial.doctorappointmentbooking.service;
import com.perennial.doctorappointmentbooking.entity.Slot;
import com.perennial.doctorappointmentbooking.repo.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private AppointmentService appointmentService;

    public List<Slot> getAll() {
        return slotRepository.findAll();
    }
    public Slot getAppointmentSlotById(Long doctorId, Long slotId) {
        List<Slot> slots = slotRepository.getAllByDoctorId(doctorId);
        for (Slot currentSlot : slots) {
            if (currentSlot.getSlotId()==slotId) {
                return currentSlot;
            }
        }
        return null;
    }
    public List<Slot> getAllByDoctorId(Long doctorId) {
        return slotRepository.getAllByDoctorId(doctorId);
    }

    public Slot deleteSlotById(Long slotId) {
        Slot deletedSlot;

        if (slotRepository.findById(slotId).isPresent()) {
            deletedSlot = slotRepository.findById(slotId).get();
        } else {
            return null;
        }

        slotRepository.delete(deletedSlot);
        return deletedSlot;
    }

}
