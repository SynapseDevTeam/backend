package com.synapse.dto;

import java.util.UUID;

public record ProfileInitializedEvent(UUID userId, String fullName) {}
