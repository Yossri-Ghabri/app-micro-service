package org.micro.customerservice;

import org.micro.customerservice.entities.Customer;
import org.micro.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
        System.out.println("Hello this app MicroService For Customer");
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                               RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("thom").mail("thom@gmail.com").build(),
                            Customer.builder().name("alex").mail("alex@gmail.com").build(),
                            Customer.builder().name("jhon").mail("jhon@gmail.com").build()
                    )
            );
            customerRepository.findAll().forEach(customer -> {
                System.out.println("this Customer "+customer);
            });
        };
    }

}
