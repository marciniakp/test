package com.example.restfullapi.controller;

import com.example.restfullapi.api.v1.mapper.CustomerMapper;
import com.example.restfullapi.api.v1.model.CustomerDTO;
import com.example.restfullapi.api.v1.model.CustomerListDTO;
import com.example.restfullapi.model.Customer;
import com.example.restfullapi.services.CategoryService;
import com.example.restfullapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    public static final String BASE_URL = "/api/v1/customers";

    CustomerService customerService;
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        /*Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
        customer1.setFirstName("Pszemek");
        customer2.setFirstName("Paweł");
        customer3.setFirstName("Andrzej");
        customer1.setLastName("Marciniak");
        customer2.setLastName("Wersun");
        customer3.setLastName("Andr");
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer1);
        CustomerDTO customerDTO1 = customerMapper.customerToCustomerDTO(customer2);
        CustomerDTO customerDTO2 = customerMapper.customerToCustomerDTO(customer3);
        List<CustomerDTO> allCustomers = new ArrayList<CustomerDTO>();
        allCustomers.add(customerDTO);
        allCustomers.add(customerDTO1);
        allCustomers.add(customerDTO2);*/
        return new CustomerListDTO(allCustomers);
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {

    }
}
