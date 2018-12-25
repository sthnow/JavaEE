package cn.itheima.dao;

import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;

import java.util.List;

public interface CustomerMapper {

    public List<Customer> findCustomerByVo(QueryVo vo);

    public Integer findCustomerByVoCount(QueryVo vo);

    public Customer findCustomerById(Long id);

    public void updateCustomerById(Customer customer);

    public void deleteCustomerById(Integer id);
}
