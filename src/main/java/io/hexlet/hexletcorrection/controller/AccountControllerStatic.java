package io.hexlet.hexletcorrection.controller;

import io.hexlet.hexletcorrection.controller.validator.AccountPostDtoValidator;
import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.dto.AccountGetDto;
import io.hexlet.hexletcorrection.dto.AccountPostDto;
import io.hexlet.hexletcorrection.dto.AccountPutDto;
import io.hexlet.hexletcorrection.dto.mapper.AccountMapper;
import io.hexlet.hexletcorrection.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static io.hexlet.hexletcorrection.controller.ControllerConstants.ACCOUNTS_PATH;
import static java.lang.String.format;
import static java.util.Objects.nonNull;

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
    private final AccountPostDtoValidator accountPostDtoValidator;

    @GetMapping
    public String getAccounts(Model model, @RequestParam(required = false) String name) {
        List<AccountGetDto> accountGetDtos;

        if (nonNull(name)) {
            accountGetDtos = accountService.findByName(name)
                    .stream()
                    .map(accountMapper::toAccountGetDto)
                    .collect(Collectors.toList());
        } else {
            accountGetDtos = accountService.findAll()
                    .stream()
                    .map(accountMapper::toAccountGetDto)
                    .collect(Collectors.toList());
        }
        model.addAttribute("accountDtos", accountGetDtos);
        return LIST_TEMPLATE;
    }

    @GetMapping("/{id}")
    public String getAccountById(Model model, @PathVariable Long id) {
        Runnable fillError = () -> model.addAttribute("error", format("Account with id=%s not found", id));
        Consumer<Account> fillAccount = account -> model.addAttribute("accountGetDto", accountMapper.toAccountGetDto(account));

        accountService.findById(id).ifPresentOrElse(fillAccount, fillError);

        return VIEW_TEMPLATE;
    }

    @GetMapping(value = "/new")
    public String getCreationForm(Model model) {
        model.addAttribute("accountPostDto", new AccountPostDto());
        return NEW_TEMPLATE;
    }

    @PostMapping
    public String postAccount(@Valid @ModelAttribute AccountPostDto accountPostDto) {
        accountPostDtoValidator.validate(accountPostDto, null);
        if (!accountPostDto.getPassword().equals(accountPostDto.getPasswordConfirm())) {
            //TODO Error two passwords not same
            return REDIRECT_ACCOUNT_PATH + "new";
        }
        Account account = accountMapper.postDtoToAccount(accountPostDto);
        Long savedAccountId = accountService.create(account).getId();

        return REDIRECT_ACCOUNT_PATH + savedAccountId;
    }

    @GetMapping(value = "/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (accountOptional.isPresent()) {
            model.addAttribute("accountGetDto", accountMapper.toAccountGetDto(accountOptional.get()));
        } else {
            model.addAttribute("error", format("Account with id=%s not found", id));
            return REDIRECT_ACCOUNT_PATH + "new";
        }

        return EDIT_TEMPLATE;
    }

    @PutMapping()
    public String putAccount(@Valid @RequestBody AccountPutDto accountPutDto) {
        Account accountOriginal = accountService.findById(accountPutDto.getId()).orElseThrow();
        String originalPassword = accountOriginal.getPassword();

        if (originalPassword.equals(accountPutDto.getPassword())) {
            //TODO Error password confirmation
            return REDIRECT_ACCOUNT_PATH + "edit/" + accountPutDto.getId();
        }

        Long updatedAccountId = accountService
            .create(accountMapper.putDtoToAccount(accountPutDto))
            .getId();

        return REDIRECT_ACCOUNT_PATH + updatedAccountId;
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
        return REDIRECT_ACCOUNT_PATH;
    }
}
