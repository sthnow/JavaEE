package cn.itheima.service;

import cn.itheima.dao.ItemsMapper;
import cn.itheima.pojo.Items;
import cn.itheima.pojo.ItemsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//实现业务逻辑层ItemService接口文件

//使用注解表明这是一个Service类，不用再Spring配置文件中说明
//交给Spring管理，使用注解时需要开启注解扫描

@Service
public class ItemServiceImpl implements ItemService {

//    业务逻辑层需要调用DAO层，因此创建一个DAO层的私有化成员变量
//    根据类型自动装配对象
    @Autowired
    private ItemsMapper itemsMapper;


    @Override
    public List<Items> list() throws Exception {
        //如果不需要任何查询条件，直接将example对象new出来即可
        //新建一个pojo对象
        ItemsExample example = new ItemsExample();

        List<Items> items = itemsMapper.selectByExampleWithBLOBs(example);

        return list();
    }

    @Override
    public Items findItemsById(Integer id) throws Exception {
        Items items = itemsMapper.selectByPrimaryKey(id);
        return items;
    }

    @Override
    public void updateItems(Items items) throws Exception {
        //更新以后返回主键
        itemsMapper.updateByPrimaryKeyWithBLOBs(items);
    }
}
