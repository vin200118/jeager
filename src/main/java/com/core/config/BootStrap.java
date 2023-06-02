package com.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.core.cat.APIClient;
import com.core.cat.BlackCat;
import com.core.cat.Cat;
import com.core.cat.WhiteCat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.jaegertracing.internal.JaegerTracer;

@Configuration
public class BootStrap {
	
//	@Bean
//	public Cat whiteCat() {
//		return new WhiteCat();
//	}
//	
//	@Bean
//	public Cat blackCat() {
//		return new BlackCat();
//	}
	@Bean
	public static JaegerTracer getTracer() {
	    io.jaegertracing.Configuration.SamplerConfiguration samplerConfig = io.jaegertracing.Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
	    io.jaegertracing.Configuration.ReporterConfiguration reporterConfig = io.jaegertracing.Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
	    io.jaegertracing.Configuration config = new io.jaegertracing.Configuration("pug").withSampler(samplerConfig).withReporter(reporterConfig);
	    return config.getTracer();
	}
	@Bean
	public APIClient feignBuilder() {
		/*
		 * Gson gson = new GsonBuilder() .setLenient() .create();
		 * new GsonEncoder(gson)).decoder(new GsonDecoder(gson))
		 */
		return Feign.builder().target(APIClient.class, "http://localhost:8081");
	}
}
