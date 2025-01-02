package com.example.heavylogistics.Controller;

import com.example.heavylogistics.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get-all")
    public ResponseEntity getAllUsers(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(authService.getAllUsers(id));
    }
}
