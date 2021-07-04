package com.amorim.vitor.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
	
	private String jwt;
	private String name;

}
