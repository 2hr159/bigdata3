package kr.mem.controller;

import java.util.HashMap;

import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberContentController;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;
import kr.mem.pojo.MemberUpdateController;

public class HandlerMapping{
	private	HashMap<String , Controller>  mappings;
	
	//key, valu
	//객체생성
	public HandlerMapping() {
		mappings=new HashMap<String, Controller>();
		initMap();
		
	}
//---------------------중요---------
	private void initMap() {
		        // 핸들러 매핑
				// /list.do---->memberlistController
				// /insert.do--->memberinsertcontroller
				// /insertForm.do--->memberInsertFormController
				// /delete.do --->memberdeletecontroller
		
//		-------------------------작업------------------------------
		try {
		mappings.put("/list.do", new MemberListController());
		mappings.put("/insert.do", new MemberInsertController());
		mappings.put("/insertForm.do", new MemberInsertFormController());
		mappings.put("/delete.do", new MemberDeleteController());
		mappings.put("/content.do", new MemberContentController());
		mappings.put("/update.do", new MemberUpdateController());
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Controller getController(String key) {
		return mappings.get(key);
	}
	

}
