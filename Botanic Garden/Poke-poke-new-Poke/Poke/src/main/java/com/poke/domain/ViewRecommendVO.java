package com.poke.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ViewRecommendVO {
	int viewrecommend_num;
	@NonNull
	int choice1_num;
	@NonNull
	int choice2_num;
	int recommend_num;
}
