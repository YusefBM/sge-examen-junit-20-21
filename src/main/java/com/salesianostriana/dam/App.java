package com.salesianostriana.dam;

import java.util.Map;

/**
 * Código de ejemplo del repositorio y servicio
 */
public class App {
    
    public static void main(String[] args) {


        // Instanciación del repositorio - Se realiza la lectura del fichero
        RepositorioMeteorologico repositorioMeteorologico = new RepositorioMeteorologico();

        // Instanciación del servicio
        ServicioMeteorologico servicioMeteorologico = new ServicioMeteorologico();

        // Ejemplo de uso del método mediaMensual para Sevilla
        Map<String, Double> mediaMensual = servicioMeteorologico.mediaMensual("SEVILLA", repositorioMeteorologico.getDatos());
        mediaMensual.forEach((k,v) -> System.out.printf("%s, %.2f\n", k, v));

        // Ejemplo de uso del método mediaDiaSemana para Sevilla
        Map<String, Double> mediaDiaSemana = servicioMeteorologico.mediaDiaSemana("SEVILLA", repositorioMeteorologico.getDatos());
        mediaDiaSemana.forEach((k,v) -> System.out.printf("%s, %.2f\n", k, v));

    }
    
}
