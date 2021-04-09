package cn.plusman.springtransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Component
public class BookingService {

	private final static Logger logger = LoggerFactory.getLogger(BookingService.class);
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	public BookingService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public void book(String... persons) {
		for (String person : persons) {
			logger.info("Booking " + person + " in a seat...");
			jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
		}
	}
	
	// public void book(String... persons) {
	// 	transactionTemplate.execute(status -> {
	// 		for (String person : persons) {
	// 			logger.info("Booking " + person + " in a seat...");
	// 			jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
	// 		}
	// 		return 0;
	// 	});
	// }

	public List<String> findAllBookings() {
		return jdbcTemplate.query(
			"select FIRST_NAME from BOOKINGS",
			(rs, rowNum) -> rs.getString("FIRST_NAME")
		);
	}
}
