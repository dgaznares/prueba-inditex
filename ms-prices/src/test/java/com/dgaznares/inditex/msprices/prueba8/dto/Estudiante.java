package com.dgaznares.inditex.msprices.prueba8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Estudiante {
    String nombre;
    String carrera; // Clave de Agrupación
    int calificacion; // Dato a Procesar
}