package com.example.restfullapi.services;

import com.example.restfullapi.api.v1.mapper.CustomerMapper;
import com.example.restfullapi.api.v1.model.CustomerDTO;
import com.example.restfullapi.model.Customer;
import com.example.restfullapi.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final String FIRST_NAME = "1st";
    private static final String LAST_NAME = "last";
    @Mock
    CustomerRepository customerRepository;
    CustomerService customerService;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository,customerMapper);
    }

    @Test
    public void getAllCustomers() throws Exception {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
        customer1.setFirstName("Pszemek");
        customer2.setFirstName("Paweł");
        customer3.setFirstName("Andrzej");
        customer1.setLastName("Marciniak");
        customer2.setLastName("Wersun");
        customer3.setLastName("Andr");

        List<Customer> customers = Arrays.asList(customer1, customer2, customer3);
        when(customerRepository.findAll()).thenReturn(customers);

        assertEquals(3,customerService.getAllCustomers().size());

    }

    @Test
    public void getCustomerById() throws Exception {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        assertEquals(FIRST_NAME,customerService.getCustomerById(1L).getFirstName());

    }

    @Test
    public void createNewCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer customer = new Customer();
        customer.setLastName(customerDTO.getFirstName());
        customer.setFirstName(customerDTO.getLastName());
        customer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerDTO newCustomer = customerService.createNewCustomer(customerDTO);

        assertEquals(customer.getFirstName(),newCustomer.getFirstName());
        assertThat(customer.getLastName(),equalTo(newCustomer.getLastName()));

        assertEquals("/api/v1/customers/1",newCustomer.getCustomerURL());

    }

    @Test
    public void deleteCustomerById() throws Exception {
        Long id = 1L;
        customerService.deleteCustomerById(id);

        verify(customerRepository,times(1)).deleteById(anyLong());

    }

}