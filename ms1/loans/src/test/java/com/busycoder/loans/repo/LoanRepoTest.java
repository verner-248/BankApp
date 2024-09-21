//package com.busycoder.loans.repo;
//
//import com.busycoder.loans.entities.Loan;
//import com.busycoder.loans.repositories.LoanRepo;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Random;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//class LoanRepoTest {
//
//    @Autowired
//    private LoanRepo loanRepo;
//    private Loan loan;
//
//    @BeforeEach
//    void setUp() {
//        loan=new Loan("7088993300",
//                getLoanNumber(),"Personal",52000,
//                12000,40000 );
//    }
//
//
//
//
//    @DisplayName("JUnit test for get product by id operation")
//    @Test
//    public void givenProductObject_whenFindById_thenReturnProductObject(){
//        // given - precondition or setup
//        Loan p1=new Loan("7088993300",
//                getLoanNumber(),"Personal",52000,
//                12000,40000 );
//        loanRepo.save(p1);
//
//        // when -  action or the behaviour that we are going test
//        Loan loanDB = loanRepo.findByMobile(p1.getMobile());
//        // then - verify the output
//        assertThat(loanDB).isNotNull();
//    }
//
//
//
//
//    @AfterEach
//    void tearDown() {
//    }
//
//
//    private String getLoanNumber(){
//        long val = Math.abs(new Random().nextLong() % 1000000000000000L); // Ensure a positive value with up to 15 digits
//        return String.valueOf(1000000000000000L + val); // Ensures a 16-digit loan number
//    }
//
//}
