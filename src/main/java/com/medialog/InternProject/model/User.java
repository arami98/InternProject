package com.medialog.InternProject.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbNewUser")
public class User {
	
	@Id
	private String loginId;
	
	private String loginPwd;
	
	private String name;
	
	private String ssn;
	
	private String tel;
	
	private String auth;
	
	private String agentRegNo;
	
	private String addr;
	
	@CreationTimestamp
	private Timestamp regDt;
	
	@CreationTimestamp
	private Timestamp updateDt;
	
}
