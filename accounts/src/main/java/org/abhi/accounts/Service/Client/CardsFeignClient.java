package org.abhi.accounts.Service.Client;

import org.abhi.accounts.DTO.CardsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/fetch" ,consumes = "application/json")
    ResponseEntity<CardsDTO> fetchCardDetails(@RequestParam String mobileNumber);

}
