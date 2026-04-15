package com.pvtool.hr.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private String sensorType; // 空气质量、温湿度、烟雾等
    private String sensorName;
    private Double temperature; // 温度
    private Double humidity; // 湿度
    private Double pm25; // PM2.5
    private Double pm10; // PM10
    private Double tvoc; // 总挥发性有机物
    private Double co2; // 二氧化碳
    private Double formaldehyde; // 甲醛
    private String status; // 正常、异常、警告

    private LocalDateTime timestamp;

    // 构造函数
    public SensorData() {}

    public SensorData(Warehouse warehouse, String sensorType, String sensorName) {
        this.warehouse = warehouse;
        this.sensorType = sensorType;
        this.sensorName = sensorName;
        this.status = "正常";
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Warehouse getWarehouse() { return warehouse; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }

    public String getSensorType() { return sensorType; }
    public void setSensorType(String sensorType) { this.sensorType = sensorType; }

    public String getSensorName() { return sensorName; }
    public void setSensorName(String sensorName) { this.sensorName = sensorName; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }

    public Double getPm25() { return pm25; }
    public void setPm25(Double pm25) { this.pm25 = pm25; }

    public Double getPm10() { return pm10; }
    public void setPm10(Double pm10) { this.pm10 = pm10; }

    public Double getTvoc() { return tvoc; }
    public void setTvoc(Double tvoc) { this.tvoc = tvoc; }

    public Double getCo2() { return co2; }
    public void setCo2(Double co2) { this.co2 = co2; }

    public Double getFormaldehyde() { return formaldehyde; }
    public void setFormaldehyde(Double formaldehyde) { this.formaldehyde = formaldehyde; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}