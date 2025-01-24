package org.islom.dars21.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @NotNull(message = "FISH bo'sh bo'lmasligi kerak")
    private String fullName;
    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak")
    private String phoneNumber;
    @NotNull(message = "address bo'sh bo'lmasligi kerak")
    private String address;
}
