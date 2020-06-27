package com.github.simple.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.simple.entity.City;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 测试 Mapper 接口
 * </p>
 *
 * @author DevinYang
 * @since 2020-06-27
 */
@Repository
public interface CityMapper extends BaseMapper<City> {

    @Select("select * from city where name = #{name}")
    List<City> selectByName(String name);

    List<City> selectByState(@Param("state") String state);

}
