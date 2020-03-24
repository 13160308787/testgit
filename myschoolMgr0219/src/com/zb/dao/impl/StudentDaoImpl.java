package com.zb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.zb.dao.BaseDao;
import com.zb.dao.IStudentDao;
import com.zb.entity.Student;

public class StudentDaoImpl implements IStudentDao {

	BaseDao baseDao = new BaseDao();
	
	public int addStuInfo(Student stu) {
		int count = 0;
		
		String sql = "insert into student (studentNo,loginpwd,studentName,sex,gradeId,borndate,email) values (?,'123456',?,?,?,?,?)";
		
		Object[] params = {stu.getStudentNo(),stu.getStudentName(),stu.getSex(),stu.getGradeId(),
				stu.getBorndate(),stu.getEmail()
		};
		
		count = baseDao.executeUpdate(sql, params);
		
		return count;
	}

	public int delStuInfo(String stuNo) {
		
		int count = 0;
		
		String sql = "delete from student where studentNo = ?";
		
		Object[] params = {stuNo};
		
		count = baseDao.executeUpdate(sql, params);
		
		return count;
	}

	public List<Student> findAllStuInfo(int count,int pageSize) {
		List<Student> stuList = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = baseDao.getConnection();
			String sql = "select * from student ORDER BY borndate limit ?,? ";
			Object[]params={(count-1)*pageSize,pageSize};
			pstm = baseDao.createPreparedStatement(conn, sql,params);
			rs = pstm.executeQuery();
			while(rs.next()){
				Student stu = new Student();
				stu.setStudentNo(rs.getString("studentNo"));
				stu.setStudentName(rs.getString("studentName"));
				stu.setSex(rs.getString("sex"));
				stu.setGradeId(rs.getInt("gradeId"));
				stu.setBorndate(rs.getString("borndate"));
				stu.setEmail(rs.getString("email"));
				stuList.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			baseDao.closeAll(rs, pstm, conn);
		}
		return stuList;
	}

	public Student findStuByNo(String stuNo) {
		Student stu = new Student();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = baseDao.getConnection();
			String sql = "select * from student where studentNo = ?";
			Object[] params = {stuNo};
			pstm = baseDao.createPreparedStatement(conn, sql,params);
			rs = pstm.executeQuery();
			if(rs.next()){
				stu.setStudentNo(rs.getString("studentNo"));
				stu.setStudentName(rs.getString("studentName"));
				stu.setSex(rs.getString("sex"));
				stu.setGradeId(rs.getInt("gradeId"));
				stu.setBorndate(rs.getString("borndate"));
				stu.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			baseDao.closeAll(rs, pstm, conn);
		}
		
		
		return stu;
	}

	public int updateStuInfo(Student stu) {
		
		int count = 0;
		
		String sql = "update student set studentName = ?,sex = ?,gradeId = ?,borndate = ?,email = ? where studentNo = ?";
		
		Object[] params = {stu.getStudentName(),stu.getSex(),stu.getGradeId(),stu.getBorndate(),stu.getEmail(),stu.getStudentNo()};
		
		count = baseDao.executeUpdate(sql, params);
		
		return count;
	}

	
	public int ckStuNo(String stuNo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = baseDao.getConnection();
			String sql = "select count(1) from student where studentNo = ?";
			Object[] params = {stuNo};
			pstm = baseDao.createPreparedStatement(conn, sql,params);
			rs = pstm.executeQuery();
			if(rs.next()){
				//接收到返回的单行单列（如果这个新增或者修改的学号已经存在则返回1 如果不存在返回0）
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			baseDao.closeAll(rs, pstm, conn);
		}
		
		
		return count;
	}

	public int findStuCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = baseDao.getConnection();
			String sql = "select count(studentNo) from student ";
			
			pstm = baseDao.createPreparedStatement(conn, sql,null);
			rs = pstm.executeQuery();
			if(rs.next()){
				//接收到返回的单行单列（如果这个新增或者修改的学号已经存在则返回1 如果不存在返回0）
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			baseDao.closeAll(rs, pstm, conn);
		}
		return count;
	}

	
	
	
}
