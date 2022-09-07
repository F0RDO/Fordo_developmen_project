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
public class CommunityVO {
	@NonNull
	int post_number;
	String post_name;
	@NonNull
	String post_contents;
	int view_count;
	String id;
}
