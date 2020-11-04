package com.salesianostriana.dam;

import java.util.Map;

public class App {
    
    public static void main(String[] args) {


        RepositorioMeteorologico repositorioMeteorologico = new RepositorioMeteorologico();

        ServicioMeteorologico servicioMeteorologico = new ServicioMeteorologico(repositorioMeteorologico);

        Map<String, Double> mediaMensual = servicioMeteorologico.mediaMensual("SEVILLA", repositorioMeteorologico.getDatos());

        mediaMensual.forEach((k,v) -> System.out.printf("%s, %.2f\n", k, v));

        Map<String, Double> mediaDiaSemana = servicioMeteorologico.mediaDiaSemana("SEVILLA", repositorioMeteorologico.getDatos());

        mediaDiaSemana.forEach((k,v) -> System.out.printf("%s, %.2f\n", k, v));




    }
    
}
