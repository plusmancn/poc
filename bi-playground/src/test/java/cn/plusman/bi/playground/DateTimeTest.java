package cn.plusman.bi.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author plusman
 * @since 2021/9/23 4:46 PM
 */
public class DateTimeTest {
    private final static DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    
    /**
     * 每年第一周测试
     * @throws ParseException
     */
    @Test
    public void weekTest() throws ParseException {
        SimpleDateFormat weekFormatter = new SimpleDateFormat("w");
        String week;
        
        week = weekFormatter.format(
            // 为周五
            inputFormat.parse("2021-01-01")
        );
        Assertions.assertEquals("1", week);
    
        week = weekFormatter.format(
            // 为周日
            inputFormat.parse("2021-01-03")
        );
        Assertions.assertEquals("1", week);
    
        week = weekFormatter.format(
            // 为周一
            inputFormat.parse("2021-01-03")
        );
        Assertions.assertEquals("2", week);
    }
    
    /**
     * 不用使用 Calendar#getWeeksInWeekYear，数据输出与 DateFormat 不一致
     * DateFormat 内部使用的是 Calendar#get
     * 
     * @throws ParseException
     */
    @Test
    public void getWeekByCalendar() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(1);
        
        int weekYear;
        
        // 周五
        calendar.setTime(inputFormat.parse("2021-01-01"));
        weekYear = calendar.get(Calendar.WEEK_OF_YEAR);
        Assertions.assertEquals(1, weekYear);
        
        // 周五
        calendar.setTime(inputFormat.parse("2021-01-01"));
        weekYear = calendar.getWeeksInWeekYear();
        Assertions.assertEquals(52, weekYear);
    
        // 周日
        calendar.setTime(inputFormat.parse("2021-01-03"));
        weekYear = calendar.get(Calendar.WEEK_OF_YEAR);
        Assertions.assertEquals(1, weekYear);
    
        // 周一
        calendar.setTime(inputFormat.parse("2021-01-04"));
        weekYear = calendar.get(Calendar.WEEK_OF_YEAR);
        Assertions.assertEquals(2, weekYear);
    }
    
    /**
     * 月内操作
     */
    @Test
    public void getThisMonthDays() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputFormat.parse("2021-01-01"));
    
        // 获取本月最后一天
        int theLastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Assertions.assertEquals(31, theLastDayOfMonth);
    
        calendar.setTime(inputFormat.parse("2021-02-15"));
        theLastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Assertions.assertEquals(28, theLastDayOfMonth);
    }
}
