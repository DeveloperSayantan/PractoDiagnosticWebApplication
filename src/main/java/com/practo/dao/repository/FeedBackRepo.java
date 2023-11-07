package com.practo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practo.entity.FeedbackEntity;

@Repository
public interface FeedBackRepo extends JpaRepository<FeedbackEntity, Integer>{

}
