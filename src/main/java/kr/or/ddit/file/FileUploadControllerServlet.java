package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.ViewResolverComposite;

import kr.or.ddit.common.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j

//@MultipartConfig   D.S 이 가져갔다.
@Controller
public class FileUploadControllerServlet{

	@Value("#{appInfo.imagesUrl}")
	private String imagesUrl;
	
	@Value("#{appInfo.imagesUrl}")
	private Resource images;

	private File imageFolder;
	
	@PostConstruct
	public void init() throws IOException {
		imageFolder = images.getFile();
		log.info("이미지 파일 저장 위치-논리 : {}", imagesUrl);
		log.info("이미지 파일 저장 위치-물리 : {}", imageFolder.getCanonicalPath());
	}
	
	@GetMapping("/15/fileUpload.do")
	public String uploadForm() {
		return "15/fileUploadForm";
	}
	
	@PostMapping("/15/fileUpload.do")
	public String doPost(
			FileUploadVO uploadVO
			, RedirectAttributes redirectAttributes
			) throws IllegalStateException, IOException{


			// 업로드가된 파일이 이미지가 맞는지?
			if (!uploadVO.getUploadFile().getContentType().startsWith("image/")) {
				// 이미지 파일이 아님 = 요구하는 컨텐츠 아님 ! 400 에러코드
				throw new BadRequestException("이미지가 아닌 파일 업로드 불가");
			}
			
			log.info("upload command object : {}", uploadVO);
			
			uploadVO.saveTo(imageFolder);
			
			uploadVO.makeFileUrl(imagesUrl);
			
			redirectAttributes.addFlashAttribute("uploadVO", uploadVO);						
			
		
		return "redirect:/15/fileUploadForm.do";

	}
}