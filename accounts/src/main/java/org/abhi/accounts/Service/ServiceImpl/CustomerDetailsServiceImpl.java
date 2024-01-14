package org.abhi.accounts.Service.ServiceImpl;

import lombok.AllArgsConstructor;
import org.abhi.accounts.DTO.AccountDTO;
import org.abhi.accounts.DTO.CardsDTO;
import org.abhi.accounts.DTO.CustomerDetailsDTO;
import org.abhi.accounts.DTO.LoanDTO;
import org.abhi.accounts.Entity.Accounts;
import org.abhi.accounts.Entity.Customer;
import org.abhi.accounts.ExceptionHandling.ResourceNotFoundException;
import org.abhi.accounts.Mapper.AccountMapper;
import org.abhi.accounts.Mapper.CustomerMapper;
import org.abhi.accounts.Repositories.AccountsRepo;
import org.abhi.accounts.Repositories.CustomerRepo;
import org.abhi.accounts.Service.Client.CardsFeignClient;
import org.abhi.accounts.Service.Client.LoansFeignClient;
import org.abhi.accounts.Service.CustomerDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;


    /**
     *
     * @param mobileNumber -Input Mobile Number
     * @return Customer details based on a given mobile number
     */
    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","MobileNumber",mobileNumber)
        );

        Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDTO());
        customerDetailsDTO.setAccountsDto(AccountMapper.mapToAccountsDto(accounts,new AccountDTO()));


        ResponseEntity<LoanDTO> loanDTOResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDTO.setLoanDTO(loanDTOResponseEntity.getBody());
        System.out.println("Check this--->");
        System.out.println(loanDTOResponseEntity.getBody());
        ResponseEntity<CardsDTO> cardsDTOResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDTO.setCardsDTO(cardsDTOResponseEntity.getBody());


        return customerDetailsDTO;
    }
}














