package com.Etech.Service;


import com.Etech.Dto.ProductDto;
import com.Etech.Model.enums.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ViewerService {

    ProductDto findProductByName(String name);
    List<ProductDto> getAllProducts();
    List<ProductDto> findAllByCatagory(String category);

    List<ProductDto> findAllByKeyWord(String keyWord);

    List<ProductDto> filterProducts(String name, String category, String keyWord);
}
