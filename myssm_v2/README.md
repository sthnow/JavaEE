# ssm框架-编写web项目时遇到的问题
1. java项目里classpath具体指哪儿个路径

    [java项目里classpath具体指哪儿个路径 - 大龙的博客 - CSDN博客](https://blog.csdn.net/u011095110/article/details/76152952)

2. 开发web的流程，先写什么再写什么
3. 这句话中的意思

    <!--切面配置--><aop:config>    <!--配置切入点和增强方法织入的方式-->    <aop:advisor advice-ref="txAdvice" pointcut="execution(* cn.itheima.service.*.*(..))" /></aop:config>

# 开发中的理解

1. 使用ssm框架的时候，只编写jsp页面是无法访问的（jsp页面放在web-inf路径下）。
    - 表现层框架使用的是SpringMVC框架，客户端发送请求被SpringMVC的前端控制器（DispatcherServlet）接收，然后发送给处理器（Handler,HandlerMapping、HandlerAdapter）。
    - HandlerMapping找到对应的方法，然后使用HandlerAdapter调用对应的方法处理请求，**自己编写的controller就是处理器适配器**,controller中有调用service层的方法。
    - 接收业务逻辑层返回处理后的数据给前端转发器
    - 前端转发器将处理后的数据转发给jsp页面，封装数据，返回html页面
    - 前端转发器将html页面发送给用户。整个流程结束
2. **关于请求方法**
    - 在浏览器中输入的请求都请求的是get方法
    - post请求用于提交表单，更新表单

# Spring的配置文件中要写什么

1. 如果使用了Spring，如果加注解就相当于在Spring的配置文件中加了一个<bean> 标签。就可以直接调用<bean>标签的id使用这个对象了
2. **DAO层**
    - 首先DAO层要操作数据库，因此要连接数据库，所以要把数据库的链接信息给出
        - 四大参数：驱动类路径，数据库url，用户名，密码
        - 因为要解耦（不能硬编码），因此要将数据库的链接信息写在配置文件当中
        - 在xml文件中加载配置文件使用<context:property-placeholder location="classpath:xxx">标签，classpath表示在classpath路径下，因为src路径下的文件编译后默认输出在classpath路径下
    - 然后DAO层操作数据库就要创建数据库连接，连接从连接池中获得，因此要配置数据库连接池
    - 有了数据库连接以后，要对数据库进行操作的话要用到对应的方法，这种方法通过mybatis（持久层框架实现）,因此要加载mybatis
    - 配置好mybatis以后，mybatis并不知道生成的mapper文件路径，因此要配置mapper扫描器

    3. **Service层**

    - 开启注解扫描即可

    4. **通知**

    - 配置通知
    - 配置切面：通知，切入点，织入的方式
    - Spring面向切面编程(AOP-execution表达式)
    - ```execution(* com.loongshawn.method.ces..*.*(..))```
    - 标识符	含义
    execution()	表达式的主体
    第一个“*”符号	表示返回值的类型任意
    com.loongshawn.method.ces	AOP所切的服务的包名，即，需要进行横切的业务类
    包名后面的“..”	表示当前包及子包
    第二个“*”	表示类名，*即所有类
    .*(..)	表示任何方法名，括号表示参数，两个点表示任何参数类型

# SpringMVC中的配置文件要写什么

三大组件的配置

1. 注解驱动
    - 帮我们配置HandlerMapping和HandlerAdapter
    - 如果有些自定义的HandlerAdapter，则需这样写
        - <mvc:annotation-driven conversion-service="自定义HandlerAdapter类名"><mvc:annotation-driven>
2. 视图解析器
    - 配置页面前缀和后缀
3. 开启注解扫描