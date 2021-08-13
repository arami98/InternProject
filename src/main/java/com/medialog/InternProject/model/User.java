package com.medialog.InternProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NEW_USER_TB")
public class User {

	@Id
	@Column(nullable = false, length = 45)
	private String loginId;

	@Column(nullable = false, length = 45)
	private String loginPwd;

	@Column(nullable = false, length = 45)
	private String name;

	@Column(nullable = false, length = 20)
	private String ssn;

	@Column(nullable = false, length = 20)
	private String tel;

	@Enumerated(EnumType.STRING)
	private Auth auth;

	@Column(length = 20)
	private String agentRegNo;
	
	@Column(nullable = false, columnDefinition = "json")
	@Convert(attributeName = "addr", converter = JsonToMapConverter.class)
	private String addr;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDt;

}