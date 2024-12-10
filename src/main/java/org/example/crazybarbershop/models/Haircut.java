package org.example.crazybarbershop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Haircut {

    private int id;

    private String name;

    private String description;

    private Category category;

    private String imageUrl;

}
