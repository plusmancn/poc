package cn.plusman.poc.mybatis.plus.explore;

import cn.plusman.poc.mybatis.plus.explore.entity.Blog;
import cn.plusman.poc.mybatis.plus.explore.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
                new SqlSessionFactoryBuilder().build(inputStream);
        
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // List<Blog> list = session.getMapper(BlogMapper.class).selectList();
                // System.out.println(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
