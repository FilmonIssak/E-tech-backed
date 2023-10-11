package com.Etech.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @ElementCollection
    @CollectionTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products = new HashMap<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    private Customer customer;

    @OneToOne(mappedBy = "cart")
    private Viewer viewer;

    public void addProduct(Product product, int quantity) {
        products.merge(product, quantity, Integer::sum);
        updateTotalPrice();
    }


    public void updateTotalPrice() {
        this.totalPrice = products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }



}
