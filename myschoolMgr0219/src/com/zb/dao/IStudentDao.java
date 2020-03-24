package com.zb.dao;

import java.util.List;

import com.zb.entity.Student;

public interface IStudentDao {
	/**
	 * ��ѯ����
	 * @return ��ѯ���Ľ����
	 */
	public List<Student> findAllStuInfo(int count,int pageSize);

	/**
	 * ����ѧ����Ϣ
	 * @param stu ��ҳ�洫�ݹ�����Ҫ������ѧ������
	 * @return ��Ӱ�������
	 */
	public int addStuInfo(Student stu);
	/**
	 * ɾ��ѧ����Ϣ
	 * @param stuNo Ҫɾ����ѧԱѧ��
	 * @return ��Ӱ�������
	 */
	public int delStuInfo(String stuNo);
	/**
	 * �޸�ѧ����Ϣ
	 * @param stu ��ҳ�洫�ݹ�����Ҫ�޸ĵ�ѧ������
	 * @return ��Ӱ�������
	 */
	public int updateStuInfo(Student stu);
	/**
	 * ����ѧ�Ų�ѯĳ��ѧ������
	 * @param stuNo Ҫ��ѯ��ѧ��ѧ��
	 * @return ��ѯ����ѧ������
	 */
	public Student findStuByNo(String stuNo);
	/**
	 * ��֤ѧ���Ƿ����
	 * @param stuNo Ҫ��֤��ѧ��
	 * @return 0 ��ʾ������ 1��ʶ����
	 */
	public int ckStuNo(String stuNo);
	
	public int findStuCount();
}
