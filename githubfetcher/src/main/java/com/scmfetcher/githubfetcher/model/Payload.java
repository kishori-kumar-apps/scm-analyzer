package com.scmfetcher.githubfetcher.model;

import com.scmfetcher.githubfetcher.constants.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@Entity
@Table
public class Payload implements Serializable {
    @Serial
    private static final long serialVersionUID = -8349709801996431595L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long payloadId;
    private String payload;
    private Instant receivedAt;
    private Instant updatedAt;
    private DeliveryStatus deliveryStatus;
}
