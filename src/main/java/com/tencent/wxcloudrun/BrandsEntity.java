package com.tencent.wxcloudrun;

import javax.persistence.*;

@Entity
@Table(name = "brands", schema = "pandian")
public class BrandsEntity {
    @Basic
    @Column(name = "uuid", nullable = false, length = 100)
    @Id
    private String uuid;
    @Basic
    @Column(name = "type", nullable = true, length = 100)
    private String type;
    @Basic
    @Column(name = "brand", nullable = true, length = 100)
    private String brand;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
}
