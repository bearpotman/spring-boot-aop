package com.bpm.aop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by bearPotMan on 2019/8/26 11:16.
 */
@Data
@Entity
@Table(name = "aop_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String phone;

    private String password;

    private Date createTime;

    private Date updateTime;

}
