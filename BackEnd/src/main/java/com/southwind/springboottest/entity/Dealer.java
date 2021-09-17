package com.southwind.springboottest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dealer")
public class Dealer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String Name;

    private String EnterpCode;

    private String Address;

    @Column(name = "commodity_code")
    private String commodity_code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commodity_code", referencedColumnName="id",insertable=false, updatable=false)
    @JsonIgnore
    private Commodity CommodityInfo;

    public Dealer(String name, String enterpCode, String address, String commoditycode) {
        Name = name;
        EnterpCode = enterpCode;
        Address = address;
        commodity_code = commoditycode;
    }
}
