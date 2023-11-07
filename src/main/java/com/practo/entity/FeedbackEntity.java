package com.practo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "feedback")
public class FeedbackEntity {

	@Id
	@Column(name = "feed_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String msg;
	private int rating;
	
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private CustomerEntity customerEntity;
	
}
