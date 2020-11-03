package com.salesianostriana.dam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.stream.Collectors;

public class ServicioMeteorologico {

    private RepositorioMeteorologico repositorioMeteorologico;

    public ServicioMeteorologico() {
        repositorioMeteorologico = new RepositorioMeteorologico();
    }

    public Map<String, Double> mediaMensual(String poblacion) {

        List<DatoMeteorologico> filtrados =
                repositorioMeteorologico.getDatos()
                    .stream()
                        .filter(d -> d.getCiudad().equalsIgnoreCase(poblacion))
                        .collect(Collectors.toList());

        Map<String, List<Double>> aux = new HashMap<>();
        Map<String, Double> result = new HashMap<>();

        for(DatoMeteorologico dato : filtrados) {
            if (!aux.containsKey(fechaAMes(dato.getFecha()))) {
                aux.put(fechaAMes(dato.getFecha()), new ArrayList<>());
                aux.get(fechaAMes(dato.getFecha())).add(dato.getPrecipitacion());
            } else {
                aux.get(fechaAMes(dato.getFecha())).add(dato.getPrecipitacion());
            }
        }

        for (String mes : aux.keySet()) {
            double acumulador = 0;
            for (double precipitacion : aux.get(mes)) {
                acumulador += precipitacion;
            }
            double media = acumulador / aux.get(mes).size();
            result.put(mes, media);
        }

        return result;
    }

    public Map<String, Double> mediaDiaSemana(String poblacion) {
        return null;
    }


    private String fechaAMes(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.FULL, new Locale("es"));
    }

}
