package com.synapse.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class DeviceRequest {
    private String customName;
    private UUID catalogProductId;
}
