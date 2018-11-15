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