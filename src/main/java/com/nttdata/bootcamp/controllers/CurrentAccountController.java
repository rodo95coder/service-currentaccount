package com.nttdata.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.exceptions.BusinessCreditNotFoundException;
import com.nttdata.bootcamp.models.BusinessCredit;
import com.nttdata.bootcamp.models.CurrentAccount;
import com.nttdata.bootcamp.services.ICurrentAccountService;
import com.nttdata.bootcamp.utils.Constants;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/currentAccount")
public class CurrentAccountController {

	@Autowired
	private ICurrentAccountService carepo;
	
	@Autowired
    private WebClient.Builder webClientBuilder;
	
	@GetMapping("/findAll")
	public Flux<CurrentAccount> findAll(){
		log.info("all CurrentAccount were consulted");
		return carepo.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Mono<CurrentAccount> findById(@PathVariable String id){
		log.info("a CurrentAccount was consulted by id");
		return carepo.findById(id);
	}
	
	@PutMapping("/update")
	public Mono<CurrentAccount> update(@RequestBody CurrentAccount currentAccount){
		return carepo.findById(currentAccount.getId()).flatMap(c->{
			c.setAccountingBalance(currentAccount.getAccountingBalance());
			c.setMaintenance(currentAccount.getMaintenance());
			log.info("a CurrentAccount was updated");
			return carepo.save(c);
		});
	}
	
	@PostMapping("/save")
	public Mono<CurrentAccount> save(@RequestBody CurrentAccount currentAccount){
		log.info("a CurrentAccount was created");
		Flux<BusinessCredit> businessCredits = webClientBuilder
                .baseUrl("http://service-product-personalcredit")
                .build()
                .get()
                .uri("/businessCredit/findByIdCustomerEnterprise/"+currentAccount.getIdCustomer())
                .retrieve()
                .bodyToFlux(BusinessCredit.class);
		return carepo.save(currentAccount).flatMap(p->{
			if(businessCredits!=null) {
				currentAccount.setProfile(Constants.PROFILEPYME);
			}else {
				currentAccount.setProfile(Constants.PROFILENONE);
			}
			return Mono.just(p);
		});
			
	}
	
	@DeleteMapping("/delete")
	public Mono<Void> delete(@RequestBody CurrentAccount currentAccount){
		log.info("a CurrentAccount was deleted");
		return carepo.delete(currentAccount);
	}
	
	@GetMapping("/findByIdCustomer/{idCustomer}")
	public Flux<CurrentAccount> findByIdCustomer(@PathVariable String idCustomer){
		log.info("all CurrentAccounts by id customer  were consulted");
		return carepo.findByIdCustomer(idCustomer);
	}
	
	@GetMapping("/findByIdCustomerAndTypeCustomer/{idCustomer}/{typeCustomer}")
	public Mono<CurrentAccount> findByIdCustomerAndTypeCustomer(@PathVariable String idCustomer, @PathVariable String typeCustomer){
		log.info("all CurrentAccounts by id customer  were consulted");
		return carepo.findByIdCustomerAndTypeCustomer(idCustomer,typeCustomer);
	}
	
}