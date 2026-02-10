package com.synapse.dto;

import lombok.*;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserCreatedEvent {
    private UUID userId;
    private String name;
}
