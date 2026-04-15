package com.pvtool.hr.service;

import com.pvtool.hr.model.Warehouse;
import com.pvtool.hr.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        Optional<Warehouse> existing = warehouseRepository.findById(id);
        if (existing.isPresent()) {
            Warehouse updated = existing.get();
            updated.setName(warehouse.getName());
            updated.setLocation(warehouse.getLocation());
            updated.setDescription(warehouse.getDescription());
            updated.setCapacity(warehouse.getCapacity());
            return warehouseRepository.save(updated);
        } else {
            throw new RuntimeException("档案库房未找到");
        }
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}