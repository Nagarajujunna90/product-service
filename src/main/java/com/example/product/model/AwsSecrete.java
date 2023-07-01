package com.example.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AwsSecrete {
    private String userName;
    private String password;
    private String host;
    private String engine;
    private String port;
    private String dbInstanceIdentifier;

}