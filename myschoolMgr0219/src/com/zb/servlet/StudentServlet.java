package com.zb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zb.biz.IStudentBiz;
import com.zb.biz.impl.StudentBizImpl;
import com.zb.entity.Student;

public class StudentServlet extends HttpServlet{

	IStudentBiz biz = new StudentBizImpl();
	
	PrintWriter out = null;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		out = response.getWriter();
		String option = request.getParameter("option");
		if(option == null){
			this.findAllStuInfo(request, response);
		}else if(option.equals("add")){
			this.addStuInfo(request, response);
		}else if(option.equals("del")){
			this.delStuInfo(request, response);
		}else if(option.equals("findById")){
			this.findStuByNo(request, response);
		}else if(option.equals("update")){
			this.updateStuInfo(request, response);
		}else if(option.equals("delAjax")){
			this.delStuInfoAjax(request, response);
		}else if(option.equals("ckStuNo")){
			this.ckStuNo(request, response);
		}
	}
	
	protected void addStuInfo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		Student stu = new Student();
		stu.setStudentNo(request.getParameter("studentNo"));
		stu.setStudentName(request.getParameter("studentName"));
		stu.setSex(request.getParameter("sex"));
		stu.setGradeId(Integer.parseInt(request.getParameter("gradeId")));
		stu.setBorndate(request.getParameter("borndate"));
		stu.setEmail(request.getParameter("email"));
		int count = biz.addStuInfo(stu);
		if(count > 0){
			out.print("<script>alert('�����ɹ�������');window.location.href='StudentServlet.do';</script>");
		}else{
			out.print("<script>alert('����ʧ�ܣ�����');window.location.href='StudentServlet.do';</script>");
		}
	}
	protected void delStuInfo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stuNo = request.getParameter("stuNo");
		int count = biz.delStuInfo(stuNo);
		if(count > 0){
			out.print("<script>alert('ɾ���ɹ�������');window.location.href='StudentServlet.do';</script>");
		}else{
			out.print("<script>alert('ɾ��ʧ�ܣ�����');window.location.href='studentServlet.do';</script>");
		}
	}
	
	protected void delStuInfoAjax(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stuNo = request.getParameter("stuNo");
		int count = biz.delStuInfo(stuNo);
		out.print(count);
	}
	
	protected void findAllStuInfo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		//������
		int totalSize=biz.findStuCount();
		//ÿҳ��������ϢInteger.parseInt(request.getParameter("pageSize"));
		int pageSize=10;
//		if(request.getParameter("pageSize")==null){
//			pageSize=10;
//		}else{
//			pageSize=Integer.parseInt(request.getParameter("pageSize"));
//		}
	    int totalCount =  totalSize % pageSize == 0?totalSize / pageSize:totalSize / pageSize+1;
		//��ǰҳ��
		int count;
		if(request.getParameter("count")==null){
			count=1;
		}else{
			count=Integer.parseInt(request.getParameter("count"));
			if(count<1){
				count=1;
			}else if(count>totalCount){
				count=totalCount;
			}
			
		}
		
		List<Student> stuList = biz.findAllStuInfo( count, pageSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("count", count);
		request.setAttribute("stuList", stuList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void findStuByNo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stuNo = request.getParameter("stuNo");
		//����ѧ�Ų�ѯ����ѧ������
		Student stu = biz.findStuByNo(stuNo);
		System.out.println(stu.getSex());
		request.setAttribute("stu", stu);
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}
	protected void updateStuInfo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		Student stu = new Student();
		stu.setStudentNo(request.getParameter("studentNo"));
		stu.setStudentName(request.getParameter("studentName"));
		stu.setSex(request.getParameter("sex"));
		stu.setGradeId(Integer.parseInt(request.getParameter("gradeId")));
		stu.setBorndate(request.getParameter("borndate"));
		stu.setEmail(request.getParameter("email"));
		int count = biz.updateStuInfo(stu);
		if(count > 0){
			out.print("<script>alert('�޸ĳɹ�������');window.location.href='StudentServlet.do';</script>");
		}else{
			out.print("<script>alert('�޸�ʧ�ܣ�����');window.location.href='studentServlet.do';</script>");
		}
	}
	
	protected void ckStuNo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		//1��ȡҪ��֤��ѧ��
		String stuNo = request.getParameter("stuNo");
		//2����Ҫ��֤��ѧ�Ŵ��ݵ����Ǳ�д�õ���֤�����У�����ѧ���Ƿ���� 0�������ڣ�����ʹ�ã� 1����
		int count = biz.ckStuNo(stuNo);
		
		out.print(count);
		
	}
	
	
}
