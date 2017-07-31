package com.test.web;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.web.bean.BoardBean;
import com.test.web.bean.MemberBean;
import com.test.web.bean.TestBean;
import com.test.web.dao.BoardDao;
import com.test.web.dao.MemberDao;

@Controller
public class IndexController {
	
	public static final String UPLOAD_IMG_PATH = "C:\\DEV\\WORKS\\Eclipse\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\SpringWeb2\\img";
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping("/hello")
	public String helloWorld(String id, String pw) {
		
		System.out.println("id: " + id + ", pw: " + pw);
		
		BoardBean bBean = boardDao.selectBoard(new BoardBean());
		System.out.println("title : " + bBean.getTitle());
		System.out.println("content: " + bBean.getContent());
		
		return "hello";
	}
	
	@RequestMapping("test/hello")
	public String test2(TestBean tBean) {
		
		System.out.println("id: " + tBean.getId() + ", pw: " + tBean.getPw() + ", addr:" +tBean.getAddr() );
		
		MemberBean memberBean = memberDao.selectMember();
		System.out.println("USER_ID: " + memberBean.getUserId());
		System.out.println("USER_PW: " + memberBean.getUserPw());
		System.out.println("NAME: " + memberBean.getName());
		System.out.println("HP: " + memberBean.getHp());
		
		return "hello";
	}
	

	
	
	@RequestMapping("/rest/imgUp")
	   @ResponseBody
	   public Map<String, Object> imgUpload(MultipartFile imageFile) {
	      
	      Map<String, Object> resMap = new HashMap<String, Object>();
	      resMap.put("result", "fail");
	      resMap.put("resultMsg", "이미지 업로드에 실패 하였습니다.");
	      
	      if(imageFile.isEmpty()) return resMap;
	      
	      String fileName = null;
	      try {
	         
	         // 첨부이미지 임시 폴더 확인 및 생성
	         String tempPath = UPLOAD_IMG_PATH;
	         System.out.println(tempPath);
	         File tempFolder = new File(tempPath);
	         if (!tempFolder.exists()) {
	            tempFolder.mkdirs();
	         }

	         // 사용자 업로드 파일 네임
	         // 임시 폴더 이므로 파일명을 UUID로 생성
	         String fileExt = imageFile.getOriginalFilename().substring(
	               imageFile.getOriginalFilename().lastIndexOf(".") + 1);
	         fileName = UUID.randomUUID().toString() + "." + fileExt;

	         byte[] bytes = imageFile.getBytes();
	         BufferedOutputStream buffStream = new BufferedOutputStream( new FileOutputStream( new File(tempPath + "/" + fileName) ) );
	         buffStream.write(bytes);
	         buffStream.close();
	         File copyFile =  new File(tempPath + "/" + fileName);
	         BufferedImage bi = ImageIO.read(copyFile);
	         System.out.println("hei--"+bi.getHeight()+"______wid--"+bi.getWidth());

	         resMap.put("result", "ok");
	         resMap.put("result", "이미지 업로드에 성공 하였습니다.");
	         
	         resMap.put("filename", imageFile.getOriginalFilename());
	         resMap.put("filesize", bytes.length);
	         resMap.put("imagealign", "C");
	         resMap.put("uploadedPath", "/");
	         resMap.put("imageurl", "/img/" + fileName);
	         resMap.put("imgWidth", bi.getWidth());
	         resMap.put("imgHeight", bi.getHeight());
	         
	         System.out.println(resMap.get("imageurl"));
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return resMap;
	   }	

}





















