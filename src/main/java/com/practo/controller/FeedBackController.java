package com.practo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practo.entity.FeedbackEntity;
import com.practo.services.FeedbackSevice;

@RestController
public class FeedBackController {

	@Autowired
	FeedbackSevice feedbackSevice;

	@PostMapping("/addFeedback")
	public FeedbackEntity addFeedback(@RequestBody FeedbackEntity feedbackEntity) {
		
		return feedbackSevice.addFeedback(feedbackEntity);
	}
	
	
	@GetMapping("/getAllFeedbacks")
	public List<FeedbackEntity> getFeedbacks(){
		
		return feedbackSevice.getAllFeedbacks();
	}
	
	@GetMapping("/getFeedback/{id}")
	public FeedbackEntity getFeedbackById(@PathVariable("id") int id) {
		
		return feedbackSevice.getFeedBackById(id);
	}
	
	@PutMapping("/updateFeedback/{id}")
	public FeedbackEntity updateFeedbackById(@PathVariable("id")int id, @RequestBody FeedbackEntity feedbackEntity) {
		
		return feedbackSevice.updateFeedBack(id, feedbackEntity);
	}
	
	@DeleteMapping("/deleteFeedback/{id}")
	public String deleteFeedback(@PathVariable("id")int id) {
		
		return feedbackSevice.deleteFeedBack(id);
	}
}
