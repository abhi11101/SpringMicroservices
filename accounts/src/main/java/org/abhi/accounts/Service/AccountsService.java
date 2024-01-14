package org.abhi.accounts.Service;

import org.abhi.accounts.DTO.CustomerDTO;

public interface AccountsService {

    /**
     *
     * @param customerDTO - CustomerDto Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber Input Mobile Number
     * @return Account Details based on a given mobileNumber
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDTO customerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber Input mobileNumber
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}
