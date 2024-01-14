package org.abhi.loans.Service;

import org.abhi.loans.DTO.LoanDTO;

public interface LoanService {

    /**
     *
     * @param mobileNumber
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    LoanDTO fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoanDTO loansDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);
}
