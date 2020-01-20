package com.pd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pd.repository.StazaRepository;

import model.Staza;

@Controller
@RequestMapping("/myImage")
public class ImageController {

//	@Resource(name="categoryService")
//	private CategoryService categoryService;
	
//	@Resource(name="itemService")
//	private ItemService itemService;
	@Autowired
	StazaRepository srepo;
	
	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(String idStaza, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException{
	
		int id = Integer.parseInt(idStaza);
		Staza s = srepo.findById(id).get();       
	    response.setContentType("image/jpeg, image/jpg, image/png");
	    response.getOutputStream().write(s.getMapa());	
	
	    response.getOutputStream().close();
	}
}






