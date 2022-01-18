package org.jun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.jun.domain.AttachFileDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	@PostMapping(value="uploadAjaxAction",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ArrayList<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		// AttachFileDTO에 저장되는 값이 여러파일에 대한 값이면 배열로 처리가 되어야 하므로ArrayList타입이 되어야 함
		ArrayList<AttachFileDTO> list = new ArrayList<>();
		
		
		
		//MultipartFile를 사용하면 DTO가 필요없다
		System.out.println("MultipartFile = "+uploadFile[0]);
		System.out.println("getOriginalFilename = "+uploadFile[0].getOriginalFilename()); //파일의 이름 컴활2급.jpg
		System.out.println("getSize = "+uploadFile[0].getSize());				//파일사이즈 197051
		System.out.println("getContentType = "+uploadFile[0].getContentType());		//파일의타입 image/jpeg
		System.out.println("getOriginalFilename = "+uploadFile[1].getOriginalFilename()); //파일의 이름 컴활2급.jpg
		System.out.println("getSize = "+uploadFile[1].getSize());				//파일사이즈 197051
		System.out.println("getContentType = "+uploadFile[1].getContentType());		//파일의타입 image/jpeg
		
		String uploadFolder="C:\\Users\\GreenArt\\git\\upload"; //파일 업로드하고자 하는 위치
		

		
		String uplodFolderPath = getFolder();
		//폴더생성                               (기존폴더,        현재폴더)를 결합
		File uploadPath = new File(uploadFolder,uplodFolderPath);
		System.out.println("uploadPath= "+uploadPath);
		
		
		
		//현재 만들려고 하는 폴더가 없으면
		if(uploadPath.exists()==false) {
			//폴더생성 메이크디렉토리라는뜻이다 이이름으로 폴더를 만들다
			uploadPath.mkdirs();
		}
		
		
		
		for(MultipartFile multiparFile : uploadFile) {
			
			//UploadController에 있는 uploadAjaxAction에서 AttachFileDTO을 사용해서 값을 저장해야 되는데
			//이럴경우 UploadController에 AttachFileDTO가 없으면 사용 할 수가 없습니다
			//그래서 UploadController에 AttachFileDTO 포함을 시켜서 사용하여 값을 저장함
			AttachFileDTO attachdto = new AttachFileDTO();
			
			System.out.println("업로드 파일 이름 = "+multiparFile.getOriginalFilename()); //파일의 이름 컴활2급.jpg
			System.out.println("업로드 파일 크기 = "+multiparFile.getSize());				//파일사이즈 197051
			System.out.println("업로드 파일 형식 = "+multiparFile.getContentType());		//파일의타입 image/jpeg
			
			String uploadFileName=multiparFile.getOriginalFilename();
			
			attachdto.setUploadPath(uploadFolder);
			
			//실제 파일명 uploadFileName을 AttachFileDTO클래스(attachdto)에 fileName에 저장(setFileName)
			attachdto.setFilename(uploadFileName);
			
			
			//중복이 되지않는 임의의 문자열을 생성
			UUID uuid = UUID.randomUUID();
			
			//UUID + "_"+ getOriginalFilename()의 조합으로 파일명을 uploadFileName에 저장
			uploadFileName=uuid.toString()+"_"+uploadFileName;
			
			//uploadFolder에 저장되어 있는 경로로 실제 파일명으로 저장
			File saveFile = new File(uploadPath,uploadFileName); //파일은 기본생성자가 없다
			
			
			
			//반드시 예외 처리를 해야한다 그냥은 못쓴다
			try{
				//saveFile변수에 저장되어 있는 폴더명으로 파일을 보내라
				multiparFile.transferTo(saveFile);
				
				//실제 업로드 경로 (uplodFolderPath) 를 AttachFileDTO클래스(attachdto) 에 uplodPath에 저장(setFileName)
				attachdto.setUploadPath(uplodFolderPath);
				//uuid값(UUID) 를 AttachFileDTO클래스(attachdto) 에 uploadPath에 저장(uplodFolderPath)
				attachdto.setUuid(uuid.toString());
				
				
				//이미지 파일이면
				if(checkImage(saveFile)) {
					//FileType값을 AttachFileDTO클래스(attachdto) 에 uploadPath에 저장(uplodImage)
					attachdto.setImage(true);
					
					//썸내일 파일을 생성하기 전에 썸네일 파일 이름을 추출
					FileOutputStream thumnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					//썸내일 파일 생성함
					Thumbnailator.createThumbnail(multiparFile.getInputStream(),thumnail,100,100);
					//썸내일 종료(메모리 공간 함수)
					thumnail.close();
				}
				//list에 DTO값을 들어오게함
				list.add(attachdto);
				
			}catch (Exception e) {
				e.printStackTrace();
			}//end try
		}//end for
		
		// 통신상태가 정상적이면 list에 저장되어 있는 값을 웹브라우저에 보내라   값을 uploadAjax.js에 있는 ajax에 success로 보내라
		return  new ResponseEntity<>(list,HttpStatus.OK);
		
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
