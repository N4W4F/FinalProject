package com.example.heavylogistics.DTOout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputLessor {
    private String companyName;
    private String commercialRegister;
    private String companyLocation;
    private String bio;
//    private List<OutputVehicle> vehicles;
}
