package com.logistics.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//使用注解扫描mapper文件，一般我们都在配置类中扫描，这样不用在启动类中进行扫描
@MapperScan("com.logistics.mapper")
//开启自动管理事务
@EnableTransactionManagement
//设置当前类为配置类
@Configuration
public class MybatisPlusConfig {

    //分页配置插件，然后就可以直接使用Page对象了
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}