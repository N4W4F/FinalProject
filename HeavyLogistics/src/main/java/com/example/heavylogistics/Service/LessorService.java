package com.example.heavylogistics.Service;

import com.example.heavylogistics.ApiResponse.ApiException;
import com.example.heavylogistics.DTOin.InputLessor;
import com.example.heavylogistics.DTOout.OutputLessor;
import com.example.heavylogistics.Model.Lessor;
import com.example.heavylogistics.Model.MyUser;
import com.example.heavylogistics.Repository.AuthRepository;
import com.example.heavylogistics.Repository.LessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessorService {
    private final LessorRepository lessorRepository;
    private final AuthRepository authRepository;

    public List<Lessor> getAllLessors(Integer adminId) {
        MyUser admin = authRepository.findMyUserById(adminId);
        if (admin == null)
            throw new ApiException("Admin with ID: " + adminId + " was not found");

        if (admin.getRole().equals("ADMIN"))
            return lessorRepository.findAll();

        throw new ApiException("You don't have the permission to access this endpoint");
    }

    public OutputLessor getLessorProfile(Integer lessorId) {
        Lessor lessor = lessorRepository.findLessorById(lessorId);
        if (lessor == null)
            throw new ApiException("Lessor was not found");

        return new OutputLessor(lessor.getCompanyName(), lessor.getCommercialRegister(), lessor.getCompanyLocation(), lessor.getBio());
    }

    public void register(InputLessor inputLessor) {
        MyUser myUser = new MyUser();
        myUser.setUsername(inputLessor.getUsername());
        myUser.setPassword(inputLessor.getPassword());
        myUser.setEmail(inputLessor.getEmail());
        myUser.setPhoneNumber(inputLessor.getPhoneNumber());
        myUser.setRole("LESSOR");
        authRepository.save(myUser);

        Lessor lessor = new Lessor();
        lessor.setCompanyName(inputLessor.getCompanyName());
        lessor.setCommercialRegister(lessor.getCommercialRegister());
        lessor.setCompanyLocation(lessor.getCompanyLocation());
        lessor.setUser(myUser);
        lessorRepository.save(lessor);
    }

    public void updateLessor(Integer authId, Integer lessorId, InputLessor inputLessor) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null)
            throw new ApiException("Admin with ID: " + authId + " was not found");

        MyUser oldLessor = authRepository.findMyUserById(lessorId);
        if (oldLessor == null)
            throw new ApiException("Lessor with ID: " + lessorId + " was not found");

        if (authId.equals(lessorId) || auth.getRole().equals("ADMIN")) {
            oldLessor.setUsername(inputLessor.getUsername());
            oldLessor.setNationalId(inputLessor.getNationalId());
            oldLessor.setPassword(inputLessor.getPassword());
            oldLessor.setEmail(inputLessor.getEmail());
            oldLessor.setPhoneNumber(inputLessor.getPhoneNumber());
            oldLessor.getLessor().setCompanyName(inputLessor.getCompanyName());
            oldLessor.getLessor().setCompanyLocation(inputLessor.getCompanyLocation());
            authRepository.save(oldLessor);
        } else throw new ApiException("You don't have access to update this lessor");
    }

    public void deleteLessor(Integer authId, Integer lessorId) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null)
            throw new ApiException("Lessor with ID: " + authId + " was not found");

        MyUser oldLessor = authRepository.findMyUserById(lessorId);
        if (oldLessor == null)
            throw new ApiException("Lessor with ID: " + lessorId + " was not found");

        if (authId.equals(lessorId) || auth.getRole().equals("ADMIN"))
            authRepository.delete(oldLessor);

        else throw new ApiException("You don't have the permission to delete a lessor");
    }
}
