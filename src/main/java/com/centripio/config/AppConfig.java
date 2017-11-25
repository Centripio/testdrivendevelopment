package com.centripio.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Carlos on 9/4/17.
 */
@Configuration
@ComponentScan(basePackages = "com.centripio")
@EnableJpaRepositories(basePackages = "com.centripio")
@EnableTransactionManagement
public class AppConfig {

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder().generateUniqueName(true)
//                .setType(EmbeddedDatabaseType.H2).setScriptEncoding("UTF-8").ignoreFailedDrops(true)
//                .addScript("data.sql").build();
//    }
}
