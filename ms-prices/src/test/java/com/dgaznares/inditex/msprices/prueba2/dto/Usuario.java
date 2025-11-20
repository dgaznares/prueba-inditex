package com.dgaznares.inditex.msprices.prueba2.dto;

import java.math.BigDecimal;
import java.util.Optional;

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
public class Usuario {
	Optional<BigDecimal>  saldo;
    
}
