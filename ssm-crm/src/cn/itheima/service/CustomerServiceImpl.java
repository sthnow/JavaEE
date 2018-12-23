package cn.itheima.service;

import cn.itheima.dao.CustomerMapper;
import cn.itheima.dao.DictMapper;
import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl  implements CustomerService{

//    service层调用Dao层
    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private CustomerMapper customerMapper;

//    覆盖父类的方法
    @Override
    public List<BaseDict> findDictById(String code) {
       List<BaseDict> list = dictMapper.findDictById(code);
       return list;

    }
//    查询表单
    @Override
    public List<Customer> findCustomerByVo(QueryVo vo) {
//        service层调用dao层
        List<Customer> listCustomer = customerMapper.findCustomerByVo(vo);
        return listCustomer;
    }

//    返回查到表单中记录的条数
    @Override
    public Integer findCustomerByVoCount(QueryVo vo) {
        Integer count = customerMapper.findCustomerByVoCount(vo);
        return count;
    }


//    修改详细
    @Override
    public Customer findCustomerById(Long id) {
        Customer customer = customerMapper.findCustomerById(id);
        return customer;
    }
}
