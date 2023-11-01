package com.Etech.Service;

import com.Etech.Dto.CartDto;
import com.Etech.Model.Cart;
import com.Etech.Model.Product;

import java.util.List;

public interface CartService {
    public List<CartDto> viewAllProductsInCart();
    public CartDto findCartProductsById(long id);

}
