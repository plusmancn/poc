/*
 * Copyright (c) 2021. <plusmancn@gmail.com> All Rights Reversed.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package cn.plusman.poc.mybatis.plus.explore.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author plusman
 * @since 2021/3/13 3:07 PM
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6340259322845405160L;
    
    private Integer id;
    private String name;
}
