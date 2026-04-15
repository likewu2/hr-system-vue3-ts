package com.pvtool.hr.service;

import com.pvtool.hr.model.Device;
import com.pvtool.hr.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public List<Device> getDevicesByWarehouseId(Long warehouseId) {
        return deviceRepository.findByWarehouseId(warehouseId);
    }

    public Device createDevice(Device device) {
        device.setLastUpdateTime(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    public Device updateDevice(Long id, Device device) {
        Optional<Device> existing = deviceRepository.findById(id);
        if (existing.isPresent()) {
            Device updated = existing.get();
            updated.setName(device.getName());
            updated.setType(device.getType());
            updated.setModel(device.getModel());
            updated.setSerialNumber(device.getSerialNumber());
            updated.setStatus(device.getStatus());
            updated.setOnline(device.isOnline());
            updated.setControlled(device.isControlled());
            updated.setControlStatus(device.getControlStatus());
            updated.setLastUpdateTime(LocalDateTime.now());
            return deviceRepository.save(updated);
        } else {
            throw new RuntimeException("设备未找到");
        }
    }

    public Device controlDevice(Long id, String controlStatus) {
        Optional<Device> existing = deviceRepository.findById(id);
        if (existing.isPresent()) {
            Device device = existing.get();
            if (device.isControlled()) {
                device.setControlStatus(controlStatus);
                device.setStatus("运行中".equals(controlStatus) ? "运行中" : "待机");
                device.setLastUpdateTime(LocalDateTime.now());
                return deviceRepository.save(device);
            } else {
                throw new RuntimeException("设备不可远程控制");
            }
        } else {
            throw new RuntimeException("设备未找到");
        }
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public List<Device> getOnlineDevices() {
        return deviceRepository.findByIsOnline(true);
    }

    public List<Device> getDevicesByStatus(String status) {
        return deviceRepository.findByStatus(status);
    }
}