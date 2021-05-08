package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.AnswerDAO;
import com.project.entity.Answer;

@RestController

public class AnswerController {

	
    @Autowired
	AnswerDAO answerDAO;
	
	
	@GetMapping("/answers/{id}")
	public Answer getAnswerbyID(@PathVariable int id)
	{
		
	    System.out.println("In function");
		
//		return "Hello World";
		return answerDAO.getAnswerByAnswerID(id);
		
		
	}
	
	
	
	
}
