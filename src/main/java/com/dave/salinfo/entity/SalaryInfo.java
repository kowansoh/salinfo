package com.dave.salinfo.entity;

import javax.persistence.*;

@Entity
@Table(name = "salary_info")
public class SalaryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private float salary;
}
