package com.Etech.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double totalPrice;

    @OneToMany
    private List<Product> products;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Viewer viewer;
    public void addProduct(Product product){
        this.products.add(product);
        this.quantity += 1;
        this.totalPrice += product.getPrice();
    }
//    @OneToMany
//    private List<OrderDto> orders;

    public void removeProduct(Product product) {
        products.remove(product);
    }


}
