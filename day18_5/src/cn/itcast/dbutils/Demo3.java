package cn.itcast.dbutils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.itcast.jdbc.JdbcUtils;

public class Demo3 {
	@Test
	//增加功能
	public void fun1() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());		//在创建qr对象时给出connection连接（使用连接池获取连接）
		String sql = "insert into t_stu values(?,?,?,?)";	//给出sql模板
		Object[] params = {1002, "liSi", 88, "female"};		//给出参数
		
		qr.update(sql, params);
	}
	
	@Test
	//查询功能
	public void fun2() throws SQLException {
		// 创建QueryRunner，需要提供数据库连接池对象
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		// 给出sql模板
		String sql = "select * from t_stu where sid=?";
		// 给出参数
		Object[] params = {1001};
		
//		ResultSetHandler<Stu> rsh = new ResultSetHandler<Stu>() {
//
//			@Override
//			public Stu handle(ResultSet rs) throws SQLException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
		// 执行query()方法，需要给出结果集处理器，即ResultSetHandler的实现类对象
		// 我们给的是BeanHandler，它实现了ResultSetHandler
		// 它需要一个类型，然后它会把rs中的数据封装到指定类型的javabean对象中，然后返回javabean
		Stu stu = qr.query(sql, new BeanHandler<Stu>(Stu.class), params);
		System.out.println(stu);
	}
	
	/**
	 * BeanListHandler的应用，它是多行处理器
	 * 每行对象一个Stu对象！
	 * @throws Exception
	 */
	@Test
	public void fun3() throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from t_stu";
		List<Stu> stuList = qr.query(sql, new BeanListHandler<Stu>(Stu.class));
		
		System.out.println(stuList);
	}
	
	/**
	 * MapHandler的应用，它是单行处理器，把一行转换成一个Map对象
	 * @throws SQLException 
	 */
	@Test
	public void fun4() throws SQLException  {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from t_stu where sid=?";
		Object[] params = {1001};
		Map map = qr.query(sql, new MapHandler(), params);
		
		System.out.println(map);
	}
	
	/**
	 * MapListHandler，它是多行处理器，把每行都转换成一个Map，即List<Map>
	 * @throws SQLException
	 */
	@Test
	public void fun5() throws SQLException  {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from t_stu";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		
		System.out.println(mapList);
	}
	
	/**
	 * ScalarHandler，它是单行单列时使用，最为合适！
	 * @throws SQLException
	 */
	@Test
	public void fun6() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select count(*) from t_stu";
		/*
		 * Integer、Long、BigInteger
		 */
		Number cnt = (Number)qr.query(sql, new ScalarHandler());
		
		long c = cnt.longValue();
		System.out.println(c);
	}
}
