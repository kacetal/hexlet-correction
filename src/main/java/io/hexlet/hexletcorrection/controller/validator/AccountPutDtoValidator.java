package io.hexlet.hexletcorrection.controller.validator;

import io.hexlet.hexletcorrection.dto.account.AccountPutDto;
import io.hexlet.hexletcorrection.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class AccountPutDtoValidator implements Validator {

    PasswordEncoder passwordEncoder;
    AccountService accountService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AccountPutDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountPutDto accountPutDto = (AccountPutDto) o;

        String originalPassword = accountService.findById(accountPutDto.getId()).orElseThrow().getPassword();

        if (!originalPassword.equals(passwordEncoder.encode(accountPutDto.getPassword()))) {
            errors.rejectValue("password", "error.validation.password");
        }
    }
}
