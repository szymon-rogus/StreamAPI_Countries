package com.example.streamapi.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class Country {

    private String name;

    private Integer population;

    private Integer area;
}
