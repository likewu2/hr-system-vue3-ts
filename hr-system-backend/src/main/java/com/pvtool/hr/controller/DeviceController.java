package com.pvtool.hr.controller;

import com.pvtool.hr.dto.ApiResponse;
import com.pvtool.hr.model.Device;
import com.pvtool.hr.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", devices));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id)
                .map(device -> ResponseEntity.ok(new ApiResponse(true, "获取成功", device)))
                .orElse(ResponseEntity.ok(new ApiResponse(false, "设备未找到")));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<ApiResponse> getDevicesByWarehouseId(@PathVariable Long warehouseId) {
        List<Device> devices = deviceService.getDevicesByWarehouseId(warehouseId);
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", devices));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDevice(@Valid @RequestBody Device device) {
        Device saved = deviceService.createDevice(device);
        return ResponseEntity.ok(new ApiResponse(true, "创建成功", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDevice(@PathVariable Long id, @Valid @RequestBody Device device) {
        try {
            Device updated = deviceService.updateDevice(id, device);
            return ResponseEntity.ok(new ApiResponse(true, "更新成功", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}/control")
    public ResponseEntity<ApiResponse> controlDevice(@PathVariable Long id, @RequestBody String controlStatus) {
        try {
            Device controlled = deviceService.controlDevice(id, controlStatus);
            return ResponseEntity.ok(new ApiResponse(true, "控制成功", controlled));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.ok(new ApiResponse(true, "删除成功"));
    }

    @GetMapping("/status/online")
    public ResponseEntity<ApiResponse> getOnlineDevices() {
        List<Device> devices = deviceService.getOnlineDevices();
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", devices));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse> getDevicesByStatus(@PathVariable String status) {
        List<Device> devices = deviceService.getDevicesByStatus(status);
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", devices));
    }
}