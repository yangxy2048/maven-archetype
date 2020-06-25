package com.github.archetype.service.impl;

import com.github.archetype.model.domain.City;
import com.github.archetype.service.CityService;
import org.springframework.stereotype.Service;

@Service("cityService")
public class CityServiceImpl extends BaseServiceImpl<City, Integer> implements CityService {
}