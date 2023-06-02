package com.core.cat;

import org.springframework.stereotype.Component;

@Component
public class WhiteCat implements Cat {

	@Override
	public String getColor() {
		return "White";
	}

}
