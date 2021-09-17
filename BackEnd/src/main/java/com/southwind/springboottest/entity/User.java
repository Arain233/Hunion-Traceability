package com.southwind.springboottest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String nickName;

    private String password;

    @JsonIgnore
    private String salt;

    private String email;

    private String mobile;

    @Column(name = "role_id")
    private Integer role_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName="id",insertable=false, updatable=false)
    @JsonIgnore
    private Role role;

    public User(String name, String nickName, String password, String salt, String email, String mobile, Integer role_id) {
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.mobile = mobile;
        this.role_id = role_id;
    }

    public User(String name, String nickName, String password, String email, String mobile, Integer role_id) {
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.role_id = role_id;
    }
}
