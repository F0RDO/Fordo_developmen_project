package com.poke.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Garden {
	private int plant_seq;
	private String id;
	private int plant_number;
	private String plant_nickname;

}
