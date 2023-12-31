package com.Etech.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
