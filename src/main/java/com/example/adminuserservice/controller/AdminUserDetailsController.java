package com.example.adminuserservice.controller;

import com.example.adminuserservice.dto.AdminUserDetailsRequest;
import com.example.adminuserservice.dto.AdminUserDetailsResponse;
import com.example.adminuserservice.service.AdminUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin User Details", description = "APIs for managing admin user details")
public class AdminUserDetailsController {

    private final AdminUserDetailsService service;

    @PostMapping("/details")
    @Operation(summary = "Store admin user details", description = "Saves admin user details to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Admin user details saved successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Void> saveAdminUserDetails(@RequestBody AdminUserDetailsRequest request) {
        service.saveAdminUserDetails(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details")
    @Operation(summary = "Retrieve admin user details", description = "Retrieves admin user details from the database by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin user details retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdminUserDetailsResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<AdminUserDetailsResponse> getAdminUserDetails(
            @Parameter(description = "User ID to retrieve details for", required = true, example = "USR001")
            @RequestParam("user_id") String userId) {
        return service.getAdminUserDetails(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
