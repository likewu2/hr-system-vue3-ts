package com.pvtool.hr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "库房名称不能为空")
    private String name;

    private String location;
    private String description;
    private Integer capacity;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Device> devices;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<SensorData> sensorDataList;

    // 构造函数
    public Warehouse() {}

    public Warehouse(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public List<Device> getDevices() { return devices; }
    public void setDevices(List<Device> devices) { this.devices = devices; }

    public List<SensorData> getSensorDataList() { return sensorDataList; }
    public void setSensorDataList(List<SensorData> sensorDataList) { this.sensorDataList = sensorDataList; }
}