package com.bpm.aop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by bearPotMan on 2019/8/26 11:16.
 */
@Data
@Entity
@Table(name = "aop_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String password;

    private Date createTime;

    private Date updateTime;

}
