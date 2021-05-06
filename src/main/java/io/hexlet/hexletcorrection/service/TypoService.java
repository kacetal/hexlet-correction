package io.hexlet.hexletcorrection.service;

import io.hexlet.hexletcorrection.domain.Typo;
import io.hexlet.hexletcorrection.repository.TypoRepository;
import io.hexlet.hexletcorrection.service.mapping.TypoMapper;
import io.hexlet.hexletcorrection.web.vm.TypoReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TypoService {

    private final TypoRepository repository;

    private final TypoMapper mapper;

    public Typo addTypoReport(TypoReport typoReport) {
        return repository.save(mapper.typoReportToTypo(typoReport));
    }

    public Page<Typo> getTypoPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Typo> getTypoById(Long id) {
        return repository.findById(id);
    }

    public boolean deleteTypoById(Long id) {
        return repository.deleteTypoById(id) > 0;
    }

    public Typo save(Typo typo) {
        return repository.save(typo);
    }
}
