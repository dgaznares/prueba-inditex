package com.dgaznares.inditex.msprices.prueba1.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Cantidad {
	private String cantidad;
    private String nombre;
}
