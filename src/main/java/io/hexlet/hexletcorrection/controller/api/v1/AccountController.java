package io.hexlet.hexletcorrection.controller.api.v1;

import io.hexlet.hexletcorrection.controller.exception.AccountNotFoundException;
import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import io.hexlet.hexletcorrection.dto.account.AccountPostDto;
import io.hexlet.hexletcorrection.dto.account.AccountPutDto;
import io.hexlet.hexletcorrection.dto.account.AccountViewDto;
import io.hexlet.hexletcorrection.dto.mapper.AccountMapper;
import io.hexlet.hexletcorrection.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static io.hexlet.hexletcorrection.controller.ControllerConstants.ACCOUNTS_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.API_PATH_V1;

@Slf4j
@RestController
@RequestMapping(API_PATH_V1 + ACCOUNTS_PATH)
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping
    public List<AccountGetDto> getAccounts() {
        return accountService.findAll()
            .stream()
            .map(accountMapper::toAccountGetDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountViewDto getAccountById(@PathVariable Long id) {
        final Account accountToView = accountService.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        return accountMapper.toAccountViewDto(accountToView);
    }

    @GetMapping("/edit/{id}")
    public AccountPutDto getAccountByIdToPut(@PathVariable Long id) {
        final Account accountToEdit = accountService.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        return accountMapper.toAccountPutDto(accountToEdit);
    }

    @GetMapping("/new")
    public AccountPostDto getAccountToPost() {
        return new AccountPostDto();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountViewDto putAccount(@Valid @RequestBody AccountPutDto accountPutDto) {
        final Account putDtoToSave = accountMapper.putDtoToAccount(accountPutDto);
        final Account savedAccount = accountService.save(putDtoToSave);
        return accountMapper.toAccountViewDto(savedAccount);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountViewDto postAccount(@Valid @RequestBody AccountPostDto accountPostDto) {
        final Account postDtoToSave = accountMapper.postDtoToAccount(accountPostDto);
        final Account savedAccount = accountService.save(postDtoToSave);
        return accountMapper.toAccountViewDto(savedAccount);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") Long id) {
        if (accountService.existsById(id)) {
            accountService.deleteById(id);
        }
        log.warn("Delete non existing entity with id=" + id);
    }
}
