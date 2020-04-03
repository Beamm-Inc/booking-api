package com.beamm.flightbooking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Role {
    @OneToOne(cascade = CascadeType.PERSIST)
    Person person;

    private String role;

    public abstract boolean login(String userName, String password);
}
