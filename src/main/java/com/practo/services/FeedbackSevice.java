package com.practo.services;

import java.util.List;

import com.practo.entity.FeedbackEntity;

public interface FeedbackSevice {
	
	FeedbackEntity addFeedback(FeedbackEntity feedbackEntity);
	
	List<FeedbackEntity> getAllFeedbacks();
	
	FeedbackEntity getFeedBackById(int id);
	
	FeedbackEntity updateFeedBack(int id, FeedbackEntity feedbackEntity);
	
	String deleteFeedBack(int id);

}
