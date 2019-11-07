package io.hexlet.hexletcorrection.controller;

import io.hexlet.hexletcorrection.controller.validator.AccountPutDtoValidator;
import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import io.hexlet.hexletcorrection.dto.account.AccountPutDto;
import io.hexlet.hexletcorrection.dto.mapper.AccountMapper;
import io.hexlet.hexletcorrection.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.hexlet.hexletcorrection.controller.ControllerConstants.ACCOUNTS_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.WARNING;
import static java.lang.String.format;
import static java.util.Collections.emptyList;

@Controller
@RequestMapping(ACCOUNTS_PATH)
@AllArgsConstructor
public class AccountControllerStatic {

    private static final String TEMPLATE_DIR = "account";
    private static final String VIEW_TEMPLATE = TEMPLATE_DIR + "/view";
    private static final String LIST_TEMPLATE = TEMPLATE_DIR + "/list";
    private static final String EDIT_TEMPLATE = TEMPLATE_DIR + "/edit";
    private static final String NEW_TEMPLATE = TEMPLATE_DIR + "/new";
    private static final String REDIRECT_ACCOUNT_PATH = "redirect:" + ACCOUNTS_PATH + "/";

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    private final AccountPutDtoValidator accountPutDtoValidator;

    @GetMapping
    public String getAccounts(Model model) {
        final List<Account> accounts = accountService.findAll();

        if (accounts.isEmpty()) {
            model.addAttribute(WARNING, "Any account found");
            model.addAttribute("accountGetDtoList", emptyList());
            return LIST_TEMPLATE;
        }

        List<AccountGetDto> accountGetDtoList = accounts
            .stream()
            .map(accountMapper::toAccountGetDto)
            .collect(Collectors.toList());

        model.addAttribute(WARNING, null);
        model.addAttribute("accountGetDtoList", accountGetDtoList);

        return LIST_TEMPLATE;
    }

    @GetMapping("/{id}")
    public String getAccountById(Model model, @PathVariable Long id) {
        final Optional<Account> account = accountService.findById(id);

        if (account.isEmpty()) {
            model.addAttribute(WARNING, format("Account with id=%d not found", id));
            return REDIRECT_ACCOUNT_PATH;
        }

        model.addAttribute("accountViewDto", accountMapper.toAccountViewDto(account.get()));
        return VIEW_TEMPLATE;
    }

    @GetMapping(value = "/edit/{id}")
    public String getEditForm(Model model, @PathVariable Long id) {
        Optional<Account> account = accountService.findById(id);

        if (account.isEmpty()) {
            model.addAttribute(WARNING, format("Account with id=%s not found", id));
            return REDIRECT_ACCOUNT_PATH;
        }

        model.addAttribute("accountPutDto", accountMapper.toAccountPutDto(account.get()));
        return EDIT_TEMPLATE;
    }

    @PutMapping()
    public String putAccount(@Valid @ModelAttribute AccountPutDto accountPutDto,
                             Errors errors,
                             Model model) {


        Optional<Account> accountOptional = accountService.findById(accountPutDto.getId());

        if (accountOptional.isEmpty()) {


            model.addAttribute(WARNING, "");
            return REDIRECT_ACCOUNT_PATH + "edit/" + accountPutDto.getId();
        }


//        if (originalPassword.equals(accountPutDto.getPassword())) {
//            //TODO Error password confirmation
//            return REDIRECT_ACCOUNT_PATH + "edit/" + accountPutDto.getId();
//        }

        Long updatedAccountId = accountService
            .save(accountMapper.putDtoToAccount(accountPutDto))
            .getId();

        return REDIRECT_ACCOUNT_PATH + updatedAccountId;
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
        return REDIRECT_ACCOUNT_PATH;
    }
}
