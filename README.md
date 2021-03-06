# Mybatis
## 简介

- Mybatis是一个开源的持久层框架。他对jdbc操作数据库的过程进行了封装，使开发者只需要关心SQL本身而不需要处理对数据库连接的操作如加载驱动类，获取connection，结果集解锁等过程。
- MyBatis通过xml或者注解的方式将要执行的statement配置起来，并通过java对象和statement中的sql进行映射生成最终的sql语句，最后由mybatis框架执行sql语句并将结果映射成java对象中返回。

## jdbc的缺点

- 频繁的创建和释放资源，影响系统性能——使用数据库连接池可以改善
- sql（结构化查询语言）和java语言写在一起，存在硬编码情况，不利于维护——将java代码和sql语句分开可以改善，将sql语句写入配置文件中
- 使用preparestament的占位符符号数目固定，存在硬编码，不利于使用——mybatis中的if标签可以解决
- 对结果集解析存在硬编码，sql解析的变化会造成结果集的变化，系统不利于维护——将数据库记录封装成pojo对象然后解析比较方便

## MyBatis的结构

- Mybatis配置文件
    - SqlMapConfig——mybatis核心配置文件
    - mapper.xml——mybatis映射文件
- SqlSessionFactory
- SqlSession
- Executor
- MappedStatement
- 数据库

### SqlMapConfig

这是mybatis全局配置文件，配置包括MyBatis运行环境等信息

### mapper.xml

sql映射文件，设置操作数据库的sql语句，需要在SqlMapConfig文件中加载

## #{}占位符和${}拼接符

- #{}占位符可以实现preparestament向占位符中设置值，自动进行java类型和jdbc类型转换，#{}可以有效防止sql注入。
    - #{}可以接收pojo类型或者基本类型值。
        - 如果接收pojo类型，括号里要写属性名称
        - 如果接受基本类型，括号中可以任意写
- ${}拼接符可以将parameterType传入的内容原封不动的拼接到sql语句当中。拼接符也可以传入pojo类型和类型的值。
    - 如果传入的是pojo类型，括号中要写对应的属性名称
    - 如果传入的是基本类型的值，括号中必须要写value

## parameterType和resultType

- parameterType：指定传入参数类型
- resultType：指定输出参数类型

## selectOne和selectList

- selectOne：指定查询一条语句
- selectList：指定查询多条语句。使用这条语句时resultType只需设置传输的本来的类型而不需要设置List，mybatis会自动将对象封装成List

## MyBatis和Hibernate的区别

- MyBatis：Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，例如互联网软件、企业运营类软件等，因为这类软件需求变化频繁，一但需求变化要求成果输出迅速。但是灵活的前提是mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。
- Hibernate：Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。但是Hibernate的学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎样用好Hibernate需要具有很强的经验和能力才行

## DAO开发方法
使用MyBatis开发DAO，有两种方式
1. 原始DAO开发方法
- 存在重复代码，DAO实现类中不同的sql方法都有通过SqlSessionFactory创建SqlSession，调用SqlSession的数据库操作方法
- 调用SqlSession的数据库操作方法需要指定statement的id，存在硬编码
2. Mapper接口开发方法

**Mapper动态代理方式**
Mapper接口开发需要遵循以下规范：
- mapper接口的类路径和Mapper.xml文件中的namespace相同。
- Mapper接口方法名和Mapper.xml中定义的每个statement的id相同 
- Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
- Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

## **动态Sql**

通过mybatis提供的各种标签方法来实现动态拼接sql

## **if**

    <select id="" class="">
    	select * from table
    	<where>  
    	//where标签会自动去掉第一个and
    		<if test="id!=null and id != ''">
    			//test 标签里面放条件
    				and id=#{}
    		</if>
    		<if test="username != null and username != ''">
    				and username like '%${}%'	
    		</if>
    	</where>
    </select>

## **foreach**

- foreach语句的用途是当向sql语句中传入list时，可以使用foreach解析list

    <if test="ids!=null and ids != ''">
    	<foreach collection="ids" item="id" open="" close="" 
    		separator="">
    		//colleciton表示传入的集合
    		//item表示每次遍历的值
    		//open表示在ids之前的语句
    		//separator表示每次遍历值之间的分隔符
    		//close表示结束遍历的语句  
    			#{id}
    </foreach>
    </if>
    

