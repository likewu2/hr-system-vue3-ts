package com.pvtool.hr.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "approvals")
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String type;
    private String description;
    private String status;
    private Long requesterId;
    private Date requestedAt;
    private Long approverId;
    private Date approvedAt;
    
    // 构造函数
    public Approval() {
    }
    
    public Approval(String type, String description, String status, Long requesterId, Date requestedAt) {
        this.type = type;
        this.description = description;
        this.status = status;
        this.requesterId = requesterId;
        this.requestedAt = requestedAt;
    }
    
    // Getter和Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getRequesterId() {
        return requesterId;
    }
    
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }
    
    public Date getRequestedAt() {
        return requestedAt;
    }
    
    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }
    
    public Long getApproverId() {
        return approverId;
    }
    
    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }
    
    public Date getApprovedAt() {
        return approvedAt;
    }
    
    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }
}