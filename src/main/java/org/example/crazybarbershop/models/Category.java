package org.example.crazybarbershop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    private int id;

    private String name;

    private int price;

    private String urlImg;

}
