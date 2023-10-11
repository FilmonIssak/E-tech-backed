package com.Etech.Service;

import com.Etech.Dto.CartDto;
import com.Etech.Model.Cart;
import com.Etech.Model.Product;

import java.util.List;

public interface CartService {

  //  public Cart addProductToCart(Long cartId, Product product);

    public List<CartDto> viewAllProductsInCart();
}
