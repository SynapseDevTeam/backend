package com.synapse.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserProfileDTO {
    private UUID id;
    private String fullName;
    private String telephone;
    private String address;
    private String planName;
}