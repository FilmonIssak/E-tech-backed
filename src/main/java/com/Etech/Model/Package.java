package com.Etech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue
    private Long id;
    //attribute for package only except for id
    private String name;
    private String description;
    private String price;
    private String image;
    private String category;
    private String quantity;
    private String status;
    private String date;
    private String time;
    private String location;
    private String duration;
    private String rating;
    private String review;
    private String discountPrice;


}
