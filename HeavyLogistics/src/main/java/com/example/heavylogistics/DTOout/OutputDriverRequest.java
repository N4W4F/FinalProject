package com.example.heavylogistics.DTOout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutputDriverRequest {

    private Integer requestId;
    private String customerName;
    private String customerPhoneNumber;

    private String vehicleName;
    private String vehicleType;
    private String vehicleCapacity;

    private String startLocation;
    private String endLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String customerNote;
    private String requestStatus;


}
