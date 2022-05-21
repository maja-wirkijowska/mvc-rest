package mvcrest.services;

import mvcrest.api.v1.mappers.CustomerMapper;
import mvcrest.api.v1.models.CustomerDTO;
import mvcrest.domain.Customer;
import mvcrest.repos.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepo customerRepo;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp() throws Exception {
        customerServiceImpl = new CustomerServiceImpl(customerMapper, customerRepo);
    }

    @Test
    void getAllCustomers() throws Exception {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Sam");
        customer2.setLastname("Axe");

        when(customerRepo.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        //when
        List<CustomerDTO> customers = customerServiceImpl.getAllCustomers();

        //then
        assertEquals(2, customers.size());
    }

    @Test
    void getCustomerById() throws  Exception {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");

        when(customerRepo.findById(anyLong())).thenReturn(java.util.Optional.of(customer1));

        //when
        CustomerDTO customer = customerServiceImpl.getCustomerById(1L);

        assertEquals("Michale", customer.getFirstname());
    }
//    @Test
//    public void createNewCustomer() throws Exception {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setFirstname("Jim");
//
//        Customer savedCustomer = new Customer();
//        savedCustomer.setFirstname(customerDTO.getFirstname());
//        savedCustomer.setLastname(customerDTO.getLastname());
//        savedCustomer.setId(1L);
//
//        when(customerRepo.save(any(Customer.class))).thenReturn(savedCustomer);
//
//        CustomerDTO savedDto = customerServiceImpl.createNewCustomer(customerDTO);
//        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
//        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
//    }
}