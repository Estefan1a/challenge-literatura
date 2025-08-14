package com.aluracursos.challengeliteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(@JsonAlias("results") List<DatosLibros> resultados
){
    @Override
    public List<DatosLibros> resultados() {
        return resultados;
    }

}
