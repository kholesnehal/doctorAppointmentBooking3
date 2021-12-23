package com.perennial.doctorappointmentbooking.controller;

import com.perennial.doctorappointmentbooking.entity.Slot;
import com.perennial.doctorappointmentbooking.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/slots")
public class SlotController {
    @Autowired
    private SlotService slotService;
    @GetMapping("/{doctorId}")
    public Slot getAllSlotByDoctorId(@PathVariable Long doctorId) {
        return (Slot) slotService.getAllByDoctorId(doctorId);
    }

    @GetMapping("/slots")
    public List<Slot> getAllSlot() {
       return slotService.getAll();
    }

    @DeleteMapping("/{slotId}")
    public List<Slot> deleteSlotById(@PathVariable Long slotId) {

       return (List<Slot>) slotService.deleteSlotById(slotId);

    }
    @GetMapping("/{doctorId}/{slotId}")
    public Slot getAppointmentSlotById(@PathVariable Long doctorId,@PathVariable Long slotId)
    {
        return slotService.getAppointmentSlotById(doctorId,slotId);
    }
}
