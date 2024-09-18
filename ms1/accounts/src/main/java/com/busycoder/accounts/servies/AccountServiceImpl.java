package com.busycoder.accounts.servies;

import com.busycoder.accounts.dto.AccountDto;
import com.busycoder.accounts.dto.AccountInfoDto;
import com.busycoder.accounts.dto.CardDto;
import com.busycoder.accounts.dto.LoanDto;
import com.busycoder.accounts.entities.Account;
import com.busycoder.accounts.repo.AccountRepo;
import com.busycoder.accounts.serviceproxy.CardServiceProxy;
import com.busycoder.accounts.serviceproxy.LoanServiceProxy;
import com.busycoder.accounts.util.DtoConvertor;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepo accountRepo;
    private final CardServiceProxy cardServiceProxy;
    private final LoanServiceProxy loanServiceProxy;

    @Override
    public List<AccountDto> getAll() {
        return accountRepo.findAll().stream()
                .map(DtoConvertor::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getByMobile(String mobile) {
        return DtoConvertor.entityToDto(accountRepo.findByMobile(mobile));
    }

//    @Override
//    public AccountInfoDto getAccountDetails(String mobile) {
//        //somehow we should be able to call the
//        // loans and cards ms and get the related information
//        // http://localhost:8090/loans?mobile=7088993300
//       // http://localhost:9090/cards?mobile=7088993300
//
//        AccountInfoDto accountInfoDto=new AccountInfoDto();
//        CardDto cardDto=new CardDto();
//        LoanDto loanDto=new LoanDto();
//        try{
//            cardDto=cardServiceProxy.findByMobileNumber(mobile);
//        } catch (FeignException e){
//            System.err.
//        }
//        accountInfoDto.setAccountDto(getByMobile(mobile));
//        accountInfoDto.setCardDto(cardServiceProxy.findByMobileNumber(mobile));
//        accountInfoDto.setLoanDto(loanServiceProxy.getByMobile(mobile));
//        return accountInfoDto;
//    }

    @Override
    public AccountInfoDto getAccountDetails(String mobile) {
        // Initialize AccountInfoDto to hold the combined information
        AccountInfoDto accountInfoDto = new AccountInfoDto();

        // Initialize CardDto and LoanDto as null or empty to handle cases where data is not found
        CardDto cardDto = null;
        LoanDto loanDto = null;

        // Fetch card details using cardServiceProxy
        try {
            cardDto = cardServiceProxy.findByMobileNumber(mobile);
        } catch (FeignException e) {
            System.err.println("error fetching card details: " + e.getMessage());
        }

        // Fetch loan details using loanServiceProxy
        try {
            loanDto = loanServiceProxy.getByMobile(mobile);
        } catch (FeignException e) {
            System.err.println("error fetching loan details: " + e.getMessage());
        }

        // Set the values in AccountInfoDto (set to null if not found)
        accountInfoDto.setAccountDto(getByMobile(mobile)); // Assuming this method always returns valid data
       if(cardDto!=null){
           accountInfoDto.setCardDto(cardDto);// Can be null if card details are not found
       }
        if(loanDto!=null){
            accountInfoDto.setLoanDto(loanDto);// Can be null if loan details are not found
        }

        return accountInfoDto;
    }


    @Override
    public String addAccount(AccountDto accountDto) {
        Account account=DtoConvertor.dtoToEntity(accountDto);
        accountRepo.save(account);
        accountDto.setAccId(account.getAccId());
        return "account is added successfully";
    }
}
