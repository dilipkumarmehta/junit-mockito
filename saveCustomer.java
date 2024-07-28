@Transactional(propagation = Propagation.REQUIRED)
	public Customer save(Customer cust) throws CustomerSaveException {
		Customer scustomer = null;
		if (cust == null) {
			return null;
		}
		try { 
			Optional<Customer> customer = customerRepository.findById(cust.getId());
			if (customer.isPresent()) {
				throw new CustomerAlreadyExistException("existing customer" + cust.getId());
			}
			System.out.println("get custome calls");
			scustomer = customerRepository.save(cust);
			System.out.println("save customer");
		} catch (DataAccessException e) {
			throw new CustomerSaveException("Failed to save customer", e);
		}
		return scustomer;
	}
##########################below are test case for above method###############################################

/**
 * 
 */
package com.ibm.cust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import com.ibm.cust.beans.Customer;
import com.ibm.cust.exception.CustomerAlreadyExistException;
import com.ibm.cust.exception.CustomerSaveException;

/**
 * 
 */

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	Customer cust;

	@BeforeEach
	public void setUp() {
		cust = new Customer();
		cust.setId(2);
		cust.setEmail("dilip@gmail.com");
		cust.setLast_name("Mehta");
		cust.setFirst_name("Dilip");

	}

	@Test
	public void testSaveCustomerSuccess() {
		when(customerRepository.findById(cust.getId())).thenReturn(Optional.empty());
		when(customerRepository.save(cust)).thenReturn(cust);
		Customer save = customerServiceImpl.save(cust);
		assertNotNull(save);
		assertEquals(cust.getId(), save.getId());
		verify(customerRepository, times(1)).findById(cust.getId());
		verify(customerRepository, times(1)).save(cust);

	}

	@Test
	public void testExistingCustomer() {
		when(customerRepository.findById(cust.getId())).thenReturn(Optional.of(cust));
		Exception exception = assertThrows(CustomerAlreadyExistException.class, () -> customerServiceImpl.save(cust));
		System.out.println(exception.getMessage());// existing customer2
	}

	@Test
	public void dataSaveAceotiin() {
		when(customerRepository.findById(cust.getId())).thenReturn(Optional.empty());
		when(customerRepository.save(cust)).thenThrow(new DataAccessException("access exception") {
		});
		Exception exception = assertThrows(CustomerSaveException.class, () -> customerServiceImpl.save(cust));
		assertEquals("Failed to save customer", exception.getMessage());
		verify(customerRepository, times(1)).findById(cust.getId());
		verify(customerRepository, times(1)).save(cust);

	}

}
