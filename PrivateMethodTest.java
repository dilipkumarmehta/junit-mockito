public static Customer getCustomerById2(Integer id) {
		try {
			 Customer cust=new Customer();
			 cust.setEmail("dddd");
			Optional<Customer> byId = Optional.of(cust);
			if (byId.isPresent()) {
				return byId.get();
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

==============================

  //private Method testing	
	@Test
    public void testGetCustomerById1_CustomerFound() throws Exception {
        Customer customer = new Customer();
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Method method = CustomerServiceImpl.class.getDeclaredMethod("getCustomerById1", Integer.class);
        method.setAccessible(true);
        Customer result = (Customer) method.invoke(customerServiceImpl,1);
        assertEquals(customer, result);
    }
}




