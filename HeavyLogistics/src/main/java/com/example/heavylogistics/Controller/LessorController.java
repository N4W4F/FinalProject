package com.example.heavylogistics.Controller;

import com.example.heavylogistics.ApiResponse.ApiResponse;
import com.example.heavylogistics.DTOin.InputLessor;
import com.example.heavylogistics.Service.LessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lessor")
public class LessorController {
    private final LessorService lessorService;

    @GetMapping("/get-all/{adminId}")
    public ResponseEntity getAllLessors(@PathVariable Integer adminId) {
        return ResponseEntity.status(200).body(lessorService.getAllLessors(adminId));
    }

    @GetMapping("/profile/{lessorId}")
    public ResponseEntity getLessorProfile(@PathVariable Integer lessorId) {
        return ResponseEntity.status(200).body(lessorService.getLessorProfile(lessorId));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid InputLessor inputLessor) {
        lessorService.register(inputLessor);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor has been registered successfully"));
    }

    @PutMapping("/update/{authId}/{lessorId}")
    public ResponseEntity updateLessor(@PathVariable Integer authId,
                                       @PathVariable Integer lessorId,
                                       @RequestBody @Valid InputLessor inputLessor) {
        lessorService.updateLessor(authId, lessorId, inputLessor);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor with ID: " + lessorId + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{authId}/{lessorId}")
    public ResponseEntity deleteLessor(@PathVariable Integer authId,
                                       @PathVariable Integer lessorId) {
        lessorService.deleteLessor(authId, lessorId);
        return ResponseEntity.status(200).body(new ApiResponse("Lessor with ID: " + lessorId + " has been updated successfully"));
    }
}
