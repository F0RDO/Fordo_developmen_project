package com.poke.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Plant_care {
	private int plantcare_seq;
	private String plant_nickname;
	private String last_date;
	private String plant_diary;
	private int plant_height;
	
}
