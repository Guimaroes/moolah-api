package com.moolah.finance;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.moolah.type.Type;
import com.moolah.user.User;

import lombok.Data;

@Data
@Entity
@Table(name = "finances")
public class Finance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal value;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private Type type;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
