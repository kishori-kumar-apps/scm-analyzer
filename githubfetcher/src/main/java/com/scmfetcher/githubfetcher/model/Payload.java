package com.scmfetcher.githubfetcher.model;

import com.scmfetcher.githubfetcher.constants.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
public class Payload implements Serializable {
    @Serial
    private static final long serialVersionUID = -8349709801996431595L;
    private Long payloadId;
    private String payload;
    private Instant receivedAt;
    private Instant updatedAt;
    private DeliveryStatus deliveryStatus;
}
