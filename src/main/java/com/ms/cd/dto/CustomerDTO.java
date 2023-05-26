package com.ms.cd.dto;

import java.util.List;

import com.ms.cd.entity.Customer;

public class CustomerDTO {

	private long contact;
	private String name;
	private int age;
	private char gender;
	private String password;
	private StockDTO currentStock;
	private List<Long> friendFamilyList;
	private List<CallDTO> callRecords;

	public static CustomerDTO createDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setContact(customer.getContact());
		customerDTO.setName(customer.getName());
		customerDTO.setAge(customer.getAge());
		customerDTO.setGender(customer.getGender());
		customerDTO.setPassword(customer.getPassword());
		StockDTO stockDTO = new StockDTO();
		stockDTO.setStockId(customer.getStockId());
		customerDTO.setCurrentStock(stockDTO);
		return customerDTO;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public StockDTO getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(StockDTO stockDTO) {
		this.currentStock = stockDTO;
	}

	public List<Long> getFriendFamilyList() {
		return friendFamilyList;
	}

	public void setFriendFamilyList(List<Long> friendFamilyList) {
		this.friendFamilyList = friendFamilyList;
	}

	public List<CallDTO> getCallRecords() {
		return callRecords;
	}

	public void setCallRecords(List<CallDTO> callRecords) {
		this.callRecords = callRecords;
	}

}
