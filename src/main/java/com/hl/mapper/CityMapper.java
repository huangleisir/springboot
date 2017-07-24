package com.hl.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hl.entity.City;

public interface CityMapper {
	
	@Select("select * from city where id = #{id}")
	City findCityById(@Param("id") String id);
	
	City findById(@Param("id") String id);
}

