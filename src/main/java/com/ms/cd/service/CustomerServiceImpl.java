package com.ms.cd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ms.cd.dto.CustomerDTO;
import com.ms.cd.entity.Customer;
import com.ms.cd.exception.CustomerException;
import com.ms.cd.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private Environment environment;

	@Override
	public CustomerDTO getCustomerByContact(long contact) throws CustomerException {
		Customer customer = customerRepository.findById(contact).orElseThrow(
				() -> new CustomerException(environment.getProperty("CustomerService.customer_not_found") + contact));
		return CustomerDTO.createDTO(customer);
	}

}
