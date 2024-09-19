package org.example.crazybarbershop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emploee {

    private int id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String position;

    private String email;

    private String address;

    private LocalDate birthday;

    private String gender;

    private String urlImage;

    private List<TimeSlot> timeSlotList;

}
