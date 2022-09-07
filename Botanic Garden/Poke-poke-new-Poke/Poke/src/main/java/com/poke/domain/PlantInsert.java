package com.poke.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantInsert {

	private String id ;
	private String plant_name;
	private String plant_nickname;
	private String plant_lastdate;
	
}