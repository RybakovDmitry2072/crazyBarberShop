package org.example.crazybarbershop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    private int id;

    private String name;

    private String surname;

    private String phoneNumber;

    private Position position;

    private String email;

    private LocalDate birthday;

    private String gender;

    private String urlImage;

    private List<TimeSlot> timeSlotList;

    private int experience;

    private String about;

    //TODO : сделать класс
    public enum Position{
        MANAGER, BARBER
    }

    public void addTimeSlot(TimeSlot timeSlot) {
        timeSlotList.add(timeSlot);
    }

    public static int getPositionId(Position position) {
        switch (position) {
            case MANAGER:
                return 1;
            case BARBER:
                return 2;
            default:
                throw new IllegalArgumentException("Unknown position: " + position);
        }
    }

    public static Position getIdPosition(int id){
        switch (id){
            case 1:
                return Position.MANAGER;
            case 2:
                return Position.BARBER;
            default:
                throw new IllegalArgumentException("Unknown position: " + id);
        }
    }

}
