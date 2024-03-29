package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private FileUploadService fileuploadService;
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImages();
		model.addAttribute("list", list);
		return "gallery/index";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(
			@RequestParam("file")MultipartFile multipartFile, Model model, GalleryVo vo) {
		String url = fileuploadService.restore(multipartFile);
		galleryService.saveImage(vo, url);
		model.addAttribute("url", url);
		return "redirect:/gallery";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
//		System.out.println(no);
		galleryService.removeImages(no);
		return "redirect:/gallery";
	}
}
