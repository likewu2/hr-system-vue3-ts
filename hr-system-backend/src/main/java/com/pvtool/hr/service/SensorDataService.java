package com.pvtool.hr.service;

import com.pvtool.hr.model.SensorData;
import com.pvtool.hr.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }

    public Optional<SensorData> getSensorDataById(Long id) {
        return sensorDataRepository.findById(id);
    }

    public List<SensorData> getSensorDataByWarehouseId(Long warehouseId) {
        return sensorDataRepository.findByWarehouseId(warehouseId);
    }

    public List<SensorData> getSensorDataByWarehouseIdAndType(Long warehouseId, String sensorType) {
        return sensorDataRepository.findByWarehouseIdAndSensorType(warehouseId, sensorType);
    }

    public SensorData createSensorData(SensorData sensorData) {
        sensorData.setTimestamp(LocalDateTime.now());
        // 简单的状态判断逻辑
        if (sensorData.getTemperature() != null && (sensorData.getTemperature() < 10 || sensorData.getTemperature() > 30)) {
            sensorData.setStatus("异常");
        } else if (sensorData.getHumidity() != null && (sensorData.getHumidity() < 30 || sensorData.getHumidity() > 80)) {
            sensorData.setStatus("异常");
        } else {
            sensorData.setStatus("正常");
        }
        return sensorDataRepository.save(sensorData);
    }

    public List<SensorData> getSensorDataByTimeRange(Long warehouseId, LocalDateTime start, LocalDateTime end) {
        return sensorDataRepository.findByWarehouseIdAndTimestampBetween(warehouseId, start, end);
    }

    public SensorData getLatestSensorData(Long warehouseId, String sensorType) {
        return sensorDataRepository.findTopByWarehouseIdAndSensorTypeOrderByTimestampDesc(warehouseId, sensorType);
    }

    public void deleteSensorData(Long id) {
        sensorDataRepository.deleteById(id);
    }
}