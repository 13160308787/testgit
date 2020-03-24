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
 * mysql�����ݿ�school��ʵ����
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
			//��������
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			System.out.println("�������ش���");
		}
	}

	/**
	 * ��ȡ���ݿ����Ӷ���
	 * @return ���ݿ����Ӷ���
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
	 * ��ȡ���ݿ��������
	 * @param conn ���ݿ����Ӷ���
	 * @param sql SQL����ַ���
	 * @param prame SQL���Ĳ���
	 * @return ���ݿ��������
	 * @throws SQLException
	 */
	public PreparedStatement createPreparedStatement(Connection conn,String sql, Object... prame) throws SQLException {
		PreparedStatement pram = conn.prepareStatement(sql);
		//��� ==0��˵����ǰ��ɾ�ķ���û�в�������ֱ�ӷ���ִ���߶���
		//�Ժ�����ʱ�򣬷��Ƕ���ĳ��������ж� ������ == nullһ��д����ǰ��
		if (prame == null || prame.length == 0)
			return pram;
		//ѭ��װ�ز���
		for (int i = 0; i < prame.length; i++) {
			pram.setObject(i + 1, prame[i]);
		}
		return pram;
	}
	/**
	 * ��ɾ��ͨ�÷���
	 * @param sql SQL����ַ���
	 * @param prame SQL���Ĳ���
	 * @return ��Ӱ�������
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
	 * �ر��������ݿ�����Ķ���
	 * @param rs
	 * @param pram
	 * @param conn
	 */
	public void closeAll(ResultSet rs, PreparedStatement pstm, Connection conn) {
		try {
			//if��Ҫ����try�����ֹif����������Ĵ��붼��ִ��
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//����finally������Ϊ�˷�ֹǰ�����������Ҳ��ִ�е�
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
