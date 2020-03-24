package com.zb.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * mysql的数据库school的实现类
 * @author lenovo
 *
 */
public class BaseDao {
	private static final String Driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/myschool";
	private static final String uid = "root";
	private static final String pwd = "2846";
	static {
		try {
			//加载驱动
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载错误");
		}
	}

	/**
	 * 获取数据库连接对象
	 * @return 数据库连接对象
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public Connection getConnection() throws SQLException, NamingException {
//		Context context =new InitialContext();
//		
//		DataSource dataSource =(DataSource)context.lookup("java:comp/env/myschool");
//		Connection conn =dataSource.getConnection();
//		return  conn;
		return DriverManager.getConnection(url, uid, pwd);
	}
	/**
	 * 获取数据库操作对象
	 * @param conn 数据库连接对象
	 * @param sql SQL语句字符串
	 * @param prame SQL语句的参数
	 * @return 数据库操作对象
	 * @throws SQLException
	 */
	public PreparedStatement createPreparedStatement(Connection conn,String sql, Object... prame) throws SQLException {
		PreparedStatement pram = conn.prepareStatement(sql);
		//如果 ==0则说明当前增删改方法没有参数，则直接返回执行者对象
		//以后工作的时候，凡是对于某个对象的判断 对象名 == null一定写在最前面
		if (prame == null || prame.length == 0)
			return pram;
		//循环装载参数
		for (int i = 0; i < prame.length; i++) {
			pram.setObject(i + 1, prame[i]);
		}
		return pram;
	}
	/**
	 * 增删改通用方法
	 * @param sql SQL语句字符串
	 * @param prame SQL语句的参数
	 * @return 受影响的行数
	 */
	public int executeUpdate(String sql, Object...params) {
		Connection conn=null;
		PreparedStatement pram=null;
		int count=-1;
		try {
			conn = this.getConnection();
			pram = this.createPreparedStatement(conn, sql, params);
			count = pram.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(null, pram, conn);
		}
		return count;
	}
	/**
	 * 关闭所有数据库产生的对象
	 * @param rs
	 * @param pram
	 * @param conn
	 */
	public void closeAll(ResultSet rs, PreparedStatement pstm, Connection conn) {
		try {
			//if块要放在try块里，防止if不成立后面的代码都不执行
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//放在finally块里是为了防止前面出错了这里也能执行到
			try {
				if(pstm!=null){
					pstm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
