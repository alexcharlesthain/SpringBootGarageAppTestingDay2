package com.qa.thain.alex.garage.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String vType;
	
	@NotNull
	@Lob
	private String vMake;
	
	@NotNull
	@Lob
	private String vModel;
	
	@NotNull
	@Lob
	private String vRegistrationNumber;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private GarageAppModel vehicle;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;		
	}
	public String getvType() {
		return vType;
	}
	public void setvType(String vType) {
		this.vType = vType;		
	}
	public String getvMake() {
		return vMake;
	}
	public void setvMake(String vMake) {
		this.vMake = vMake;	
	}
	public String getvModel() {
		return vModel;
	}
	public void setvModel(String vModel) {
		this.vModel = vModel;
	}
	public String getvRegistrationNumber() {
		return vRegistrationNumber;
	}
	public void setvRegistrationNumber(String vRegistrationNumber) {
		this.vRegistrationNumber = vRegistrationNumber;
	}
	public GarageAppModel getVehicle() {
		return vehicle;
	}	
	public void setVehicle(GarageAppModel Vehicle) {
		this.vehicle = Vehicle;
	}
}