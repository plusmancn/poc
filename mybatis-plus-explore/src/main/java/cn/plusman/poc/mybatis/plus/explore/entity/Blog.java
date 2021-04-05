package cn.plusman.poc.mybatis.plus.explore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * cn.plusman.mybatis.entity
 *
 * @author plusman
 * @since 12/17/20
 */
@Data
@Accessors(chain = true)
@TableName(
    resultMap = "BlogResultMap"
)
public class Blog implements Serializable {
    @TableId(
        value = "id",
        type = IdType.AUTO
    )
    private Integer blogId;
    private String content;
    private String camelStyle;
    
    /**
     * 需要在 resultMap 配置 Json Type Handler，才可正常显示
     * 不然显示为 null
     */
    @TableField(
        typeHandler = cn.plusman.poc.mybatis.plus.explore.typehandler.JsonTypeHandler.class
    )
    private String[] jsonField;
    /**
     * 如果字段为 String，则直接取出 JSON Stirng
     */
    // private String jsonField;
}
