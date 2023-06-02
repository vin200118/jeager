package com.core.cat;

import org.springframework.stereotype.Component;

@Component
public class BlackCat implements Cat {

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return "black";
	}

}
