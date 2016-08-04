package com.omni.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omni.core.UserInfoBusiness;
import com.omni.data.vo.SunInfoVO;
import com.omni.data.vo.UserInfoVO;

public class OmniMainServlet extends HttpServlet {
	private static final long serialVersionUID = 4302056826373533040L;
	private String message;
	
	public void init() throws ServletException {
		// Do required initialization
		message = "Welcome to Omni Web Services";
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");
		
		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageName = request.getParameter("pageName");
		String errors = null;
		if ("student".equalsIgnoreCase(pageName)) {
			errors = studentRegistarion(request);
		} else if ("subject".equalsIgnoreCase(pageName)) {
			errors = subjectRegistarion(request);
		} else {
			errors = "InValid Page";
		}
		
		
		// Set response content type
		response.setContentType("text/html");
		
		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		if (errors == null || errors.equalsIgnoreCase("1")) {
			out.println("<h1>Successfully Signed in</h1>");
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<h1>");
			buffer.append(errors);
			buffer.append("<h1>");
			out.println(buffer.toString());
		}
	}
	
	private String studentRegistarion(HttpServletRequest request) {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String gender = request.getParameter("gender");		
		String eMail = request.getParameter("email");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipCode");
		String homePhone = request.getParameter("homePhone");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("passowrd");
		
		
		
		UserInfoVO user = new UserInfoVO();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setEmail(eMail);
		user.setAddress(address);
		user.setCity(city);
		user.setState(state);
		user.setZipCode(Integer.parseInt(zipcode));
		user.setHomePhone(Integer.parseInt(homePhone));
		user.setUserName(userName);
		user.setPassowrd(passWord);
		
		UserInfoBusiness userBuss = new UserInfoBusiness();
		String errors = userBuss.updateUserInfo(user);
		
		return errors;
	}

	private String subjectRegistarion(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String passowrd = request.getParameter("passowrd");
		String Semester = request.getParameter("semester");		
		String Branch = request.getParameter("branch");
		String Num_subjects = request.getParameter("num_subjects");
		String Sub1 = request.getParameter("sub1");
		String Sub2 = request.getParameter("sub2");
		String Sub3 = request.getParameter("sub3");
		String Sub4 = request.getParameter("sub4");
		//String userName = request.getParameter("username");
		String Sub5 = request.getParameter("sub5");
		
		SunInfoVO user = new SunInfoVO();
		user.setUserName(userName);
		user.setPassowrd(passowrd);
		user.setSemester(Semester);
		user.setBranch(Branch);
		user.setNum_subjects(Num_subjects);
		user.setSub1(Sub1);
		user.setSub2(Sub2);
		user.setSub3(Sub3);
		user.setSub4(Sub4);
		user.setSub5(Sub5);
		
		UserInfoBusiness userBuss = new UserInfoBusiness();
		String errors = userBuss.updateSubInfo(user);
		

		
		
		
		return errors;
	}

	public void destroy() {
	      // do nothing.
	}
}
