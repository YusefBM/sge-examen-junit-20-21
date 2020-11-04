package com.salesianostriana.dam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Esta clase ofrece algunos cálculos
 * con datos meteorológicos
 */
public class ServicioMeteorologico {

    // Repositorio de datos meteorológicos
    private RepositorioMeteorologico repositorioMeteorologico;

    /**
     * Constructor sin parámetros
     * Orientado a usar los métodos que reciben
     * la lista de datos como argumento
     */
    public ServicioMeteorologico() { }

    /**
     * Constructor con parámetros
     * @param repositorioMeteorologico
     */
    public ServicioMeteorologico(RepositorioMeteorologico repositorioMeteorologico) {
        this.repositorioMeteorologico = repositorioMeteorologico;
    }

    /**
     * Método que devuelve la media mensual de precipitaciones para una población
     * Utiliza los datos del repositorio que se ha pasado como argumento al servicio
     * @param poblacion
     * @return Mapa de tipo clave = Mes, valor = Media de precipitaciones de ese mes
     */
    public Map<String, Double> mediaMensual(String poblacion) {
        return mediaMensual(poblacion, repositorioMeteorologico.getDatos());
    }

    /**
     * Método que devuelve la media mensual de precipitaciones para una población
     * Utiliza los datos del que se le pasan como argumento al método
     * @param poblacion
     * @param datos Listado de datos meteorológicos a utilizar para el cálculo
     * @return Mapa de tipo clave = Mes, valor = Media de precipitaciones de ese mes
     */
    public Map<String, Double> mediaMensual(String poblacion, List<DatoMeteorologico> datos) {

        List<DatoMeteorologico> filtrados =
                filtrarPorPoblacion(poblacion, datos);

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

    /**
     * Método que devuelve la media de precipitaciones para cada día de la semana para una población
     * Utiliza los datos del repositorio que se ha pasado como argumento al servicio
     * @param poblacion
     * @param datos Listado de datos meteorológicos a utilizar para el cálculo
     * @return Mapa de tipo clave = Mes, valor = Media de precipitaciones de ese mes
     */
    public Map<String, Double> mediaDiaSemana(String poblacion) {
        return mediaDiaSemana(poblacion, repositorioMeteorologico.getDatos());
    }

    /**
     * Método que devuelve la media de precipitaciones para cada día de la semana para una población
     * Utiliza los datos del que se le pasan como argumento al método
     * @param poblacion
     * @param datos Listado de datos meteorológicos a utilizar para el cálculo
     * @return Mapa de tipo clave = Mes, valor = Media de precipitaciones de ese mes
     */
    public Map<String, Double> mediaDiaSemana(String poblacion, List<DatoMeteorologico> datos) {
        List<DatoMeteorologico> filtrados =
                filtrarPorPoblacion(poblacion, datos);

        Map<String, List<Double>> aux = new HashMap<>();
        Map<String, Double> result = new HashMap<>();

        for(DatoMeteorologico dato : filtrados) {
            if (!aux.containsKey(fechaADiaSemana(dato.getFecha()))) {
                aux.put(fechaADiaSemana(dato.getFecha()), new ArrayList<>());
                aux.get(fechaADiaSemana(dato.getFecha())).add(dato.getPrecipitacion());
            } else {
                aux.get(fechaADiaSemana(dato.getFecha())).add(dato.getPrecipitacion());
            }
        }

        for (String diaSemana : aux.keySet()) {
            double acumulador = 0;
            for (double precipitacion : aux.get(diaSemana)) {
                acumulador += precipitacion;
            }
            double media = acumulador / aux.get(diaSemana).size();
            result.put(diaSemana, media);
        }


        return result;
    }


    /**
     * Método privado que recibe una fecha y devuelve
     * el mes como cadena de caracteres en mayúsculas
     * @param date
     * @return
     */
    private String fechaAMes(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.FULL, new Locale("es")).toUpperCase();
    }

    /**
     * Método privado que recibe una fecha y devuelve
     * el día de la semana como cadena de caracteres en mayúsculas
     * @param date
     * @return
     */
    private String fechaADiaSemana(LocalDate date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es")).toUpperCase();
    }

    /**
     * Método privado que filtra los datos meteorológicos de una lista, dejando sólo
     * aquellos cuya población coincida con la cadena pasada como argumento
     * @param poblacion Población cuyos datos queremos obtener
     * @param datos Listado de datos meteorologicos
     * @return Sólo los datos de la población pasada como argumento
     */
    private List<DatoMeteorologico> filtrarPorPoblacion(String poblacion, List<DatoMeteorologico> datos) {
        return datos
                        .stream()
                        .filter(d -> d.getCiudad().equalsIgnoreCase(poblacion))
                        .collect(Collectors.toList());
    }

}
