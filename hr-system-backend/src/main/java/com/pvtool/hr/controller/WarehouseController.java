package com.pvtool.hr.controller;

import com.pvtool.hr.dto.ApiResponse;
import com.pvtool.hr.model.Warehouse;
import com.pvtool.hr.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@CrossOrigin(origins = "http://localhost:3000")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(new ApiResponse(true, "获取成功", warehouses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
                .map(warehouse -> ResponseEntity.ok(new ApiResponse(true, "获取成功", warehouse)))
                .orElse(ResponseEntity.ok(new ApiResponse(false, "档案库房未找到")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createWarehouse(@Valid @RequestBody Warehouse warehouse) {
        Warehouse saved = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.ok(new ApiResponse(true, "创建成功", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateWarehouse(@PathVariable Long id, @Valid @RequestBody Warehouse warehouse) {
        try {
            Warehouse updated = warehouseService.updateWarehouse(id, warehouse);
            return ResponseEntity.ok(new ApiResponse(true, "更新成功", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok(new ApiResponse(true, "删除成功"));
    }
}