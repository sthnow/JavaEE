package cn.itcast.demo3;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.Class.forName;

public class JdbcUtils2 {

    private static Properties props = null;

    static {
        try {
            InputStream in = JdbcUtils.class.getClassLoader()
                    .getResourceAsStream("dbconfig.properties");

            props = new Properties();

            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //加载驱动类
        try{
            //通过给props一个键名称搜寻对应的值，然后通过class加载驱动类
            Class.forName(props.getProperty("driverClassName"));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //得到connection对象，获取连接
    //因为是类的方法，所以在调用时直接使用  类名.方法名   调用即可，不用先新建一个对象在调用方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password"));
    }
}
