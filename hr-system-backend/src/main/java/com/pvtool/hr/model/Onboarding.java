package com.pvtool.hr.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "onboardings")
public class Onboarding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String position;
    private Date startDate;
    private String status;
    
    // 构造函数
    public Onboarding() {
    }
    
    public Onboarding(String name, String position, Date startDate, String status) {
        this.name = name;
        this.position = position;
        this.startDate = startDate;
        this.status = status;
    }
    
    // Getter和Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}