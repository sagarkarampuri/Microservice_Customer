package com.ms.cd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.cd.dto.CallDTO;
import com.ms.cd.dto.CustomerDTO;
import com.ms.cd.dto.StockDTO;
import com.ms.cd.exception.CustomerException;
import com.ms.cd.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient client;

	String stockUri;

	String friendFamilyUri;

	String callUri;

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

		List<ServiceInstance> ffInstances = client.getInstances("FriendFamilyMS");
		if (ffInstances != null && !ffInstances.isEmpty()) {
			friendFamilyUri = ffInstances.get(0).getUri().toString();
		}
		@SuppressWarnings("unchecked")
		List<Long> contacts = restTemplate.getForObject(
				friendFamilyUri + "/customers/" + customerDTO.getContact() + "/friendandfamily", List.class);
		customerDTO.setFriendFamilyList(contacts);

		List<ServiceInstance> callInstances = client.getInstances("CallMS");
		if (callInstances != null && !callInstances.isEmpty()) {
			callUri = callInstances.get(0).getUri().toString();
		}
		@SuppressWarnings("unchecked")
		List<CallDTO> records = restTemplate.getForObject(callUri + "/calls/" + customerDTO.getContact() + "/record",
				List.class);
		customerDTO.setCallRecords(records);

		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}
}
