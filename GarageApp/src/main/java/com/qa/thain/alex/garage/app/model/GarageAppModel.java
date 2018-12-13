package com.qa.thain.alex.garage.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "vehicle")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "creationDate", "lastModified" }, allowGetters = true)
public class GarageAppModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String vMake;
	
	@NotBlank
	private String vModel;
	
	@NotBlank
	private String vRegistrationNumber;
	
	@NotBlank
	private String vType;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationDate;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModified; 
	
	public GarageAppModel(String Type, String Make, String Model, String RegistrationNumber, int i) {
		this.vType = Type;
		this.vMake = Make;
		this.vModel = Model;
		this.vRegistrationNumber = RegistrationNumber;
		this.id = i;
	}	 
	public GarageAppModel() {
		this.vType = "Car";
		this.vMake = "Ford";
		this.vModel = "Focus";
		this.vRegistrationNumber = "SE12SEE";
		this.id = 14;
	}
	public int getId() {
		return this.id;
	}	
	public void setId(int id)	{
		this.id = id;
	}
	public String getvMake() {
		return this.vMake;
	}
	public void setvMake(String vMake) {
		this.vMake = vMake;
	}	
	public String getvModel() {
		return this.vModel;
	}		
	public void setvModel(String vModel) {	
		this.vModel = vModel;
	}	
	public String getvRegistrationNumber() {
		return this.vRegistrationNumber;
	}	
	public void setvRegistrationNumber(String vRegistrationNumber) {
		this.vRegistrationNumber = vRegistrationNumber;
	}
	public String getvType() {
		return this.vType;
	}
	public void setvType(String vType) {
		this.vType = vType;
	}
	public Date getCreationDate() {
		return this.creationDate;
	}	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	
	public Date getLastModified() {	
		return this.lastModified;
	}
	public void setLastModified(Date lastModified) {	
		this.lastModified = lastModified;
	}
}