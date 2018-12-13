package com.qa.thain.alex.garage.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.qa.thain.alex.garage.app.repository.*;
import com.qa.thain.alex.garage.app.exception.ResourceNotFoundException;
import com.qa.thain.alex.garage.app.model.*;

@RestController
public class VehicleController {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private GarageAppRepository garageAppRepository;
	
	@GetMapping("/vehicle/{vehicleId)/orders")
	public Page<Order> getAllOrdersByVehicleId(@PathVariable (value = "vehicle_id") int vehicleId, Pageable pageable) {
		return orderRepository.findByVehicleId(vehicleId, pageable);	
	}
	
	@PostMapping("/vehicle /{vehicleId}/orders")
	public Order createComment(@PathVariable (value = "vehicleId") int vehicleId, @Valid @RequestBody Order order) {
		return garageAppRepository.findById(vehicleId).map(GarageAppModel -> {
			order.setVehicle(GarageAppModel);
			return orderRepository.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", order));
	}
	
	@PutMapping("/vehicle/{vehicleId}/orders/{orderId}")
	public Order updateOrder(@PathVariable (value = "vehicleId") int vehicleId, @PathVariable (value = "orderId") Long orderId, @Valid @RequestBody Order orderRequest) {
		if(!garageAppRepository.existsById(vehicleId)) {
			throw new ResourceNotFoundException("Vehicle", "id", orderRequest);
		}
		
		return orderRepository.findById(orderId).map(order -> {
			order.setvMake(orderRequest.getvMake());
			return orderRepository.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId", "id", orderRequest));
	}
	
	@DeleteMapping("/vehicle/{vehicleId}/orders/{ordersId}")
	public ResponseEntity<?> deleteComment(@PathVariable (value = "vehicleId") int vehicleId, @PathVariable (value = "orderId") Long orderId) {
		if(!garageAppRepository.existsById(vehicleId)) {
			throw new ResourceNotFoundException("Vehicle", "id", vehicleId);
		}
		
		return orderRepository.findById((long) vehicleId).map(order -> {
			orderRepository.delete(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId " , orderId.toString() , null));	
	}
	
	
}

	

