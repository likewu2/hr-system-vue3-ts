package com.pvtool.hr.repository;

import com.pvtool.hr.model.Device;
import com.pvtool.hr.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByWarehouse(Warehouse warehouse);
    List<Device> findByWarehouseId(Long warehouseId);
    List<Device> findByStatus(String status);
    List<Device> findByIsOnline(boolean isOnline);
}