package cn.plusman.poc.test.explore5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author plusman
 * @since 2021/9/17 11:57 PM
 */
class SimpleClassTest {
    SimpleClass simpleClass = new SimpleClass();

    @Test
    void hello() {
        String resp = simpleClass.Hello("Junit5");
        Assertions.assertEquals("Hello Junit5", resp);
    }
}