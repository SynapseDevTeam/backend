package com.synapse.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userDevice")
public class UserDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private UUID catalogId;

    private String customName;

    @ManyToOne
    @JoinColumn(name = "homeId")
    @JsonIgnore
    private Home home;

    public UserDevice(String name, UUID catalogId, String customName, Home home) {
        this.name = name;
        this.catalogId = catalogId;
        this.customName = customName;
        this.home = home;
    }
}
