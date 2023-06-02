package com.core.cat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
public class CatController {
	private Cat whiteCat;
	private Cat blackCat;
	private JaegerTracer tracer;
	private APIClient apiClient;
	
	public CatController(Cat whiteCat, Cat blackCat,APIClient apiClient, JaegerTracer tracer) {
		this.whiteCat = whiteCat;
		this.blackCat = blackCat;
		this.tracer = tracer;
		this.apiClient = apiClient;
	}
	@GetMapping("/api/dept/{departmentId}")
	public String getDepartmentName(@PathVariable(required = true) int departmentId) {
		Tracer tracer = new JaegerTracer.Builder("myServiceName").build();
		Span span = tracer.buildSpan("getDepartment from pug ").start();
		//JaegerSpan span = tracer.buildSpan("getDepartment from pug ").start();
	//	span.context().getTraceId();
		try {
			TraceData traceData = new TraceData();
		//	traceData.setTraceId(span.context().getTraceId());
		return apiClient.getDepartmentById();
		}finally {
			
			span.finish();
			
		}
	}
	@GetMapping("/api/getCat")
	public String getCatColor(@RequestParam(required = false) String catName) {
		JaegerSpan span = tracer.buildSpan("getCat").start();
		
		try {
		if("white".equals(catName)) {
			return whiteCat.getColor();
		}else if ("black".equals(catName)) {
			return blackCat.getColor();
		}else {
			return "fake Color";
		}
		}finally {
			span.finish();
		}
		
	}
	
	@GetMapping(value = {"/api/getFromPath/{catName}"})
	public String getCatColorWithPathVer(@PathVariable(required = false) String catName) {
		if("white".equals(catName)) {
			return whiteCat.getColor();
		}else if ("black".equals(catName)) {
			return blackCat.getColor();
		}else {
			return "fake Color";
		}
	}
	
	@GetMapping(value = {"/api/make/{catName}", "/api/make"})
	public String getMap(@PathVariable(required = true) String catName) {
		if("white".equals(catName)) {
			return whiteCat.getColor();
		}else if ("black".equals(catName)) {
			return blackCat.getColor();
		}else {
			return "fake Color";
		}
	}
}
