package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/*import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;*/

//@SpringBootApplication
//@EnableSwagger2
//@EnableHystrix
@SpringBootApplication(scanBasePackages = {
        "com.example.demo.orderservice",
        "com.example.demo.paymentservice",
        "com.example.demo.Auth"
})
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

   /* @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.orderservice")).build();
    }*/

}
