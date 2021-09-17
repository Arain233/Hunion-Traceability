package com.southwind.springboottest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "commodity")
public class Commodity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String Name;

    @Column(name="tra_code")
    private long TraCode;

    private String MFD;

    private String OriginPlace;

    private String EnterpCode;

    private String Address;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Material> MaterialInfo;

    public Commodity(String name, String MFD, String originPlace, String enterpCode, String address) {
        Name = name;
        this.MFD = MFD;
        OriginPlace = originPlace;
        EnterpCode = enterpCode;
        Address = address;
    }
}
