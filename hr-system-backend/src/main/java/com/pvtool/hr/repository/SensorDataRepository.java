package com.pvtool.hr.repository;

import com.pvtool.hr.model.SensorData;
import com.pvtool.hr.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findByWarehouse(Warehouse warehouse);
    List<SensorData> findByWarehouseId(Long warehouseId);
    List<SensorData> findBySensorType(String sensorType);
    List<SensorData> findByWarehouseIdAndSensorType(Long warehouseId, String sensorType);
    List<SensorData> findByWarehouseIdAndTimestampBetween(Long warehouseId, LocalDateTime start, LocalDateTime end);
    SensorData findTopByWarehouseIdAndSensorTypeOrderByTimestampDesc(Long warehouseId, String sensorType);
}