package com.practo.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.practo.dao.repository.FeedBackRepo;
import com.practo.entity.FeedbackEntity;
import com.practo.services.FeedbackSevice;

@Service
public class FeedBackServiceImpl implements FeedbackSevice{

	@Autowired
	FeedBackRepo feedBackRepo;
	
	@Override
	public FeedbackEntity addFeedback(@RequestBody FeedbackEntity feedbackEntity) {
		
		return feedBackRepo.save(feedbackEntity);
	}

	@Override
	public List<FeedbackEntity> getAllFeedbacks() {
		
		return feedBackRepo.findAll();
	}

	@Override
	public FeedbackEntity getFeedBackById(int id) {
		
		Optional<FeedbackEntity> actualFeedback = feedBackRepo.findById(id);
		
		if(actualFeedback.isPresent()) {
			
			return actualFeedback.get();
			
			}
		
		return null;
	}

	@Override
	public FeedbackEntity updateFeedBack(int id, FeedbackEntity feedbackEntity) {

		Optional<FeedbackEntity> actualFeedback = feedBackRepo.findById(id);
		
		if(actualFeedback.isPresent()) {
			
			FeedbackEntity originalFeedback = actualFeedback.get();
			
			 if (Objects.nonNull(feedbackEntity.getMsg()) && !"".equalsIgnoreCase(feedbackEntity.getMsg())) {
				 originalFeedback.setMsg(feedbackEntity.getMsg());
	            }
			 
			 if (Objects.nonNull(feedbackEntity.getRating())) {
				 originalFeedback.setRating(feedbackEntity.getRating());
	            }
			
			 return feedBackRepo.save(feedbackEntity);
		}
		
		return null;
	}

	@Override
	public String deleteFeedBack(int id) {

		Optional<FeedbackEntity> actualFeedback = feedBackRepo.findById(id);
		
		if(actualFeedback.isPresent()) {
			
			feedBackRepo.deleteById(id);
			
			return "Feedback deleted";
		}
		return "No Such Feedback found";
	}

}
