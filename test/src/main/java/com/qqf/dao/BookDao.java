package com.qqf.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/6 0006.
 */
@Repository
@Mapper
public interface BookDao {
    Map first(Map data);
    Map first1(Map data);
}
