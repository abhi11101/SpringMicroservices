package org.abhi.accounts.Service;

import org.abhi.accounts.DTO.CustomerDetailsDTO;

public interface CustomerDetailsService {

    /**
     *
     * @param mobileNumber -Input Mobile Number
     * @return Customer details based on a given mobile number
     */
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
