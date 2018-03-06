package cn.itcast.user.dao;


//数据类，访问数据库

import cn.itcast.user.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLResult;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class UserDao {

    /*
        数据访问层
         功能:对数据库进行操作
         暴露出两个方法,login和regist,service在使用的时候直接使用这两个方法就可以
     */
    private String path = "D:/IDEA/users.xml"; //依赖数据文件



    //查询用户的功能
    public User findByUsername(String username) {
        /*
        1.得到Document
        2.xpath查询
            /1查询结果是否为null，如果为null，返回null
            /2如果不为null，需要把Element封装到User对象中
         */

        /*
        得到Document
            1.创建解析器
            2.
         */
        SAXReader reader = new SAXReader();
        //
        try {
            Document doc = reader.read(path);
           Element ele = (Element)doc.selectSingleNode("//user[@username='" + username + "']");
           if(ele == null)return null;

           User user = new User();
            //数据的转换过程
           String attrUsername =  ele.attributeValue("username");
           String attrPassword = ele.attributeValue("password");
           user.setUsername(attrUsername);
           user.setPassword(attrPassword);
            return user;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }


    //添加用户的功能
    public void add(User user) {
        /*
        1.得到Document
        2.通过Document得到root元素，<users>
        3.使用参数user，转换成Element对象
        4.将element添加到root元素中
        5.保存Document
         */

        //得到document对象
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(path);
            Element root = doc.getRootElement();
            Element userEle = root.addElement("user");

            //为user设置属性
            userEle.addAttribute("username", user.getUsername());
            userEle.addAttribute("password", user.getPassword());

            /*
            保存文档
             */
            //创建输出格式化
            OutputFormat format = new OutputFormat("\t",true); //缩进使用\t,是否使用换行,是
            format.setTrimText(true);       //清空原有的换行和缩进

            //创建xmlWriter
            XMLWriter writer;
            try {
                writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"utf-8"),format);
                writer.write(doc);
                writer.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
