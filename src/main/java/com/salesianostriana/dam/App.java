package com.salesianostriana.dam;

import java.util.Map;

public class App {
    
    public static void main(String[] args) {

        ServicioMeteorologico servicioMeteorologico = new ServicioMeteorologico();

        Map<String, Double> medias = servicioMeteorologico.mediaMensual("SEVILLA");

        medias.forEach((k,v) -> System.out.printf("%s, %.2f\n", k, v));


    }
    
}
