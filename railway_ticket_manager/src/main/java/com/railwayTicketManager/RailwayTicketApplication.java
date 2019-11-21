package com.railwayTicketManager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages = {"com.railwayTicketManager.dao"})
public class RailwayTicketApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RailwayTicketApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RailwayTicketApplication.class, args);
	}

}
