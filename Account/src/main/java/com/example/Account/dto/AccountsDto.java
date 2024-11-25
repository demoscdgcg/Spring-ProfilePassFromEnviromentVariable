package com.example.Account.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {


    private long accountNo;


    @NotEmpty(message = "accountType should not be null")
    private String accountType;

    @NotEmpty(message = "branchAddress should not be null")
    private String branchAddress;
}
