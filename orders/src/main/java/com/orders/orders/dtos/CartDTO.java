package com.orders.orders.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {

    private Long id;

    @NotNull
    private Long customerId;

    private List<Long> productIds;
}