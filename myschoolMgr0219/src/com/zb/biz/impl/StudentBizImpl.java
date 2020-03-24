package com.zb.biz.impl;

import java.util.List;

import com.zb.biz.IStudentBiz;
import com.zb.dao.IStudentDao;
import com.zb.dao.impl.StudentDaoImpl;
import com.zb.entity.Student;

public class StudentBizImpl implements IStudentBiz {

	IStudentDao dao = new StudentDaoImpl();
	
	public int addStuInfo(Student stu) {
		// TODO Auto-generated method stub
		return dao.addStuInfo(stu);
	}

	public int delStuInfo(String stuNo) {
		// TODO Auto-generated method stub
		return dao.delStuInfo(stuNo);
	}

	public List<Student> findAllStuInfo(int count,int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAllStuInfo( count,pageSize);
	}

	public Student findStuByNo(String stuNo) {
		// TODO Auto-generated method stub
		return dao.findStuByNo(stuNo);
	}

	public int updateStuInfo(Student stu) {
		// TODO Auto-generated method stub
		return dao.updateStuInfo(stu);
	}

	public int ckStuNo(String stuNo) {
		// TODO Auto-generated method stub
		return dao.ckStuNo(stuNo);
	}

	public int findStuCount() {
		// TODO Auto-generated method stub
		return dao.findStuCount();
	}

	
	
}
