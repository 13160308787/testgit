package com.zb.dao;

import java.util.List;

import com.zb.entity.Student;

public interface IStudentDao {
	/**
	 * 查询所有
	 * @return 查询到的结果集
	 */
	public List<Student> findAllStuInfo(int count,int pageSize);

	/**
	 * 新增学生信息
	 * @param stu 从页面传递过来的要新增的学生对象
	 * @return 受影响的行数
	 */
	public int addStuInfo(Student stu);
	/**
	 * 删除学生信息
	 * @param stuNo 要删除的学员学号
	 * @return 受影响的行数
	 */
	public int delStuInfo(String stuNo);
	/**
	 * 修改学生信息
	 * @param stu 从页面传递过来的要修改的学生对象
	 * @return 受影响的行数
	 */
	public int updateStuInfo(Student stu);
	/**
	 * 根据学号查询某个学生对象
	 * @param stuNo 要查询的学生学号
	 * @return 查询到的学生对象
	 */
	public Student findStuByNo(String stuNo);
	/**
	 * 验证学号是否存在
	 * @param stuNo 要验证的学号
	 * @return 0 表示不存在 1标识存在
	 */
	public int ckStuNo(String stuNo);
	
	public int findStuCount();
}
