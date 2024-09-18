package org.example.crazybarbershop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private List<TimeSlot> timeSlotList;


}
