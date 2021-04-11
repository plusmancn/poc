package cn.plusman.springtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author plusman
 * @since 2021/4/10 10:14 PM
 */
@Configuration
public class MyAppConfig {
    @Autowired
    private JdbcTemplate JdbcTemplate;
    
    @Bean
    public BookingServiceRegWithConfig bookingServiceRegWithConfig() {
        return new BookingServiceRegWithConfig(JdbcTemplate);
    }
}
