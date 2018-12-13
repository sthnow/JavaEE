package cn.itheima.service;


import cn.itheima.pojo.Items;

import java.util.List;

//业务逻辑层接口文件
public interface ItemService {

    public List<Items> list() throws Exception;

    public Items findItemsById(Integer id) throws Exception;

    public void updateItems(Items items) throws Exception;
}
