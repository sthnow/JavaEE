import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class demo1 {

    @Test
    //自己创建对象
    public void run1(){
        //创建连接池，先使用Spring提供的内置连接池
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //设置数据库的四大参数
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_day03?serverTimezone=UTC&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        //创建模板类，给模板类里面传一个数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //使用模板完成数据库操作
        //第一个问号表示排序方式，默认id使用增序
        jdbcTemplate.update("insert into t_account values (null,?,?)", "测试", 10000);

    }
}
