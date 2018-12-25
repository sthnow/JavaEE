package cn.itheima.service;

import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

public interface CustomerService {

    public List<BaseDict> findDictById(String code);

    public List<Customer> findCustomerByVo(QueryVo vo);

    public Integer findCustomerByVoCount(QueryVo vo);

    public Customer findCustomerById(Long id);

    public void updateCustomerById(Customer customer);

    public void deleteCustomerById(Integer id);
}