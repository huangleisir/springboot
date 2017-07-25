package com.hl.mapper.cluster;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hl.entity.City;
import com.hl.entity.User;

public interface UserMapper {
	
	@Select("select * from user where id = #{id}")
	User findUserById(@Param("id") String id);
	
	User findById(@Param("id") String id);
	
	List<User> selectAll();
}

