package com.eureka.zuul.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponseModel {
    private String message;
    private String token;
}
