package com.salesianostriana.dam;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoMeteorologico {

    private LocalDate fecha;
    private double precipitacion;
    private String ciudad;


}
