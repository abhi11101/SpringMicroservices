package org.abhi.accounts.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.abhi.accounts.DTO.CustomerDetailsDTO;
import org.abhi.accounts.DTO.ErrorResponseDTO;
import org.abhi.accounts.Service.CustomerDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "REST APIs for Customer in Application",
        description = "REST API for EazyBank to FETCH customer details"
)
@RestController
@RequestMapping(value = "/api/",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerDetailsService customerDetailsService;


    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestParam
               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")String mobileNumber){

          CustomerDetailsDTO customerDetailsDTO = customerDetailsService.fetchCustomerDetails(mobileNumber);

          return ResponseEntity
                  .status(HttpStatus.OK)
                  .body(customerDetailsDTO);
    }

}
