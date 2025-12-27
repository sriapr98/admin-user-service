package com.example.adminuserservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin_user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "department")
    private String department;

    @Column(name = "designation")
    private String designation;

    @Column(name = "product")
    private String product;

    @Column(name = "produce_code")
    private String produceCode;

    @Column(name = "grade")
    private String grade;

    @Column(name = "position")
    private String position;
}
