package cn.itcast.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.itcast.jdbc.JdbcUtils;

/*
查询
 */
public class Demo2 {
	@Test
	public void fun1() {
//		Stu s = new Stu(1001, "zhangSan", 99, "male");
//		addStu(s);
		
		Stu s = load(1001);
		//stu这个javabean类有toString方法
		//在调用print或者println方法时会自动调用对象的toString方法，所以javabean中要有toString方法
		System.out.println(s);
	}
	
	public void addStu(Stu stu) {
		QR qr = new QR(JdbcUtils.getDataSource());//创建对象时给出连接池，然后再qr中使用连接池创建对象
		String sql = "insert into t_stu values(?,?,?,?)";//给出sql模板
		// 给出参数
		Object[] params = {stu.getSid(), stu.getSname(), stu.getAge(), stu.getGender()};
		// 调用update执行增、删、改！
		qr.update(sql, params);
	}
	
	public Stu load(int sid) {
		QR qr = new QR(JdbcUtils.getDataSource());//创建对象时给出连接池
		String sql = "select * from t_stu where sid=?";//给出sql模板
		Object[] params = {sid};

		/**
		 * 内部类
		 */
		RsHandler<Stu> rh = new RsHandler<>() {
			//实现了一个接口，将结果集转换成需要的类型
			//传入的参数是结果集，返回值类型是stu
			public Stu handle(ResultSet rs) throws SQLException {		//实现接口的handle方法
				if(!rs.next()) return null;
				Stu stu = new Stu();
				stu.setSid(rs.getInt("sid"));
				stu.setSname(rs.getString("sname"));
				stu.setAge(rs.getInt("age"));
				stu.setGender(rs.getString("gender"));
				return stu;
			}
		};

		return (Stu) qr.query(sql, rh, params);
	}
}
