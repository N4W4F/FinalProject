package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public List<MyUser> getAllUsers(Integer adminId) {
        MyUser admin = authRepository.findMyUserById(adminId);
        if (admin == null)
            throw new ApiException("Admin with ID: " + adminId + " was not found");

        if (admin.getRole().equals("ADMIN"))
            return authRepository.findAll();

        throw new ApiException("You don't have the permission to access this endpoint");
    }
}
