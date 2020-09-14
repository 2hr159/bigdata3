package kr.mem.pojo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemverVO;

public class MemberListController implements Controller{
	   @Override
	   public String requestHandle(HttpServletRequest request, 
	                             HttpServletResponse response) throws ServletException, IOException {
	        MemberDAO dao=new MemberDAO();
	        ArrayList<MemverVO> list=dao.memberAllList();
	        request.setAttribute("list", list);
//	        /WEB-INF/views/
	       return "member/MemberList.jsp";
	   }
}