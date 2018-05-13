package com.example.restfullapi.services;

import com.example.restfullapi.api.v1.mapper.CustomerMapper;
import com.example.restfullapi.api.v1.model.CustomerDTO;
import com.example.restfullapi.controller.CustomerController;
import com.example.restfullapi.model.Customer;
import com.example.restfullapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerURL(this.getCustomerURL(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id).get();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerURL(getCustomerURL(customer.getId()));
        return customerDTO;

    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        savedCustomerDTO.setCustomerURL(getCustomerURL(savedCustomer.getId()));
        return savedCustomerDTO;

    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }


    private String getCustomerURL(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }
}
