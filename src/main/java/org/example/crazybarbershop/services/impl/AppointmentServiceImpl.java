package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.AppointmentDtoFactory;
import org.example.crazybarbershop.data.AppointmentData;
import org.example.crazybarbershop.dto.AppointmentDto;
import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.repository.interfaces.AppointmentRepository;
import org.example.crazybarbershop.repository.interfaces.UserRepository;
import org.example.crazybarbershop.services.interfaces.AppointmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;


    @Override
    public boolean isAppointmentCompleted(Appointment appointment) {
        System.out.println(appointment);

        if (appointment.getStatus() == null){
            return false;
        }
        return appointment.getStatus().equals("Не выполнен");

    }

    @Override
    public void bookAppointment(int categoryId, int emloyeeId, int timeSlotId) {

        Appointment appointment = Appointment.builder()
                .categoryId(categoryId)
                .employeeId(emloyeeId)
                .timeSlotId(timeSlotId)
                .build();

        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByUserUd(int id){

        List<Appointment> appointment = appointmentRepository.findAppointmentByUserId(id).orElseThrow(() ->
                new IllegalStateException("not found appointent"));

        return appointment;
    }
}