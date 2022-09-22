package com.botscrew.university.model;

import com.botscrew.university.constants.Degree;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;
    private String name;

    private String lastName;
    @ToString.Exclude
    private Degree degree;
    @ToString.Exclude
    private BigDecimal salary;
    //@ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "lectorsOfDepartment")



}