package com.orders.orders.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    @NotNull
    private Long customerId;

    @NotNull
    private LocalDateTime createdAt;

    @NotBlank
    private String status;

    private List<Long> productIds;
}

