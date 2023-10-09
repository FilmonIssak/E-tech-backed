package com.Etech.Service;

import com.Etech.Model.Cart;
import com.Etech.Model.Product;

public interface CartService {

    public Cart addProductToCart(Long cartId, Product product);
}
