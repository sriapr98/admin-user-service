package com.example.adminuserservice.service;

import com.example.adminuserservice.dto.AdminUserDetailsRequest;
import com.example.adminuserservice.dto.AdminUserDetailsResponse;
import com.example.adminuserservice.entity.AdminUserDetails;
import com.example.adminuserservice.repository.AdminUserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService {

    private final AdminUserDetailsRepository repository;

    public void saveAdminUserDetails(AdminUserDetailsRequest request) {
        AdminUserDetails entity = new AdminUserDetails();
        entity.setUserId(request.getUserId());
        entity.setDepartment(request.getDepartment());
        entity.setDesignation(request.getDesignation());
        entity.setProduct(request.getProduct());
        entity.setProduceCode(request.getProduceCode());
        entity.setGrade(request.getGrade());
        entity.setPosition(request.getPosition());
        
        repository.save(entity);
    }

    public Optional<AdminUserDetailsResponse> getAdminUserDetails(String userId) {
        return repository.findByUserId(userId)
                .map(entity -> new AdminUserDetailsResponse(
                        entity.getDepartment(),
                        entity.getDesignation(),
                        entity.getProduct(),
                        entity.getProduceCode(),
                        entity.getGrade(),
                        entity.getPosition()
                ));
    }
}
