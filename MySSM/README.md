# SpringMVC

# 简介

SpringMVC是一个表现层框架

# 处理流程

[](https://www.notion.so/384e600ad81444e1b33c3daa119f39de#6f48090e91e94a1796d1bcc258b3a2f8)

- 前端控制器控制所有表现层数据的流转
- 处理器和业务层进行交互。前端控制器将获取的请求交给前端控制器，前端控制器再将请求交给处理器，处理器解析参数，交给业务层，业务层再和持久层进行交互。最后将处理后的结果返回给处理器。
- 前端控制器将返回来的数据交给jsp，jsp将返回的数据和标签封装成HTML页面返回给控制器（浏览器只能解析静态的HTML页面），然后前端控制器将HTML页面响应给用户

**使用注解制定url到请求方法的映射，如果使用配置文件，在多用户修改配置文件的情况下无法确定以谁的为准，因此使用注解**

# 框架结构

- 前端控制器（DispatcherServlet）：处理数据在三大组件之间的流转
- 三大组件：
    - 处理器映射器（HandlerMapping）：作用是将url中的字符串映射到方法名
        - 相当于一个HashMap，key是url的字符串，value是方法名
    - 处理器适配器（HandlerAdapter）：根据不同的Handler（例如注解和非注解形式）找到不同的处理器适配器去执行Handler，返回ModeAndView
        - 什么是Handler？——自己要写的Controller
    - 视图解析器（ViewResolver）：将不同的视图类型解析
    - 这些组件中，自己要写的是
        - Handler
        - jsp

## 处理流程

1. 前端控制器：浏览器将用户请求发送给前端控制器，前端控制器将请求转发给处理器映射器
2. 处理器映射器：处理器映射器根据请求的url找到对应的方法，形成处理器执行链返回给前端控制器。如果有设置拦截器，拦截器决定是否拦截请求。
3. 处理器适配器：前端控制器将Handler转发给处理器适配器，处理器适配器根据不同的Handler找到不同的适配器处理，返回ModeAndView给前端控制器。（Mode中存放数据，View存放要将数据返回给的页面）
4. 视图解析器：前端控制器将ModeAndView转发给视图解析器，视图解析器根据不同的视图解析，返回View对象（是一个html标签）

## 注解驱动和注解扫描的区别

- **注解驱动**：配置最新的注解处理器映射器和处理器适配器
- **注解扫描**：扫描注解的

# POJO

- POJO是带有属性和对应的set和get方法的javabean（java可重用对象）
- POJO分为三类：
    - VO：view-object，视图对象,用来将表示层获取到的数据封装到这个对象里
    - BO：business-object，业务对象，用来将业务层的数据封装到这个对象里
    - PO：persistent-object，用来将数据访问层的数据封装到这个对象
- 三种类别的POJO可以混着用（因为都是一些属性和对应的set和get方法），但是一般不这么做，会导致代码混乱。

# DAO层

**javaWeb经典三层架构之一**

- web层（表示层）：负责获取数据，通知处理数据，展示数据
- service层（业务逻辑层）：负责联系表示层和数据访问层，将一些业务逻辑加在其中
- dao层（数据访问层）：负责处理数据的增删改查

## javaWeb三层架构和MVC设计模式的区别

MVC设计模式：即mode，view，controller。

- 不同
    - MVC设计模式是一种设计模式，目的是将html代码和业务逻辑代码分开（对比mode1），使代码看起来更清晰
    - 经典三层架构是一种分层思想，为了让不同的人负责不同的模块
- 相同
    - 共同的点就是分层，解耦

# Controller方法的返回值（指定返回到哪个页面，指定返回到页面的数据）

- ModeAndView
    - modeAndView.addObject("itemList",list)；指定返回页面的数据
    - modeAndView.setViewName("itemList");    指定返回的页面
- String(推荐使用)
    - 返回普通字符串，就是页面去掉前缀和后缀的部分，返回给页面数据通过Model来完成
    - 返回的字符串以forward:开头为请求转发
    - 返回的字符串以redirect:开头为重定向
- 返回void（使用它破坏了springMVC的结构，页面要加全路径，因此不推荐使用）
    - 可以使用request.setAttribut来给页面返回数据
    - 可以使用response.getRequestDispatcher().forward()来指定返回的页面
    - 如果controller返回值为void，则不走springMVC的组件，所以要写页面的完整路径

    相对路径，相对于当前类的目录下，可以使用相对路径跳转

    绝对路径，相对于当前项目的路径下，使用绝对路径跳转。

    在SpringMVC中，不管是forward还是redirect后面凡是以“/”开头的就是绝对路径；不以"/"开头的就是相对路径

    例如：forward:/iterms/itemEdit.action  是绝对路径

    forward:itemEdit.action 是相对路径

**Get请求和Post请求的区分**

- 凡是通过浏览器及问号传参都是get请求
- post请求是提交数据进行更新保存的，其他都是get请求

# 上传图片

1. 在tomcat中配置虚拟图片服务器
2. 导入fileupload的jar包
3. 在springMvc.xml中配置上传组件
4. 在页面上编写上传域,更改form标签的类型
5. 在controller方法中可以使用MultiPartFile接口接收上传的图片
6. 将文件名保存到数据库,将图片保存到磁盘中

# Json数据交互:

1. 需要加入jackson的jar包
2. @Requestbody:将页面传到controller中的json格式字符串自动转换成java的pojo对象
3. @ResponseBody:将java中pojo对象自动转换成json格式字符串返回给页

# RestFul支持:

就是对url的命名标准,要求url中只有能名词,没有动词(不严格要求),但是要求url中不能用问号?传参
传参数:
页面:${pageContext.request.contextPath }/items/itemEdit/${[item.id](http://item.id/)}
方法: @RquestMapping("/itemEdit/{id}")
方法: @PathVariable("id") Integer idd

# 拦截器

作用:拦截请求,一般做登录权限验证时用的比较多

1. 需要编写自定义拦截器类,实现HandlerInterceptor接口
2. 在spirngMvc.xml中配置拦截器生效

# 登录权限验证

1. 编写登录的controller, 编写跳转到登录页面的方法, 编写登录验证方法
2. 编写登录页面
3. 编写拦截器

运行过程

1. 访问随意一个页面,拦截器会拦截请求,会验证session中是否有登录信息
如果已登录,放行
如果未登录,跳转到登录页面
2. 在登录页面中输入用户名,密码,点击登录按钮,拦截器会拦截请求,如果是登录路径放行
在controller方法中判断用户名密码是否正确,如果正确则将登录信息放入session

## 关于mode的一点补充

mode底层使用了request域，但是对request域进行了扩展。

不管是使用请求转发还是重定向，都可以把数据传输过去

所以推荐使用mode，而不是request和response