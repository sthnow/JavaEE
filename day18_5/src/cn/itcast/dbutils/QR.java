package cn.itcast.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class QR<T> {		//声明是一个泛型类
	private DataSource dataSource;
	
	public QR(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public QR() {
		super();
	}

	/**
	 * 做insert、update、delete
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();//通过连接池得到连接对象

			pstmt = con.prepareStatement(sql);//使用sql创建pstmt
			initParams(pstmt, params);//设置参数
			
			return pstmt.executeUpdate();//执行
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}

	/**
	 *给参数赋值
	 * @param pstmt
	 * @param params
	 * @throws SQLException
	 */
	private void initParams(PreparedStatement pstmt, Object... params) 		//Object... params   这是可变数组的写法，类型时object
			throws SQLException {
		for(int i = 0; i < params.length; i++) {
			pstmt.setObject(i+1, params[i]);
		}
	}


	/**
	 * 实现查询操作
	 * @param sql
	 * @param rh
	 * @param params
	 * @return
	 */
	public T query(String sql, RsHandler<T> rh, Object... params) {		//泛型方法
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();//通过连接池得到连接对象

			pstmt = con.prepareStatement(sql);//使用sql创建pstmt
			initParams(pstmt, params);//设置参数
			
			rs = pstmt.executeQuery();//执行
			
			return  rh.handle(rs);
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(SQLException e1) {}
		}
	}
}

/**
 * 功能:用来把结果集转换成需要的类型！
 * 默认时包访问权限
  */
interface RsHandler<T> {		//声明是一个泛型接口
	public T handle(ResultSet rs) throws SQLException;		//将rs转换成需要的类型
}
