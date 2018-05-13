package com.example.restfullapi.services;


import com.example.restfullapi.api.v1.mapper.CustomerMapper;
import com.example.restfullapi.api.v1.model.CustomerDTO;
import com.example.restfullapi.bootstrap.Bootstrap;
import com.example.restfullapi.model.Customer;
import com.example.restfullapi.repositories.CategoryRepository;
import com.example.restfullapi.repositories.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest //daje nam polaczenie z baza
public class CustomerServiceTestIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;


    @Before
    public void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void saveAndFind() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("first");
        customerDTO.setLastName("wersun");

        CustomerDTO savedCustomer = customerService.createNewCustomer(customerDTO);
        assertEquals("/api/v1/customers/4",savedCustomer.getCustomerURL());
        Customer customer = customerRepository.findById(1L).get();
        assertNotNull(customer);

    }
}
