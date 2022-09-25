package com.dave.salinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salary_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryInfo {
  @Id private String name;

  @Column private float salary;
}
