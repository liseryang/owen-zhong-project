package com.oz.springmvc.test.controller;

import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="file_upload")
public class FileUploadController {
	@RequestMapping(method=RequestMethod.GET)
	public String fileUpload(){
		return "file_upload";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String doUpload(@RequestParam("file") MultipartFile file,Model model,HttpServletRequest request){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(request.getRealPath("/")+"/"+file.getOriginalFilename());
			fos.write(file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("name",file.getOriginalFilename());
		return "file_view";
	}
	
}
