package com.jarengisnerdev.FinancialTrackerApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.java.Log;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Log
@SpringBootApplication
@ComponentScan(basePackages = "com.jarengisnerdev.FinancialTrackerApplication")
public class FinancialTrackerApplication implements CommandLineRunner {

	private final DataSource dataSource;
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public FinancialTrackerApplication(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public static void main(String[] args) {
		SpringApplication.run(FinancialTrackerApplication.class, args);
	}

	@Override
	public void run(String... args){
		log.info("Application finished spinning up");
	}

}
