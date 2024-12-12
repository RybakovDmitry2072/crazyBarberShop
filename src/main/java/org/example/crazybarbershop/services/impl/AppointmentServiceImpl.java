package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.data.AppointmentData;
import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.repository.interfaces.AppointmentRepository;
import org.example.crazybarbershop.services.interfaces.AppointmentService;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;

    @Override
    public void bookAppointment(int categoryId, int emloyeeId, int timeSlotId) {

        Appointment appointment = Appointment.builder()
                .categoryId(categoryId)
                .employeeId(emloyeeId)
                .timeSlotId(timeSlotId)
                .build();

        System.out.println(appointment);

        appointmentRepository.save(appointment);
    }

//    public List<String> getAllHaircutType(){
//        return List.of(Appointment.HaircutType.values())
//                .stream()
//                .map(Appointment.HaircutType::name)
//                .collect(Collectors.toList());
//    }
}