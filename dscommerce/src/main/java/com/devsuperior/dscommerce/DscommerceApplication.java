package com.devsuperior.dscommerce;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.projections.UserDetailsByEmailProjection;
import com.devsuperior.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DscommerceApplication {
	@Autowired
	private UserRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(DscommerceApplication.class, args);
	}
}
