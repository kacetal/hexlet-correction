package io.hexlet.hexletcorrection.controller;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPutDto;
import io.hexlet.hexletcorrection.dto.mapper.CorrectionMapper;
import io.hexlet.hexletcorrection.service.AccountService;
import io.hexlet.hexletcorrection.service.CorrectionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static io.hexlet.hexletcorrection.controller.ControllerConstants.CORRECTIONS_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.WARNING;
import static java.lang.String.format;

@Controller
@RequestMapping(CORRECTIONS_PATH)
@AllArgsConstructor
public class CorrectionControllerStatic {

    private static final String TEMPLATE_DIR = "correction";
    private static final String VIEW_TEMPLATE = TEMPLATE_DIR + "/view";
    private static final String LIST_TEMPLATE = TEMPLATE_DIR + "/list";
    private static final String EDIT_TEMPLATE = TEMPLATE_DIR + "/edit";
    private static final String REDIRECT_CORRECTIONS_PATH = "redirect:" + CORRECTIONS_PATH + "/";

    private final CorrectionService correctionService;
    private final AccountService accountService;
    private final CorrectionMapper correctionMapper;

    @GetMapping
    public String getCorrections(Model model) {
        final List<Correction> correctionList = correctionService.findAll();

        if (correctionList.size() == 0) {
            model.addAttribute(WARNING, "Any correction found");
            model.addAttribute("correctionGetDtos", Collections.EMPTY_LIST);
        }

        final List<CorrectionGetDto> correctionGetDtos = correctionList
            .stream()
            .map(correctionMapper::toCorrectionGetDto)
            .collect(Collectors.toList());

        model.addAttribute(WARNING, null);
        model.addAttribute("correctionGetDtos", correctionGetDtos);

        return LIST_TEMPLATE;
    }

    @GetMapping("/my")
    public String getCorrectionsByCorrecter(Model model, @AuthenticationPrincipal Account account) {
        final List<Correction> correctionList = correctionService.findByCorrecter(account);

        if (correctionList.size() == 0) {
            model.addAttribute(WARNING, "Any correction found by user with username=" + account.getUsername());
            model.addAttribute("correctionGetDtos", Collections.EMPTY_LIST);
        }

        final List<CorrectionGetDto> correctionGetDtos = correctionList
            .stream()
            .map(correctionMapper::toCorrectionGetDto)
            .collect(Collectors.toList());

        model.addAttribute(WARNING, null);
        model.addAttribute("correctionGetDtos", correctionGetDtos);

        return LIST_TEMPLATE;
    }

    @GetMapping("/resolver/{id}")
    public String getCorrectionsByResolver(Model model, @PathVariable Long id) {
        final Optional<Account> resolver = accountService.findById(id);
        final List<Correction> correctionList = correctionService.findByResolver(resolver.orElse(null));

        if (correctionList.size() == 0) {
            model.addAttribute(WARNING, "Any correction found by resolver with id=" + id);
            model.addAttribute("correctionGetDtos", Collections.EMPTY_LIST);
        }

        final List<CorrectionGetDto> correctionGetDtos = correctionList
            .stream()
            .map(correctionMapper::toCorrectionGetDto)
            .collect(Collectors.toList());

        model.addAttribute(WARNING, null);
        model.addAttribute("correctionGetDtos", correctionGetDtos);

        return LIST_TEMPLATE;
    }

    @GetMapping("/{id}")
    public String getCorrectionById(Model model, @PathVariable Long id) {
        Runnable fillWarning = () -> model.addAttribute(WARNING, format("Correction with id=%s not found", id));

        Consumer<Correction> fillAccount = correction -> {
            model.addAttribute("CorrectionViewDtos", correctionMapper.toCorrectionViewDto(correction));
            model.addAttribute(WARNING, null);
        };

        correctionService.findById(id).ifPresentOrElse(fillAccount, fillWarning);

        return VIEW_TEMPLATE;
    }

    @GetMapping(value = "/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {
        Optional<Correction> correctionOptional = correctionService.findById(id);

        if (correctionOptional.isEmpty()) {
            model.addAttribute(WARNING, format("Correction with id=%d not found", id));
            return REDIRECT_CORRECTIONS_PATH;
        }

        model.addAttribute(WARNING, null);
        model.addAttribute("correctionPutDto", correctionMapper.toCorrectionPutDto(correctionOptional.get()));

        return EDIT_TEMPLATE;
    }

    @PutMapping()
    public String putAccount(
        @Valid @ModelAttribute CorrectionPutDto correctionPutDto,
        Errors errors,
        Model model
    ) {



        Optional<Account> accountOptional = accountService.findById(correctionPutDto.getId());

        if (accountOptional.isEmpty()) {


            model.addAttribute(WARNING, "");
            return REDIRECT_CORRECTIONS_PATH + "edit/" + correctionPutDto.getId();
        }


//        if (originalPassword.equals(correctionPutDto.getPassword())) {
//            //TODO Error password confirmation
//            return REDIRECT_CORRECTIONS_PATH + "edit/" + correctionPutDto.getId();
//        }

        Long updatedAccountId = correctionService
            .save(correctionMapper.putDtoToCorrection(correctionPutDto))
            .getId();

        return REDIRECT_CORRECTIONS_PATH + updatedAccountId;
    }

    @DeleteMapping("/{id}")
    public String deleteCorrectionById(@PathVariable Long id) {
        correctionService.deleteById(id);
        return REDIRECT_CORRECTIONS_PATH;
    }
}
