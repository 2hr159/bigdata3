package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemverVO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;

public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		// 1. 어떤요청인지 파악하는 작업 -> *.do
		String reqUrl = request.getRequestURI();
		System.out.println(reqUrl);
		String ctxPath = request.getContextPath();
		System.out.println(ctxPath);
		// 클라이언트가 요청한 명령
		String command = reqUrl.substring(ctxPath.length());
		System.out.println(command);

		// 각 요청에 따라 처리하기(분기작업)
		Controller controller = null;
		MemberDAO dao = new MemberDAO();
		String nextView = null;
		HandlerMapping mappings = new HandlerMapping();
        controller=mappings.getController(command);
        nextView=controller.requestHandle(request, response);
		// 핸들러 매핑
		// /list.do---->memberlistController
		// /insert.do--->memberinsertcontroller
		// /insertForm.do--->memberInsertFormController
		// /delete.do --->memberdeletecontroller

//		if (command.equals("/list.do")) {
//			controller = new MemberListController();
//			nextView = controller.requestHandle(request, response);
//		} else if (command.equals("/insert.do")) {
//
//			controller = new MemberInsertController();
//			nextView = controller.requestHandle(request, response);
//
//		} else if (command.equals("/insertForm.do")) {
//			controller = new MemberInsertFormController();
//			nextView = controller.requestHandle(request, response);
//
//		} else if (command.equals("/delete.do")) {
//			controller = new MemberDeleteController();
//			nextView = controller.requestHandle(request, response);
//
//		}

		// view 페이지로 연동하는 부분-------------------------------------------
		if (nextView != null) {
			if (nextView.indexOf("redirect:") != -1) {
				String[] sp = nextView.split(":"); // sp[0]:sp[1]
				response.sendRedirect(sp[1]); // :O
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + nextView);
				rd.forward(request, response);
			}
		}
	}
}
