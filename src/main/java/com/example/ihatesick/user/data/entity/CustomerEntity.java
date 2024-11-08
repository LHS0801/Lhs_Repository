package com.example.ihatesick.user.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "customer")

public class CustomerEntity {

    @Id
    @Column(name = "customer_id", unique = true, nullable = false)
    private String customer_id;

    @Column(name = "customer_name", nullable = false)
    private String customer_name;

    @Column(name = "customer_email", nullable = false)
    private String customer_email;

    @Column(name = "customer_password", nullable = false)
    private String customer_password;

    @Column(name = "customer_birth", nullable = false)
    private Date customer_birth;

    @Column(name = "customer_gender", nullable = false)
    private String customer_gender;

    @Column(name = "customer_phone", nullable = false)
    private String customer_phone;






    public String getId() {
        return customer_id;
    }

    public void setId(String id) {
        this.customer_id = id;
    }

    public String getName() {
        return customer_name;
    }

    public void setName(String name) {
        this.customer_name = name;
    }

    public String getEmail() {
        return customer_email;
    }

    public void setEmail(String email) {
        this.customer_email = email;
    }

    public String getPassword() {
        return customer_password;
    }

    public void setPassword(String password) {
        this.customer_password = password;}

    public Date getBirth() {return customer_birth;}

    public void setBirth(Date birth) {
        this.customer_birth = birth;
    }

    public String getGender() {
        return customer_gender;
    }

    public void setGender(String gender) {
        this.customer_gender = gender;
    }

    public String getPhone() {
        return customer_phone;
    }

    public void setPhone(String phone) {
        this.customer_phone = phone;
    }



}