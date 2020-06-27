package com.github.simple;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.simple.entity.City;
import com.github.simple.mapper.CityMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 描述信息
 *
 * @author DevinYang
 * @createTime 2020-06-15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private CityMapper cityMapper;

    @Test
    public void testSelectOne() {
        City city = cityMapper.selectById(1L);
        System.out.println(city);
        Assert.assertEquals(city.getName(),"Beijing");
    }

    @Test
    public void testInsert() {
        City city = new City();
        city.setName("测试");
        city.setCountry("中国");
        city.setState("GZ");
        assertThat(cityMapper.insert(city)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(city.getId()).isNotNull();
    }

    @Test
    public void testDelete() {
        assertThat(cityMapper.deleteById(3L)).isGreaterThan(0);
        assertThat(cityMapper.delete(new QueryWrapper<City>()
                .lambda().eq(City::getName, "Shanghai"))).isGreaterThan(0);
    }

    @Test
    public void testUpdate() {
        City city = cityMapper.selectById(2);
        assertThat(city.getCountry()).isEqualTo("CN");
        assertThat(city.getName()).isEqualTo("Shanghai");

        cityMapper.update(
                null,
                Wrappers.<City>lambdaUpdate().set(City::getName, "guangzhou").eq(City::getId, 2)
        );

        assertThat(cityMapper.selectById(2).getName()).isEqualTo("guangzhou");
    }

    @Test
    public void testSelect() {
        List<City> cityList = cityMapper.selectList(null);
        Assert.assertEquals(5, cityList.size());
        cityList.forEach(System.out::println);
    }

    @Test
    public void testSelectByName() {
        List<City> cityList = cityMapper.selectByName("Shanghai");
        cityList.forEach(System.out::println);
    }

    @Test
    public void testSelectByState() {
        List<City> cityList = cityMapper.selectByState("CA");
        cityList.forEach(System.out::println);
    }


    @Test
    public void testPage() {
        System.out.println("----- baseMapper 自带分页 ------");
        Page<City> page = new Page<>(1, 2);
        IPage<City> userIPage = cityMapper.selectPage(page, new QueryWrapper<City>()
                .gt("id", 1));
        assertThat(page).isSameAs(userIPage);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        print(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

}
