package com.nttdata.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.nttdata.bootcamp.models.CurrentAccount;
import com.nttdata.bootcamp.repositories.ICurrentAccountRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@EnableEurekaClient
@Slf4j
@SpringBootApplication
public class BootcampServiceProductCurrentaccountApplication implements CommandLineRunner{
	
	@Autowired
	ICurrentAccountRepo carepo;
	
	@Autowired
	ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(BootcampServiceProductCurrentaccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		mongoTemplate.dropCollection("currentaccounts").subscribe();
		
		Flux.just(CurrentAccount.builder()
				.idCustomer("b1")
				.typeCustomer("customerPerson")
				.accountingBalance("100")
				.maintenance("2")
				.profile("none")
				.build()).flatMap(bs->{
						return carepo.save(bs);
				}).subscribe(s-> log.info("Se ingreso currentAccount: "+s));
		
	}

}