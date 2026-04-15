-- src/main/resources/data.sql
-- PostgreSQL 数据库初始化脚本
-- 注意：PostgreSQL 需要手动创建数据库，这里只创建表结构

-- 员工表
CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    entry_date DATE NOT NULL,
    phone VARCHAR(255),
    address VARCHAR(255),
    status VARCHAR(255) DEFAULT '在职'
);

-- 档案库房表
CREATE TABLE IF NOT EXISTS warehouses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    description TEXT,
    capacity INTEGER
);

-- 设备表
CREATE TABLE IF NOT EXISTS devices (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    model VARCHAR(255),
    serial_number VARCHAR(255),
    status VARCHAR(255) DEFAULT '待机',
    is_online BOOLEAN DEFAULT false,
    is_controlled BOOLEAN DEFAULT true,
    control_status VARCHAR(255) DEFAULT '关闭',
    warehouse_id INTEGER REFERENCES warehouses(id),
    last_update_time TIMESTAMP,
    installation_date TIMESTAMP
);

-- 传感器数据表
CREATE TABLE IF NOT EXISTS sensor_data (
    id SERIAL PRIMARY KEY,
    warehouse_id INTEGER REFERENCES warehouses(id),
    sensor_type VARCHAR(255),
    sensor_name VARCHAR(255),
    temperature DOUBLE PRECISION,
    humidity DOUBLE PRECISION,
    pm25 DOUBLE PRECISION,
    pm10 DOUBLE PRECISION,
    tvoc DOUBLE PRECISION,
    co2 DOUBLE PRECISION,
    formaldehyde DOUBLE PRECISION,
    status VARCHAR(255) DEFAULT '正常',
    timestamp TIMESTAMP
);