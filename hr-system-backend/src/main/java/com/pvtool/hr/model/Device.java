package com.pvtool.hr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "设备名称不能为空")
    private String name;

    @NotBlank(message = "设备类型不能为空")
    private String type; // 恒温恒湿一体机、空气净化、空调等

    private String model;
    private String serialNumber;
    private String status; // 运行中、待机、故障
    private boolean isOnline; // 是否在线
    private boolean isControlled; // 是否可远程控制
    private String controlStatus; // 开启、关闭、自动

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private LocalDateTime lastUpdateTime;
    private LocalDateTime installationDate;

    // 构造函数
    public Device() {}

    public Device(String name, String type, Warehouse warehouse) {
        this.name = name;
        this.type = type;
        this.warehouse = warehouse;
        this.status = "待机";
        this.isOnline = false;
        this.isControlled = true;
        this.controlStatus = "关闭";
        this.lastUpdateTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean online) { isOnline = online; }

    public boolean isControlled() { return isControlled; }
    public void setControlled(boolean controlled) { isControlled = controlled; }

    public String getControlStatus() { return controlStatus; }
    public void setControlStatus(String controlStatus) { this.controlStatus = controlStatus; }

    public Warehouse getWarehouse() { return warehouse; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }

    public LocalDateTime getLastUpdateTime() { return lastUpdateTime; }
    public void setLastUpdateTime(LocalDateTime lastUpdateTime) { this.lastUpdateTime = lastUpdateTime; }

    public LocalDateTime getInstallationDate() { return installationDate; }
    public void setInstallationDate(LocalDateTime installationDate) { this.installationDate = installationDate; }
}