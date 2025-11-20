
package com.dgaznares.inditex.msprices.prueba1.dto;

import java.math.BigDecimal;

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
public class TransaccionDto {
	String moneda;
	BigDecimal monto;
}
