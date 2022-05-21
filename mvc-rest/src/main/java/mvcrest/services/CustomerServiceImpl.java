package mvcrest.services;

import mvcrest.api.v1.mappers.CustomerMapper;
import mvcrest.api.v1.models.CustomerDTO;
import mvcrest.domain.Customer;
import mvcrest.repos.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepo customerRepo) {
        this.customerMapper = customerMapper;
        this.customerRepo = customerRepo;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepo.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        Customer savedCustomer = customerRepo.save(customer);
        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return returnDto;
    }
}
