package com.tencent.wxcloudrun;

import javax.persistence.*;

@Entity
@Table(name = "areas", schema = "pandian")
public class AreasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "area", nullable = false, length = 100)
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}