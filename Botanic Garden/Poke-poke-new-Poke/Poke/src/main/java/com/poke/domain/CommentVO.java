package com.poke.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class CommentVO {
	private int comment_num;
	@NonNull
	private  int post_number;
	@NonNull
	private  String comment_contents;
	@NonNull
	private String id;
	private String rage_date;
}
