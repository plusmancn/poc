/*
 * Copyright (c) 2021. <plusmancn@gmail.com> All Rights Reversed.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package cn.plusman.poc.mybatis.plus.explore.mapper;


import cn.plusman.poc.mybatis.plus.explore.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author plusman
 * @since 2021/3/13 3:05 PM
 */
public interface UserMapper {
    User selectByid3(Integer id);
    
    /**
     * $ 号示例
     * @return
     */
    User selectUserWithDollar(String name);
    
    /**
     * # 号示例
     * @return
     */
    User selectUserWithPound(String name);
}
