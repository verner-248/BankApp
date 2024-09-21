package com.busycoder.loans.services.impl;

import com.busycoder.loans.dto.LoanDto;
import com.busycoder.loans.entities.Loan;
//import com.busycoder.loans.exceptions.ResourceNotFoundException;
import com.busycoder.loans.repositories.LoanRepo;
import com.busycoder.loans.services.LoanService;
import com.busycoder.loans.services.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class loanServiceImplTest {

    @InjectMocks
    private LoanServiceImpl loanService;

    @Mock
    private LoanRepo loanRepo;

    private Loan loan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loan = new Loan("7088993300",
                getLoanNumber(), "Personal", 52000,
                12000, 40000);
    }

    @Test
    void givenExistingMobile_whenGetByMobile_thenReturnLoan() {
        // Arrange
        String mobile = "7088993300";
        when(loanRepo.findByMobile(mobile)).thenReturn(loan);

        // Act
        LoanDto foundLoan = loanService.findByMobile(mobile);

        // Assert
        assertThat(foundLoan).isNotNull();
        assertThat(foundLoan.getMobile()).isEqualTo(mobile);
        //assertThat(foundLoan.getLoanNumber()).isEqualTo("1000000000000000");
        assertThat(foundLoan.getLoanType()).isEqualTo("Personal");
        assertThat(foundLoan.getTotalLoan()).isEqualTo(52000);
        assertThat(foundLoan.getAmountPaid()).isEqualTo(12000);
        assertThat(foundLoan.getOutstandingAmount()).isEqualTo(40000);

        // Verify that the repository method was called once
        verify(loanRepo, times(1)).findByMobile(mobile);
    }



    private String getLoanNumber(){
        long val = Math.abs(new Random().nextLong() % 1000000000000000L);
        return String.valueOf(1000000000000000L + val);
    }
}
