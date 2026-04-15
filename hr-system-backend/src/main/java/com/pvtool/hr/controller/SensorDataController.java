package com.pvtool.hr.controller;

import com.pvtool.hr.dto.ApiResponse;
import com.pvtool.hr.model.SensorData;
import com.pvtool.hr.service.SensorDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sensor-data")
@CrossOrigin(origins = "http://localhost:3000")
public class SensorDataController {

    @Autowired
    private SensorDataService sensorDataService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllSensorData() {
        List<SensorData> sensorDataList = sensorDataService.getAllSensorData();
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", sensorDataList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getSensorDataById(@PathVariable Long id) {
        return sensorDataService.getSensorDataById(id)
                .map(sensorData -> ResponseEntity.ok(new ApiResponse(true, "获取成功", sensorData)))
                .orElse(ResponseEntity.ok(new ApiResponse(false, "传感器数据未找到")));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<ApiResponse> getSensorDataByWarehouseId(@PathVariable Long warehouseId) {
        List<SensorData> sensorDataList = sensorDataService.getSensorDataByWarehouseId(warehouseId);
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", sensorDataList));
    }

    @GetMapping("/warehouse/{warehouseId}/type/{sensorType}")
    public ResponseEntity<ApiResponse> getSensorDataByWarehouseIdAndType(@PathVariable Long warehouseId, @PathVariable String sensorType) {
        List<SensorData> sensorDataList = sensorDataService.getSensorDataByWarehouseIdAndType(warehouseId, sensorType);
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", sensorDataList));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createSensorData(@Valid @RequestBody SensorData sensorData) {
        SensorData saved = sensorDataService.createSensorData(sensorData);
        return ResponseEntity.ok(new ApiResponse(true, "创建成功", saved));
    }

    @GetMapping("/warehouse/{warehouseId}/time-range")
    public ResponseEntity<ApiResponse> getSensorDataByTimeRange(
            @PathVariable Long warehouseId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<SensorData> sensorDataList = sensorDataService.getSensorDataByTimeRange(warehouseId, start, end);
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", sensorDataList));
    }

    @GetMapping("/warehouse/{warehouseId}/latest/{sensorType}")
    public ResponseEntity<ApiResponse> getLatestSensorData(@PathVariable Long warehouseId, @PathVariable String sensorType) {
        SensorData sensorData = sensorDataService.getLatestSensorData(warehouseId, sensorType);
        if (sensorData != null) {
            return ResponseEntity.ok(new ApiResponse(true, "获取成功", sensorData));
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "传感器数据未找到"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSensorData(@PathVariable Long id) {
        sensorDataService.deleteSensorData(id);
        return ResponseEntity.ok(new ApiResponse(true, "删除成功"));
    }
}