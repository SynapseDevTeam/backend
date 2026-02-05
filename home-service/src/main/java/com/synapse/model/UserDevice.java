package com.synapse.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "user_device")
public class UserDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "catalog_id", nullable = false)
    private UUID catalogId;

    private String customName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    @JsonIgnore
    private Home home;

    public UserDevice(UUID catalogId, String customName, Home home) {
        this.catalogId = catalogId;
        this.customName = customName;
        this.home = home;
    }
}
