package com.test.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.web.bean.BoardBean;
import com.test.web.bean.MemberBean;
import com.test.web.dao.BoardDao;
import com.test.web.dao.MemberDao;

@Controller
public class RESTController {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private BoardDao boardDao;
	
	//DB ---> Bean --->JSON
	@RequestMapping("/rest/selectMember")
	@ResponseBody
	public Map<String, Object> selectMember(){
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		try {
			MemberBean mBean = memberDao.selectMember();
			
			resMap.put("Result", "ok");
			resMap.put("memberBean", mBean);
		}catch(Exception e) {
			e.printStackTrace();
			resMap.put("result", "fail");
		}
		return resMap;
	}
	
	
	
	//DB ---> Bean --->JSON
		@RequestMapping("/rest/selectMemberList")
		@ResponseBody
		public Map<String, Object> selectMemberList(){
			
			Map<String, Object> resMap = new HashMap<String, Object>();
			
			try {
				List<MemberBean> list = memberDao.selectMemberList();
				
				resMap.put("Result", "ok");
				resMap.put("memberBean", list);
			}catch(Exception e) {
				e.printStackTrace();
				resMap.put("result", "fail");
			}
			return resMap;
		}

		
		//DB ---> Bean --->JSON 임의의값을 넣어 주는 것
				@RequestMapping("/rest/insertMember")
				@ResponseBody
				public Map<String, Object> insertMember(MemberBean mBean){

					Map<String, Object> resMap = new HashMap<String, Object>();

					try {
						int resVal = memberDao.insertMember(mBean);
						if(resVal > 0) {
							resMap.put("result",  "ok");
						}
					}catch(DuplicateKeyException dke) {
						resMap.put("resultMsg",  "이미존재하는 USER_ID입니다");
					}
					catch(Exception e) {
						e.printStackTrace();
						resMap.put("resultMsg", e.getMessage());
					}
					return resMap;
				}

				
				
				//DB ---> Bean --->JSON 임의의값을 넣어 주는 것
				@RequestMapping("/rest/updateMember")
				@ResponseBody
				public Map<String, Object> updateMember(MemberBean mBean){

					Map<String, Object> resMap = new HashMap<String, Object>();

					try {
						int resVal = memberDao.updateMember(mBean);
						if(resVal > 0) {
							resMap.put("result",  "ok");
							resMap.put("resultMsg",  "업데이트에 성공 하였습니다.");
						}
						else {
							resMap.put("resultMsg",  "존재하지 않는 USER_ID입니다.");
						}
					}
					catch(Exception e) {
						e.printStackTrace();
						resMap.put("resultMsg", e.getMessage());
					}
					return resMap;
				}



				//DB ---> Bean --->JSON 임의의값을 넣어 주는 것
				@RequestMapping("/rest/deleteMember")
				@ResponseBody
				public Map<String, Object> deleteMember(MemberBean mBean){

					Map<String, Object> resMap = new HashMap<String, Object>();

					try {
						int resVal = memberDao.deleteMember(mBean);
						if(resVal > 0) {
							resMap.put("result",  "ok");
							resMap.put("resultMsg",  "삭제에 성공 하였습니다.");
						}
						else {
							resMap.put("resultMsg", "존재하지 않는 USER_ID입니다.");
						}
					}
					catch(Exception e) {
						e.printStackTrace();
						resMap.put("resultMsg", e.getMessage());
					}
					return resMap;
				}
				

				//DB ---> Bean --->JSON
				@RequestMapping("/rest/selectBoard")
				@ResponseBody
				public Map<String, Object> selectBoard(BoardBean mBean){

					Map<String, Object> resMap = new HashMap<String, Object>();

					try {
						BoardBean pBean = boardDao.selectBoard(mBean);

						resMap.put("Result", "ok");
						resMap.put("boardBean", pBean);
					}catch(Exception e) {
						e.printStackTrace();
						resMap.put("result", "fail");
					}
					return resMap;
				}

		
		//DB ---> Bean --->JSON
		@RequestMapping("/rest/selectBoardList")
		@ResponseBody
		public Map<String, Object> selectBoardList(){

			Map<String, Object> resMap = new HashMap<String, Object>();

			try {
				List<BoardBean> list = boardDao.selectBoardList();

				resMap.put("Result", "ok");
				resMap.put("boardBean", list);
			}catch(Exception e) {
				e.printStackTrace();
				resMap.put("result", "fail");
			}
			return resMap;
		}
		
		//DB ---> Bean --->JSON 임의의값을 넣어 주는 것
				@RequestMapping("/rest/insertBoard")
				@ResponseBody
				public Map<String, Object> insertBoard(BoardBean mBean){

					Map<String, Object> resMap = new HashMap<String, Object>();

					try {
						int resVal = boardDao.insertBoard(mBean);
						if(resVal > 0) {
							resMap.put("result",  "ok");
						}
					}catch(DuplicateKeyException dke) {
						resMap.put("resultMsg",  "이미존재하는 USER_ID입니다");
					}
					catch(Exception e) {
						e.printStackTrace();
						resMap.put("resultMsg", e.getMessage());
					}
					return resMap;
				}

				
				
				//DB ---> Bean --->JSON 임의의값을 넣어 주는 것
				@RequestMapping("/rest/updateBoard")
				@ResponseBody
				public Map<String, Object> updateBoard(BoardBean mBean){

					Map<String, Object> resMap = new HashMap<String, Object>();

					try {
						int resVal = boardDao.updateBoard(mBean);
						if(resVal > 0) {
							resMap.put("result",  "ok");
							resMap.put("resultMsg",  "업데이트에 성공 하였습니다.");
						}
						else {
							resMap.put("resultMsg",  "존재하지 않는 USER_ID입니다.");
						}
					}
					catch(Exception e) {
						e.printStackTrace();
						resMap.put("resultMsg", e.getMessage());
					}
					return resMap;
				}



				//DB ---> Bean --->JSON 임의의값을 넣어 주는 것
				@RequestMapping("/rest/deleteBoard")
				@ResponseBody
				public Map<String, Object> deleteBoard(BoardBean mBean){

					Map<String, Object> resMap = new HashMap<String, Object>();

					try {
						int resVal = boardDao.deleteBoard(mBean);
						if(resVal > 0) {
							resMap.put("result",  "ok");
							resMap.put("resultMsg",  "삭제에 성공 하였습니다.");
						}
						else {
							resMap.put("resultMsg", "존재하지 않는 USER_ID입니다.");
						}
					}
					catch(Exception e) {
						e.printStackTrace();
						resMap.put("resultMsg", e.getMessage());
					}
					return resMap;
				}
		
		


}
