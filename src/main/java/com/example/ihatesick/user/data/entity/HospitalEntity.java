package com.example.ihatesick.user.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Data
@Table(name = "hospital")
public class HospitalEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "codename", nullable = false)
    private String codename;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "call", nullable = false)
    private String call;

    @Column(name = "x", nullable = false)
    private BigDecimal x;

    @Column(name = "y", nullable = false)
    private BigDecimal y;

}
