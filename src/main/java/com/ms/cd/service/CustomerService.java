package com.ms.cd.service;

import com.ms.cd.dto.CustomerDTO;
import com.ms.cd.exception.CustomerException;

public interface CustomerService {

	CustomerDTO getCustomerByContact(long contact) throws CustomerException;
}
