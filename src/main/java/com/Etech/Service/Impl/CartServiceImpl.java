package com.Etech.Service.Impl;

import com.Etech.Model.Cart;
import com.Etech.Model.Product;
import com.Etech.Repository.CartRepository;
import com.Etech.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addProductToCart(Long cartId, Product product) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.addProduct(product);
            return cartRepository.save(cart);
        }
        return null; // or throw an exception
    }
}
