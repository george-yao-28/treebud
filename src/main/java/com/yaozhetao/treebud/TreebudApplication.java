package com.yaozhetao.treebud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yaozhetao.treebud.mapper")
public class TreebudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreebudApplication.class, args);
	}

}
