package org.example.crazybarbershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

    private int id;

    private String username;

    private String categoryName;

    private String employeeName;

    private String timeSlot;

    private String status;

}
