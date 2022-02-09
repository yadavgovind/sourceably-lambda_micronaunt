package com.oms.projectbuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "contact_us")
@NoArgsConstructor
@AllArgsConstructor
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String query;
    private String message;
    private String mobileno;
}
