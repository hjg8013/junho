package org.jun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@GetMapping("upload")
    public void uploadForm() {
       System.out.println("uploadForm파일 업로드 화면....");
    }
	
	@GetMapping("uploadAjax")
	public void uploadActionForm(MultipartFile[] uploadFile) {
		System.out.println("uploadActionForm파일 업로드 화면....");
	}
	//uplpad.jsp에서 form태그를 이용해서 파일 업로드 controller
	@PostMapping("uploadAction")
	public void uploadAction(MultipartFile[] uploadFile) {
		//MultipartFile를 사용하면 DTO가 필요없다
		System.out.println("MultipartFile = "+uploadFile[0]);
		System.out.println("getOriginalFilename = "+uploadFile[0].getOriginalFilename()); //파일의 이름 컴활2급.jpg
		System.out.println("getSize = "+uploadFile[0].getSize());				//파일사이즈 197051
		System.out.println("getContentType = "+uploadFile[0].getContentType());		//파일의타입 image/jpeg
		System.out.println("getOriginalFilename = "+uploadFile[1].getOriginalFilename()); //파일의 이름 컴활2급.jpg
		System.out.println("getSize = "+uploadFile[1].getSize());				//파일사이즈 197051
		System.out.println("getContentType = "+uploadFile[1].getContentType());		//파일의타입 image/jpeg
		
		String uploadFolder="C:\\Users\\GreenArt\\git\\upload"; //파일 업로드하고자 하는 위치
		
		for(MultipartFile multiparFile : uploadFile) {
			System.out.println("업로드 파일 이름 = "+multiparFile.getOriginalFilename()); //파일의 이름 컴활2급.jpg
			System.out.println("업로드 파일 크기 = "+multiparFile.getSize());				//파일사이즈 197051
			System.out.println("업로드 파일 형식 = "+multiparFile.getContentType());		//파일의타입 image/jpeg
			//uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장
			File saveFile = new File(uploadFolder,multiparFile.getOriginalFilename()); //파일은 기본생성자가 없다
			//반드시 예외 처리를 해야한다 그냥은 못쓴다
			try{
				multiparFile.transferTo(saveFile);	
			}catch (Exception e) {
				e.printStackTrace();
			}//end try
			
		}//end for
	}
	//uplpadAjax.jsp에서 Ajax태그를 이용해서 파일 업로드 controller
	@PostMapping("uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		//MultipartFile를 사용하면 DTO가 필요없다
		System.out.println("MultipartFile = "+uploadFile[0]);
		System.out.println("getOriginalFilename = "+uploadFile[0].getOriginalFilename()); //파일의 이름 컴활2급.jpg
		System.out.println("getSize = "+uploadFile[0].getSize());				//파일사이즈 197051
		System.out.println("getContentType = "+uploadFile[0].getContentType());		//파일의타입 image/jpeg
		System.out.println("getOriginalFilename = "+uploadFile[1].getOriginalFilename()); //파일의 이름 컴활2급.jpg
		System.out.println("getSize = "+uploadFile[1].getSize());				//파일사이즈 197051
		System.out.println("getContentType = "+uploadFile[1].getContentType());		//파일의타입 image/jpeg
		
		String uploadFolder="C:\\Users\\GreenArt\\git\\upload"; //파일 업로드하고자 하는 위치
		
		for(MultipartFile multiparFile : uploadFile) {
			System.out.println("업로드 파일 이름 = "+multiparFile.getOriginalFilename()); //파일의 이름 컴활2급.jpg
			System.out.println("업로드 파일 크기 = "+multiparFile.getSize());				//파일사이즈 197051
			System.out.println("업로드 파일 형식 = "+multiparFile.getContentType());		//파일의타입 image/jpeg
			//uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장
			File saveFile = new File(uploadFolder,multiparFile.getOriginalFilename()); //파일은 기본생성자가 없다
			//반드시 예외 처리를 해야한다 그냥은 못쓴다
			try{
				multiparFile.transferTo(saveFile);	
			}catch (Exception e) {
				e.printStackTrace();
			}//end try
			
		}//end for
	}
}
