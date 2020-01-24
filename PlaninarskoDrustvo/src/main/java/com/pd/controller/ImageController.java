package com.pd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pd.repository.SlikaRepository;
import com.pd.repository.StazaRepository;

import model.Slika;
import model.Staza;

@Controller
@RequestMapping("/myImage")
public class ImageController {

	@Autowired
	StazaRepository srepo;
	@Autowired
	SlikaRepository slikarepo;
	
	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(String idStaza, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException{
		int id = Integer.parseInt(idStaza);
		Staza s = srepo.findById(id).get();       
	    response.setContentType("image/jpeg, image/jpg, image/png");
	    response.getOutputStream().write(s.getMapa());	
	
	    response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/imageDisplayIMG", method = RequestMethod.GET)
	public void showImageIMG(String idSlika, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException{
		int id = Integer.parseInt(idSlika);
		Slika s = slikarepo.findById(id).get();
		response.setContentType("image/jpeg, image/jpg, image/png");
	    response.getOutputStream().write(s.getSlika());	
	
	    response.getOutputStream().close();
	}
}






