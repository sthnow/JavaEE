package cn.itheima.controller;


import cn.itcast.utils.Page;
import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.QueryVo;
import cn.itheima.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


//使用注解表明这是一个Controller
@Controller
//窄华请求映射
@RequestMapping("/customer")
public class CustomerController {


    //    controller调用service层
    @Autowired
    private CustomerService customerService;

    //    引入字典资源
//     使用value注解引入字典资源文件
//    这个引入是在controller层中引入的，属于SpringMVC框架，因此配置资源的引入要在SpringMVC的配置文件中配置
//    使用的标签，<context:property-placeholder location="classpath:xxxxx">
//    使用classpath的原因是，src及resource文件经过编译后，都在项目的output路径下，默认的output路径就是classes文件
    @Value("${customer.dict.source}")
    private String source;
    @Value("${customer.dict.industry}")
    private String industry;
    @Value("${customer.dict.level}")
    private String level;


    @RequestMapping("/access")
    public String access() throws Exception {
        return "customer";
    }

    @RequestMapping("/list")
    public String list(QueryVo vo, Model model) throws Exception {
//        客户来源
        List<BaseDict> sourceList = customerService.findDictById(source);
//        所属行业
        List<BaseDict> industryList = customerService.findDictById(industry);
//        客户级别
        List<BaseDict> levelList = customerService.findDictById(level);


//        if (vo.getCustName() != null) {
//            vo.setCustName(new String(vo.getCustName().getBytes("iso8859-1"), "utf-8"));
//        }

//      设置分页所相关的数据
//      设置查询的起始记录条数
        if (vo.getPage() == null) {
            vo.setPage(1);
        }

        vo.setStart((vo.getPage() - 1) * vo.getSize());
//         查询数据列表和数据总数
        List<Customer> resultList = customerService.findCustomerByVo(vo);
        Integer count = customerService.findCustomerByVoCount(vo);
        Page<Customer> page = new Page<Customer>();
//        数据总数
        page.setTotal(count);
//        每页显示的条数
        page.setSize(vo.getSize());
//        当前页数
        page.setPage(vo.getPage());
//        数据列表
        page.setRows(resultList);


        model.addAttribute("page", page);
//        高级查询下拉页表数据
//        使用mode想页面设置数据
        model.addAttribute("fromType", sourceList);
        model.addAttribute("industryType", industryList);
        model.addAttribute("levelType", levelList);

//        对get请求获取到的中文乱码问题进行转码


//        高级查询选中数据回显
        model.addAttribute("custName", vo.getCustName());
        model.addAttribute("custSource", vo.getCustSource());
        model.addAttribute("custIndustry", vo.getCustIndustry());
        model.addAttribute("custLevel", vo.getCustLevel());

        return "customer";
    }

    //    修改详细页面
    @RequestMapping("/detail")
    @ResponseBody
    public Customer detail(Long id) throws Exception {
        Customer customer = customerService.findCustomerById(id);
        return customer;
    }


    //  修改保存页面
    @RequestMapping("/update")
    public String update(Customer customer) throws Exception {
        customerService.updateCustomerById(customer);
        return "";
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        customerService.deleteCustomerById(id);
    }


}
