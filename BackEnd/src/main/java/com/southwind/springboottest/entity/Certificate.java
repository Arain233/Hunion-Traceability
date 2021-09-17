package com.southwind.springboottest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "certificate")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String certificate_binary;

    private long tra_code;

    private String hash;

    public Certificate(String certificate_binary, long tra_code) {
        this.certificate_binary = certificate_binary;
        this.tra_code = tra_code;
    }
}
