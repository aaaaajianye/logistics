package com.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 订单 √ 实地配送员 - 公司1 - 货物 - 货车 - 公司2 - 实地配送员
 */
@SpringBootApplication
@EnableSwagger2
public class LogisticsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run( LogisticsSystemApplication.class, args);
    }

}