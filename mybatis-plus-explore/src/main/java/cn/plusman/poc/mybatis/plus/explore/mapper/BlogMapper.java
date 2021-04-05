package cn.plusman.poc.mybatis.plus.explore.mapper;

import cn.plusman.poc.mybatis.plus.explore.entity.Blog;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * cn.plusman.mybatis.mapper
 *
 * @author plusman
 * @since 12/17/20
 */
public interface BlogMapper extends BaseMapper<Blog> {
    /**
     * 根据 id 获取博客内容
     * @param id
     * @return
     */
    Blog selectBlog(
        int id
    );
    
    /**
     * 获取博客列表
     * @return
     */
    List<Blog> selectList();
}
