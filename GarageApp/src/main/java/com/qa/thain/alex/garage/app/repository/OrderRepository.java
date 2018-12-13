package com.qa.thain.alex.garage.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.thain.alex.garage.app.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Page<Order> findByVehicleId(int id, Pageable pageable);
}
