package com.ms.cd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.cd.dto.CustomerDTO;
import com.ms.cd.entity.Customer;
import com.ms.cd.exception.CustomerException;
import com.ms.cd.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDTO getCustomerByContact(long contact) throws CustomerException {
		Customer customer = customerRepository.findById(contact)
				.orElseThrow(() -> new CustomerException("customer not found with phone number : " + contact));
		return CustomerDTO.createDTO(customer);
	}

}
