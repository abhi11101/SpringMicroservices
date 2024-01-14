package org.abhi.accounts.Service.ServiceImpl;

import lombok.AllArgsConstructor;
import org.abhi.accounts.Constants.AccountsConstants;
import org.abhi.accounts.DTO.AccountDTO;
import org.abhi.accounts.DTO.CustomerDTO;
import org.abhi.accounts.Entity.Accounts;
import org.abhi.accounts.Entity.Customer;
import org.abhi.accounts.ExceptionHandling.CustomerAlreadyExistsExcepion;
import org.abhi.accounts.ExceptionHandling.ResourceNotFoundException;
import org.abhi.accounts.Mapper.AccountMapper;
import org.abhi.accounts.Mapper.CustomerMapper;
import org.abhi.accounts.Repositories.AccountsRepo;
import org.abhi.accounts.Repositories.CustomerRepo;
import org.abhi.accounts.Service.AccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;

    /**
     *
     * @param customerDTO - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepo.findByMobileNumber(customerDTO.getMobileNumber());

        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsExcepion(
                    "Customer Already registered with given mobileNumber " + customerDTO.getMobileNumber());
        }
        Customer savedCustomer = customerRepo.save(customer);
        accountsRepo.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAcctNumber = 1000000L + new Random().nextInt(9000000);

        newAccount.setAccountNumber(randomAcctNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    /**
     *
     * @param mobileNumber Input Mobile Number
     * @return Account Details based on a given mobileNumber
     */
    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {

        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)

        );

        Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(

                ()-> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer,new CustomerDTO());
        customerDTO.setAccountsDto(AccountMapper.mapToAccountsDto(accounts,new AccountDTO()));

        return customerDTO;
    }

    /**
     *
     * @param customerDTO customerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {

        boolean isUpdated = false;
        AccountDTO accountsDto = customerDTO.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

//          We are mapping all the account data we received in request body to account object for update.
            AccountMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepo.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepo.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customerRepo.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());
        return true;
    }

}

















