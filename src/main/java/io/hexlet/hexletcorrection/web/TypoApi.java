package io.hexlet.hexletcorrection.web;

import io.hexlet.hexletcorrection.domain.Typo;
import io.hexlet.hexletcorrection.service.TypoService;
import io.hexlet.hexletcorrection.web.vm.TypoReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.net.*;

import static io.hexlet.hexletcorrection.web.Routers.API_TYPOS;
import static org.springframework.http.ResponseEntity.created;

@Slf4j
@RestController
@RequestMapping(API_TYPOS)
@RequiredArgsConstructor
public class TypoApi {

    private final TypoService service;

    @PostMapping
    public ResponseEntity<Typo> getAllTypos(@Valid @RequestBody TypoReport report) throws URISyntaxException {
        final var savedTypo = service.addTypoReport(report);
        return created(new URI("/typos/" + savedTypo.getId())).body(savedTypo);
    }
}
