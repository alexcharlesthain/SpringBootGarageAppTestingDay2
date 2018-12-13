package com.qa.thain.alex.garage.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.qa.thain.alex.garage.app.exception.ResourceNotFoundException;
import com.qa.thain.alex.garage.app.model.*;
import com.qa.thain.alex.garage.app.repository.GarageAppRepository;

@RestController
@RequestMapping("/api")
public class GarageAppController {

	@Autowired
	GarageAppRepository myRepository;

	//method to create a vehicle
	@PostMapping("/vehicle")
	public GarageAppModel createVehicle(@Valid @RequestBody GarageAppModel mSDM) {
		return myRepository.save(mSDM);		
	}
	
	//Method to get a vehicle by id
	@GetMapping("/vehicle/{id}")
	public GarageAppModel getVehiclebyID(@PathVariable(value="id")int id) {
		return myRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("GarageAppModel", "id", id));
	}
	
	//Method to get all vehicles
	@GetMapping("/vehicle")
	public List<GarageAppModel> getAllVehicle() {
		return myRepository.findAll(); 
	}
	
	//Method to update a vehicle
	@PutMapping("/vehicle/{id}")
	public GarageAppModel updateVehicle(@PathVariable(value = "id") int id,
			@Valid @RequestBody GarageAppModel vehicleDetails){
		
		GarageAppModel mSDM = myRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle","id", id));
		
		mSDM.setvMake(vehicleDetails.getvMake());
		mSDM.setvModel(vehicleDetails.getvModel());
		mSDM.setvRegistrationNumber(vehicleDetails.getvRegistrationNumber());
		mSDM.setvType(vehicleDetails.getvType());
		
		GarageAppModel updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	//Method to remove a vehicle
	@DeleteMapping("/vehicle/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable(value="id")int id) {
		GarageAppModel mSDM = myRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vehicle","id", id));
		
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	
	}	
}
