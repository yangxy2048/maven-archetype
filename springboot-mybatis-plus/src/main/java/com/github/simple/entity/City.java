package com.github.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author DevinYang
 * @since 2020-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class City extends Model<City> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("NAME")
    private String name;

    private String state;

    private String country;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
