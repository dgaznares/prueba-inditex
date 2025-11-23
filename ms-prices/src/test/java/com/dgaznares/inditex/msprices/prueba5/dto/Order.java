package com.dgaznares.inditex.msprices.prueba5.dto;

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
public class Order {
	private String producto;
	private Integer cantidad;
	private Double precio; 

}
