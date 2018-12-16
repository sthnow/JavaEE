package cn.itheima.controller;


import cn.itheima.pojo.Items;
import cn.itheima.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller

//窄华请求映射
@RequestMapping("/items")
public class ItemsController {

//    表现层需要调用service层（业务逻辑层）
//    所以需要创建一个service层对象
    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    public ModelAndView itemsList() throws Exception{
        List<Items> list = itemService.list();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("itemList",list);

        modelAndView.setViewName("itemList");

        return modelAndView;
    }

    @RequestMapping(value="/find")
    public String find(HttpServletRequest request, Model model) throws Exception{

        Integer id = Integer.parseInt(request.getParameter("id"));
        Items item = itemService.findItemsById(id);
        model.addAttribute("item",item);
        return "editItem";
    }

    @RequestMapping(value = "findById")
    public String findById(){
        return "findItem";
    }
}
