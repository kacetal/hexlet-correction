package io.hexlet.hexletcorrection.web;

import io.hexlet.hexletcorrection.domain.Typo;
import io.hexlet.hexletcorrection.service.TypoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;

import static io.hexlet.hexletcorrection.web.Routers.TYPOS;


@Slf4j
@Controller
@RequestMapping(TYPOS)
@RequiredArgsConstructor
public class TypoResources {

    private final TypoService service;

    @GetMapping
    public String getAllTypos(Model model) {
        model.addAttribute("typos", service.getTypoPage(PageRequest.of(0, 10)));
        return TemplateConstants.TYPO_LIST_TEMPLATE;
    }

    @GetMapping("/{id}")
    public String getTypoById(Model model, @PathVariable Long id) {
        final var typo = service.getTypoById(id);
        if (typo.isEmpty()) {
            log.warn("Typo with id {} not found, " + Routers.REDIRECT_TO_TYPO_ROOT, id);
            return Routers.REDIRECT_TO_TYPO_ROOT;
        }
        model.addAttribute("typo", typo.get());
        return TemplateConstants.TYPO_INFO_TEMPLATE;
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(Model model, @PathVariable Long id) {
        final var typo = service.getTypoById(id);
        if (typo.isEmpty()) {
            log.error("Typo for update with id {} not found, " + Routers.REDIRECT_TO_TYPO_ROOT, typo);
            return Routers.REDIRECT_TO_TYPO_ROOT;
        }
        model.addAttribute("typo", typo.get());
        return TemplateConstants.TYPO_UPDATE_TEMPLATE;
    }

    @PutMapping
    public String updateTypo(@Valid Typo typo) {
        if (typo.getId() == null) {
            log.error("Typo for update must have an id: {}", typo);
            log.warn(Routers.REDIRECT_TO_TYPO_ROOT);
            return Routers.REDIRECT_TO_TYPO_ROOT;
        }
        return Routers.REDIRECT_TO_TYPO_ROOT + service.save(typo).getId();
    }

    @DeleteMapping("/{id}")
    public String deleteTypoById(@PathVariable Long id) {
        if (service.deleteTypoById(id)) {
            return Routers.REDIRECT_TO_TYPO_ROOT;
        }
        log.warn("Typo for delete with id {} not found, " + Routers.REDIRECT_TO_TYPO_ROOT, id);
        return Routers.REDIRECT_TO_TYPO_ROOT;
    }
}
