package cn.itheima.dao;

import cn.itheima.pojo.BaseDict;

import java.util.List;

public interface DictMapper {

    public List<BaseDict> findDictById(String code);
}
