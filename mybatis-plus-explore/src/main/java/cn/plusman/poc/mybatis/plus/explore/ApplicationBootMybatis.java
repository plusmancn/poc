package cn.plusman.poc.mybatis.plus.explore;

import cn.plusman.poc.mybatis.plus.explore.entity.User;
import cn.plusman.poc.mybatis.plus.explore.mapper.UserMapper;
import cn.plusman.poc.mybatis.plus.explore.util.SystemEnv;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis 启动流程
 * @author plusman
 * @since 2021/4/3 7:44 PM
 */
public class ApplicationBootMybatis {
    public static void main(String[] args) {
        String resource = "mybatis/mybatis-config.xml";
        try {
            InputStream inputStream = null;
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream, SystemEnv.getProperties());
        
            try (
                // SqlSession session = sqlSessionFactory.openSession()
                SqlSession session = sqlSessionFactory.openSession(ExecutorType.REUSE)
            ) {
                testDollarAndPound(session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * # 和 $ 测试
     * @param session
     */
    public static void testDollarAndPound(SqlSession session) {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User userDollar = userMapper.selectUserWithDollar("Shulei");
        User userPound1 = userMapper.selectUserWithPound("Shulei");
        User userPound2 = userMapper.selectUserWithPound("JiaNan");
    
        System.out.println(userDollar);
        System.out.println(userPound1);
        System.out.println(userPound2);
    }
}
