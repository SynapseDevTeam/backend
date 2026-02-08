package com.synapse.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscriptions")
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Subscription {
    @Id
    @Column(name = "user_id")
    private UUID id;

    @OneToOne
    @MapsId 
    @JoinColumn(name = "user_id")
    private UserProfile user;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id")
    private Plan plan;
}