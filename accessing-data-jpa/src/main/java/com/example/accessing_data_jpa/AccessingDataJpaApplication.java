package com.example.accessing_data_jpa;

import com.example.accessing_data_jpa.model.Customer;
import com.example.accessing_data_jpa.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args ->  {
			// save a few customers
			repository.save(new Customer("Adrien", "Salez"));
			repository.save(new Customer("Emilie", "Inthavong"));
			repository.save(new Customer("Moutik", "Salez"));
			repository.save(new Customer("Mi", "Ah"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findByID(1L):");
			log.info("---------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Salez'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Salez").forEach(salez -> {
				log.info(salez.toString());
			});
			log.info("");
		});
	}

}
