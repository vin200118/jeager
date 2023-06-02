package com.core.cat;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Headers;
import feign.RequestLine;

@FeignClient
public interface APIClient {
	@RequestLine("GET /api/departments")
	@Headers("Content-Type:application/json")
    String getDepartmentById();
}