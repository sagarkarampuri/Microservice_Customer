package com.ms.cd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.cd.dto.CustomerDTO;
import com.ms.cd.dto.StockDTO;
import com.ms.cd.exception.CustomerException;
import com.ms.cd.service.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient client;

	String stockUri;

	@GetMapping("/customers/{contact}")
	public ResponseEntity<CustomerDTO> getCustomerByContact(@PathVariable("contact") long contact)
			throws CustomerException {
		CustomerDTO customerDTO = customerService.getCustomerByContact(contact);
		List<ServiceInstance> stockInstances = client.getInstances("StockMS");
		if (stockInstances != null && !stockInstances.isEmpty()) {
			stockUri = stockInstances.get(0).getUri().toString();
		}
		StockDTO stockDTO = restTemplate
				.getForObject(stockUri + "/stocks/" + customerDTO.getCurrentStock().getStockId(), StockDTO.class);
		customerDTO.setCurrentStock(stockDTO);
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}
}
