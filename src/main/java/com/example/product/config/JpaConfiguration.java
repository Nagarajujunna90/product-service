/*
package com.example.orderservice.config;

import com.example.orderservice.model.AwsSecrete;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;

@Configuration
public class JpaConfiguration {
    private static final Gson gson = new Gson();
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretkey;

    @Bean
    public DataSource dataSource() {
        AwsSecrete secrets = getSecret();
        return DataSourceBuilder
                .create()
                //  .driverClassName("com.mysql.cj.jdbc.driver")
                .url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/employee")
                .username(secrets.getUserName())
                .password(secrets.getPassword())
                .build();

    }
    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.example.orderservice");
       // entityManagerFactoryBean.setPersistenceProviderClass();
        return entityManagerFactoryBean;
    }
    public static AwsSecrete getSecret() {
        String secretName = "mysql-rds";
        Region region = Region.of("us-east-1");
        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder().region(region).build();
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder().secretId(secretName).build();
        GetSecretValueResponse getSecretValueResponse;
        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw e;
        }

        String secret = getSecretValueResponse.secretString();
        return gson.fromJson(secret, AwsSecrete.class);
    }


}

*/
