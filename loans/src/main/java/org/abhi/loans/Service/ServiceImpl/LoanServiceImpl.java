package org.abhi.loans.Service.ServiceImpl;

import lombok.AllArgsConstructor;
import org.abhi.loans.Constants.LoansConstants;
import org.abhi.loans.DTO.LoanDTO;
import org.abhi.loans.Entity.Loans;
import org.abhi.loans.ExceptionHandling.LoanAlreadyExistsException;
import org.abhi.loans.ExceptionHandling.ResourceNotFoundException;
import org.abhi.loans.LoansApplication;
import org.abhi.loans.Mappers.LoanMapper;
import org.abhi.loans.Repositories.LoanRepo;
import org.abhi.loans.Service.LoanService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepo loanRepo;

    /**
     *
     * @param mobileNumber
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loansOptional = loanRepo.findByMobileNumber(mobileNumber);
        if (loansOptional.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber" + mobileNumber);
        }
        loanRepo.save(createLoanAccount(mobileNumber));
    }

    private Loans createLoanAccount(String mobileNumber){
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     *
     * @param mobileNumber Input mobile Number
     * @return
     */
    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        Loans loans = loanRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.maptoLoanDTO(loans, new LoanDTO());
    }

    /**
     *
     * @param loansDto - LoansDto Object
     * @return
     */
    @Override
    public boolean updateLoan(LoanDTO loansDto) {

        Loans loans = loanRepo.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber())
        );

        LoanMapper.maptoLoan(loansDto,loans);
        loanRepo.save(loans);

        return true;
    }

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loanRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loanRepo.deleteById(loans.getLoanId());
        return true;
    }
}
