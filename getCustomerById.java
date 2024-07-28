@Override
	public Customer getCustomerById(Integer id) {
		try {
			Optional<Customer> byId = customerRepository.findById(id);
			if (byId.isPresent()) {
				//return byId.get();
			} else
				throw new CustomerNotFoundException("Customer not found with id " + id);
		} catch (SQLGrammarException e) {
			// Handle the exception
			//System.err.println("SQL Grammar Error: " + e.getMessage());
			// Optionally, log the error or rethrow it as a custom exception
			throw new CustomDatabaseException("There was a problem with the SQL syntax", e);

		} catch (DataAccessException ex) {
			
		}
		return null;

	}


===========================================


@Test
	public void getCustomerByIdREturnEmpty() {
		// 1. for success
		when(customerRepository.findById(1)).thenReturn(Optional.of(cust));
		assertEquals(2, customerServiceImpl.getCustomerById(1).getId());
		// 2. for return empty data from db
		when(customerRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(CustomerNotFoundException.class, () -> customerServiceImpl.getCustomerById(1));
		// 3. when exception thrown while query
		when(customerRepository.findById(2)).thenThrow(new SQLGrammarException("sqlException", null) {
		});
		assertThrows(CustomDatabaseException.class, () -> customerServiceImpl.getCustomerById(2));
		
	}

	@Test
	public void extracted() {
		when(customerRepository.findById(2)).thenThrow(new DataAccessException("data access Exception") {});
		assertEquals(null, customerServiceImpl.getCustomerById(2));
	}
