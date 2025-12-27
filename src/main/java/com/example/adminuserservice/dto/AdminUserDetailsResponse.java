package com.example.adminuserservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response body containing admin user details")
public class AdminUserDetailsResponse {

    @JsonProperty("department")
    @Schema(description = "Department of the user", example = "HR")
    private String department;

    @JsonProperty("designation")
    @Schema(description = "Designation of the user", example = "Manager")
    private String designation;

    @JsonProperty("product")
    @Schema(description = "Product assigned to the user", example = "Laptop")
    private String product;

    @JsonProperty("produce_code")
    @Schema(description = "Product code", example = "P01")
    private String produceCode;

    @JsonProperty("grade")
    @Schema(description = "Grade of the user", example = "A")
    private String grade;

    @JsonProperty("position")
    @Schema(description = "Position of the user", example = "Senior")
    private String position;
}
