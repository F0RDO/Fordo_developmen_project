package com.poke.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantResult {
	private String plant_nickname;
	private String teperatureResult;
	private int teperatureResultTTS;
	private String humidityResult;
	private int humidityResultTTS;
	private String uvResult;
	private int uvResultTTS;
}
