package org.abhi.loans.Mappers;

import org.abhi.loans.DTO.LoanDTO;
import org.abhi.loans.Entity.Loans;

public class LoanMapper {

    public static LoanDTO maptoLoanDTO(Loans loans, LoanDTO loanDTO){

        loanDTO.setLoanNumber(loans.getLoanNumber());
        loanDTO.setLoanType(loans.getLoanType());
        loanDTO.setTotalLoan(loans.getTotalLoan());
        loanDTO.setAmountPaid(loans.getAmountPaid());
        loanDTO.setOutstandingAmount(loans.getOutstandingAmount());
        loanDTO.setMobileNumber(loans.getMobileNumber());

        return loanDTO;
    }

    public static Loans maptoLoan(LoanDTO loansDto, Loans loans){
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());

        return loans;
    }
}
