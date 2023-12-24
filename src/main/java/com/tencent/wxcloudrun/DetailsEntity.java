package com.tencent.wxcloudrun;

import javax.persistence.*;

@Entity
@Table(name = "details", schema = "pandian")
public class DetailsEntity {
    @Basic
    @Column(name = "no", nullable = false, length = 100)
    @Id
    private String no;
    @Basic
    @Column(name = "seat", nullable = true, length = 100)
    private String seat;
    @Basic
    @Column(name = "type", nullable = true, length = 100)
    private String type;
    @Basic
    @Column(name = "brand", nullable = true, length = 100)
    private String brand;
    @Basic
    @Column(name = "model", nullable = true, length = 100)
    private String model;
    @Basic
    @Column(name = "area", nullable = true, length = 100)
    private String area;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
