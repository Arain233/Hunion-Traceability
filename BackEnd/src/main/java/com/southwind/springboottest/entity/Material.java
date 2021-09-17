package com.southwind.springboottest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "material")
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String Name;

    private String MFD;

    private String OriginPlace;

    private String EnterpCode;

    private String Address;

    @Column(name = "commodity_code")
    private String commodity_code;

    public Material(String name, String MFD, String originPlace, String enterpCode, String address, String commoditycode) {
        Name = name;
        this.MFD = MFD;
        OriginPlace = originPlace;
        EnterpCode = enterpCode;
        Address = address;
        commodity_code = commoditycode;
    }
}

