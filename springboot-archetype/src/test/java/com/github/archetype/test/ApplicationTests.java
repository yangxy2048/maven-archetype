package com.github.archetype.test;

import com.github.pagehelper.PageInfo;
import com.github.archetype.model.domain.City;
import com.github.archetype.service.CityService;
import com.github.archetype.util.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationTests extends SpringBaseTest {

    @Autowired
    private CityService cityService;

    @Test
    public void selectPage() {
        cityService.selectPageAndCount(null, 1, 3).getList().stream()
                .map(JsonUtils::toJson)
                .forEach(log::info);
    }

}