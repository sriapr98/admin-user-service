package com.example.adminuserservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for creating admin user details")
public class AdminUserDetailsRequest {

    @JsonProperty("user_id")
    @Schema(description = "Unique user identifier", example = "USR001")
    private String userId;

    @JsonProperty("department")
    @Schema(description = "Department of the user", example = "HR", allowableValues = {"HR", "Finance"})
    private String department;

    @JsonProperty("designation")
    @Schema(description = "Designation of the user", example = "Manager", allowableValues = {"Manager", "Team lead"})
    private String designation;

    @JsonProperty("product")
    @Schema(description = "Product assigned to the user", example = "Laptop", allowableValues = {"Laptop", "Monitor"})
    private String product;

    @JsonProperty("produce_code")
    @Schema(description = "Product code", example = "P01", allowableValues = {"P01", "P02"})
    private String produceCode;

    @JsonProperty("grade")
    @Schema(description = "Grade of the user", example = "A", allowableValues = {"A", "B", "C"})
    private String grade;

    @JsonProperty("position")
    @Schema(description = "Position of the user", example = "Senior", allowableValues = {"Junior", "Senior", "Lead"})
    private String position;
}
