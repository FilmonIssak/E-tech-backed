package com.Etech.Configuration;

import com.Etech.Dto.CartDto;
import com.Etech.Dto.ProductQuantityDto;
import com.Etech.Dto.ProductWithOutQuantityDetailDto;
import com.Etech.Model.Cart;
import com.Etech.Model.Product;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        Converter<Map<Product, Integer>, List<ProductQuantityDto>> productMapConverter = ctx -> {
            Map<Product, Integer> source = ctx.getSource();
            return source.entrySet().stream()
                    .map(entry -> new ProductQuantityDto(mapper.map(entry.getKey(), ProductWithOutQuantityDetailDto.class), entry.getValue()))
                    .collect(Collectors.toList());
        };

        mapper.createTypeMap(Cart.class, CartDto.class)
                .addMappings(m -> m.using(productMapConverter).map(Cart::getProducts, CartDto::setProductQuantities));

        return mapper;
    }
}
