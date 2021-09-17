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
@Table(name = "retailer")
public class Retailer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String Name;

    private String EnterpCode;

    private String Address;

    @Column(name="tra_code")
    private long TraCode;

    @Column(name = "dealer_code")
    private String dealer_code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dealer_code", referencedColumnName="id",insertable=false, updatable=false)
    @JsonIgnore
    private Dealer DealerInfo;

    public Retailer(String name, String enterpCode, String address, String dealercode) {
        Name = name;
        EnterpCode = enterpCode;
        Address = address;
        dealer_code = dealercode;
    }
}
