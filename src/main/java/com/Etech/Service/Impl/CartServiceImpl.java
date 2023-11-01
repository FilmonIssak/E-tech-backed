package com.Etech.Service.Impl;

import com.Etech.Dto.CartDto;
import com.Etech.Dto.OrderDto;
import com.Etech.Dto.ProductDto;
import com.Etech.Exception.ResourceException;
import com.Etech.Model.Cart;
import com.Etech.Model.Order;
import com.Etech.Model.Product;
import com.Etech.Repository.CartRepo;
import com.Etech.Service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CartDto> viewAllProductsInCart() {
        List<Cart> cartList = cartRepo.findAll();
        return cartList.stream().map(cart -> modelMapper.map(cart, CartDto.class)).collect(Collectors.toList());
    }

    @Override
    public CartDto findCartProductsById(long id) {
        Cart toGet = cartRepo.findById(id).orElseThrow(() -> new ResourceException("Cart with cart id: " + id + " is not present"));
        return modelMapper.map(toGet, CartDto.class);
    }

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
