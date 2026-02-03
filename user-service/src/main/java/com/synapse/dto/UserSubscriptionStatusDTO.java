package com.synapse.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserSubscriptionStatusDTO {
    private String planName;
    private String status;
    private int maxHomes;
    private int maxDevicesPerHome;
    private String expiresAt; // O LocalDateTime si prefieres
}