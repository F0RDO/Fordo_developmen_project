package com.poke.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
	private String plant_nickname;
	private int temperature;
	private int humidity;
	private float uv;
	private int cycle;
}
