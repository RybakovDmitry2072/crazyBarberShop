package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.Exceptions.CancelAppointmentException;
import org.example.crazybarbershop.Exceptions.DbException;
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
    public Appointment getAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("not found appointment"));

        return appointment;
    }

    @Override
    public void cancelAppointment(int appointmentId) {
       try {
           appointmentRepository.delete(appointmentId);
       } catch (DbException e){
           throw new CancelAppointmentException("Невозможно отвенить запись!", e);
       }
    }

    @Override
    public boolean isAppointmentCompleted(Appointment appointment) {

        if (appointment.getStatus() == null){
            return false;
        }
        return appointment.getStatus().equals("Не выполнен");

    }

    @Override
    public void bookAppointment(int categoryId, int emloyeeId, int timeSlotId, int userId) {

        Appointment appointment = Appointment.builder()
                .categoryId(categoryId)
                .employeeId(emloyeeId)
                .timeSlotId(timeSlotId)
                .userId(userId)
                .build();

        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByUserUd(int id){

        List<Appointment> appointment = appointmentRepository.findAppointmentByUserId(id).orElseThrow(() ->
                new IllegalStateException("not found appointent"));

        return appointment;
    }
}