## sql标签`<sql id="">`

可以将重复的sql语句抽取出来，然后再原来使用的位置上使用,达到重用的目的

`<include refid=""/>`引用

---

# mybatis整合spring

**整合需要做的事**

- **导包**
    - MyBatis包
    - Spring包
    - Spring-MyBatis整合包
    - junit测试包
    - mysql驱动包
- **修改配置文件**
    - 现在所有的对象都交给了Spring管理，所以在Spring的配置文件ApplicationContext文件中配置连接数据库的信息，并删除掉MyBatis中SqlMapConfig中关于配置数据库的信息
- 编写Spring配置文件
    - 数据库连接及配置连接池
    - 事务管理
    - sqlsessionFactory对象，配置到Spring容器中
    - mapper代理对象或者dao实现类配置到Spring容器中
- 编写dao或者mapper文件
- 测试

## 整合之后原生DAO方法

两个文件

1. DAO接口文件
2. DAO实现类文件

**整合后会话工厂归Spring管理**

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

## 三大组件的配置

1. 注解驱动
    - 帮我们配置HandlerMapping和HandlerAdapter
    - 如果有些自定义的HandlerAdapter，则需这样写
        - <mvc:annotation-driven conversion-service="自定义HandlerAdapter类名"><mvc:annotation-driven>
2. 视图解析器
    - 配置页面前缀和后缀
3. 开启注解扫描

## jsp页面配置的一些东西

`${pageContext.request.contextPath}`

这句话的意思是获取端口和项目路径

## 使用Restful的好处

1. 使代码整个看起来规范
2. 不使用restful时，对于get请求来说，可以直接在地址栏通过？传递参数。使用restful后就不能通过地址栏直接传参

## Restful一些理解

/testRESTfulLink/{name}/{time}里面的{name}和{time}表示占位符，通过@PathVariable获取占位符中的参数，默认@PathVariable获取与形参名相同的占位符所表示的值。
     * 如果@PathVariable获取了一个不存在的占位符的值，如@PathVariable(name = "lang") String xxx，这个就会报异常！！！！
     * **在@PathVariable(name = "name")中的name名称可以省略，写成@PathVariable("name")两种都表示获取name占位符中的值。**
     * 形参顺序没有要求，本来就是通过@PathVariable来获取占位符的值。
     * 如果我们的浏览器链接没有和该地址匹配上，也就是说该有占位符的地方浏览器url中没有，那么就会出现404----/testRESTfulLink/{name}/{time}
     * 如：/testRESTfulLink/FireLang/2017-05-06 访问正常
     * /testRESTfulLink/FireLang 出现404
--------------------- 
作者：Silence_Mind 
来源：CSDN 
原文：https://blog.csdn.net/Marvel__Dead/article/details/71835477 
版权声明：本文为博主原创文章，转载请附上博文链接！

## SpringMVC url-pattern的配置

 <!-- SpringMVC前端控制器 -->
  <servlet>
    <servlet-name>SpringMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- contextConfigLocation配置SpringMVC加载的配置文件（配置处理器映射器、适配器等等）
        如果不配置contextConfigLocation，默认加载的是/WEB-INF/servlet名称-servlet.xml(SpringMVC-servlet.xml)
    -->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:Spring/applicationContext*.xml</param-value>
    </init-param>
    <!-- 配置servlet创建的优先级 -->
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>SpringMVC</servlet-name>
    <!-- 
        **第一种：*.action，访问以.action结尾由DispatcherServlet进行解析
        第二种：/，所以访问的地址由DispatcherServlet进行解析，对于静态文件的解析需要配置不让DispatcherServlet进行解析，对于静态文件的解析需要配置，不让DispatcherServlet进行解析。
        使用此种方式可以实现 RESTful 风格的url 。
        第三种：/*，这样配置不对，使用这种配置，最终要转发到一个jsp页面时，仍然会由DispatcherServlet解析jsp地址，不能根据jsp页面找到Handler，会报错。**
    -->
    <url-pattern>/</url-pattern>
--------------------- 
作者：Silence_Mind 
来源：CSDN 
原文：https://blog.csdn.net/Marvel__Dead/article/details/71835477 
版权声明：本文为博主原创文章，转载请附上博文链接！