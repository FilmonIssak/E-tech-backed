package com.Etech.Service;


import com.Etech.Dto.CartDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Dto.ViewerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ViewerService {

    ProductDto findProductByName(String name);
    List<ProductDto> getAllProducts();
    List<ProductDto> findAllByCatagory(String category);

    List<ProductDto> findAllByKeyWord(String keyWord);

//    List<ProductDto> filterProducts(String name, String category, String keyWord);
    public ViewerDto register(ViewerDto viewerDto);
    public CartDto addProductToViewerCart(Long viewerId, Long productId, int quantity);
//    public CartDto addProductToCartForViewer(Long viewerId, Long productId);

    public CartDto deleteProductFromCartForViewer(Long viewerId, Long productId);

}