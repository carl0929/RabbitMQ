package com.carl.exportexcle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.carl.exportexcle.dao")
public class ExportExcleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExportExcleApplication.class, args);
	}

}
