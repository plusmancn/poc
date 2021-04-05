package cn.plusman.poc.mybatis.plus.explore;

import cn.plusman.poc.mybatis.plus.explore.entity.Blog;
import cn.plusman.poc.mybatis.plus.explore.entity.User;
import cn.plusman.poc.mybatis.plus.explore.mapper.BlogMapper;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * MybatisPlus 启动流程
 * @author plusman
 * @since 2021/4/3 7:44 PM
 */
public class ApplicationBootMybatisPlus {
    public static void main(String[] args) {
        String resource = "mybatis/mybatis-config.xml";
        try {
            InputStream inputStream = null;
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                // mybatis-plus 启动方式
                new MybatisSqlSessionFactoryBuilder().build(inputStream);
                // mybatis 启动方式
                // new SqlSessionFactoryBuilder().build(inputStream);
        
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // testDuplicateMapperMethod(session);
                // testLambda(session);
                testInsert(session);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void testDuplicateMapperMethod(SqlSession session) {
        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        List<Blog> list = session.getMapper(BlogMapper.class).selectList();
        System.out.println(list);
    }
    
    public static void testLambda(SqlSession session) {
    
        List<Blog> list = session.getMapper(BlogMapper.class).selectList(
            new QueryWrapper<Blog>().lambda().eq(Blog::getBlogId, 3)
        );
        System.out.println(list);
    }
    
    /**
     * 测试数据插入时主键的作用
     * @param session
     */
    public static void testInsert(SqlSession session) {
        Blog blog = new Blog()
            .setContent("insert from mybatis-plus")
            .setJsonField(new String[]{"hello"});
        int affectedRows = session.getMapper(BlogMapper.class).insert(blog);
        System.out.println(affectedRows);
    }
    
}
