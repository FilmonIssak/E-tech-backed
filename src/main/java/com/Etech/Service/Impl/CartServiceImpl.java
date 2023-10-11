package com.Etech.Service.Impl;

import com.Etech.Repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {

    @Autowired
    private CartRepo cartRepo;

//    @Override
//    public Cart addProductToCart(Long cartId, Product product) {
//        Optional<Cart> optionalCart = cartRepository.findById(cartId);
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//            cart.addProduct(product);
//            return cartRepository.save(cart);
//        }
//        return null; // or throw an exception
//    }
}
