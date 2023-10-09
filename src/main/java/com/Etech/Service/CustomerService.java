package com.Etech.Service;


import com.Etech.Dto.CustomerDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Model.Customer;

import java.util.List;

public interface CustomerService {

    public List<CustomerDto> getAll();
    public CustomerDto register(CustomerDto customerDto);
    public List<ProductDto> findAll();

}
