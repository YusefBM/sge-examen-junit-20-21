package com.salesianostriana.dam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta clase es el repositorio de datos meterológicos
 */
public class RepositorioMeteorologico {

    private final String DELIMITADOR = ";";

    List<DatoMeteorologico> data = new ArrayList<>();

    public RepositorioMeteorologico() {

        try {
            data = Files.lines(Paths.get("datos-sevilla.csv"))
                            .map(linea -> {
                                String[] partes = linea.split(DELIMITADOR);
                                return DatoMeteorologico.builder()
                                        .fecha(LocalDate.parse(partes[0], DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                                        .precipitacion(Double.parseDouble(partes[1]))
                                        .ciudad(partes[2])
                                        .build();
                            })
                            .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error al procesar el fichero CSV");
            data = null;
        }

    }


    public List<DatoMeteorologico> getDatos() {
        return Collections.unmodifiableList(data);
    }




}
