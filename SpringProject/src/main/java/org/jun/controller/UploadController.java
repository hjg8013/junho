package org.jun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

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
		
		
		//폴더생성                               (기존폴더,        현재폴더)를 결합
		File uploadPath = new File(uploadFolder,getFolder());
		System.out.println(uploadPath);
		
		//현재 만들려고 하는 폴더가 없으면
		if(uploadPath.exists()==false) {
			//폴더생성 메이크디렉토리라는뜻이다 이이름으로 폴더를 만들다
			uploadPath.mkdirs();
		}
		
		
		
		for(MultipartFile multiparFile : uploadFile) {
			System.out.println("업로드 파일 이름 = "+multiparFile.getOriginalFilename()); //파일의 이름 컴활2급.jpg
			System.out.println("업로드 파일 크기 = "+multiparFile.getSize());				//파일사이즈 197051
			System.out.println("업로드 파일 형식 = "+multiparFile.getContentType());		//파일의타입 image/jpeg
			
			String uploadFileName=multiparFile.getOriginalFilename();
			//중복이 되지않는 임의의 문자열을 생성
			UUID uuid = UUID.randomUUID();
			
			//UUID + "_"+ getOriginalFilename()의 조합으로 파일명을 uploadFileName에 저장
			uploadFileName=uuid.toString()+"_"+uploadFileName;
			
			//uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장
			File saveFile = new File(uploadFolder,uploadFileName); //파일은 기본생성자가 없다
			
			//반드시 예외 처리를 해야한다 그냥은 못쓴다
			try{
				//saveFile변수에 저장되어 있는 폴더명으로 파일을 보내라
				multiparFile.transferTo(saveFile);
				//이미지 파일이면
				if(checkImage(saveFile)) {
					//썸내일 파일을 생성하기 전에 썸네일 파일 이름을 추출
					FileOutputStream thumnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					//썸내일 파일 생성함
					Thumbnailator.createThumbnail(multiparFile.getInputStream(),thumnail,100,100);
					//썸내일 종료(메모리 공간 함수)
					thumnail.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}//end try
			
		}//end for
	}
	//년/월/일 폴더의 생성
	private String getFolder() {
		Date date = new Date();
		System.out.println("date= "+date); //현재날짜와 시간이 나온다
		// tue jan 18 09:34:09 KST 2022 -> 2022-01-18으로 변경
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		//             2022-01-18 ->  2022\01\18
		return str.replace("-", File.separator);
	}
	//썸네일 이미지 생성을 할 것인지 안할것인지에 대해 판단하는 매소드
	private boolean checkImage(File file) {
		//무조건 예외처리를 해야한다
		try {
			String contentTypeStr = Files.probeContentType(file.toPath());//파일의 타입을 알아내는 probeContentType매소드 호출하여 사용
			//그 파일의 타입이 image이면 true, 그렇지 않으면  false
			return contentTypeStr.startsWith("image");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
