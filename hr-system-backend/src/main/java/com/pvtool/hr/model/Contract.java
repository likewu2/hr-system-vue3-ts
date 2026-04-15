package com.pvtool.hr.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String employeeName;
    private String contractType;
    private Date startDate;
    private Date endDate;
    private String status;
    private Long signedBy;
    private Date signedAt;
    
    // 构造函数
    public Contract() {
    }
    
    public Contract(String employeeName, String contractType, Date startDate, Date endDate, String status) {
        this.employeeName = employeeName;
        this.contractType = contractType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    
    // Getter和Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public String getContractType() {
        return contractType;
    }
    
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getSignedBy() {
        return signedBy;
    }
    
    public void setSignedBy(Long signedBy) {
        this.signedBy = signedBy;
    }
    
    public Date getSignedAt() {
        return signedAt;
    }
    
    public void setSignedAt(Date signedAt) {
        this.signedAt = signedAt;
    }
}