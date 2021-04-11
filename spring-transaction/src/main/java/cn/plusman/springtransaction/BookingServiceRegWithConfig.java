package cn.plusman.springtransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * 通过 {@link MyAppConfig MyAppConfig} 注入，需要注释掉 @Component，不然会产生重复注入错误
 * @author plusman
 */
// @Component // 该类已经通过 MyAppConfig 以配置的形式注入
public class BookingServiceRegWithConfig {

	private final static Logger logger = LoggerFactory.getLogger(BookingServiceRegWithConfig.class);
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	public BookingServiceRegWithConfig(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public void book(String... persons) {
		logger.info("book mehtod of BookingServiceRegWithConfig");
		
		for (String person : persons) {
			logger.info("Booking " + person + " in a seat...");
			jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
		}
	}
	
	public List<String> findAllBookings() {
		return jdbcTemplate.query(
			"select FIRST_NAME from BOOKINGS",
			(rs, rowNum) -> rs.getString("FIRST_NAME")
		);
	}
}
