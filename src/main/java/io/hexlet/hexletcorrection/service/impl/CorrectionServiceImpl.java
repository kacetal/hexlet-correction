package io.hexlet.hexletcorrection.service.impl;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.repository.CorrectionRepository;
import io.hexlet.hexletcorrection.service.CorrectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorrectionServiceImpl implements CorrectionService {

    private final CorrectionRepository correctionRepository;

    @Override
    public List<Correction> findByReporter(String reporter) {
        if (isNullOrEmpty(reporter)) {
            return emptyList();
        }
        return correctionRepository.findByReporterName(reporter);
    }

    @Override
    public List<Correction> findByCorrecter(Account correcter) {
        if (correcter == null) {
            return emptyList();
        }
        return correctionRepository.findByCorrecter(correcter);
    }

    @Override
    public List<Correction> findByResolver(Account resolver) {
        if (resolver == null) {
            return emptyList();
        }
        return correctionRepository.findByResolver(resolver);
    }

    @Override
    public List<Correction> findByPageURL(String pageURL) {
        return null;
    }

    @Override
    public List<Correction> findAll() {
        return correctionRepository.findAll();
    }

    @Override
    public List<Correction> findAllByIds(Iterable<Long> ids) {
        if (ids == null) {
            return emptyList();
        }
        return correctionRepository.findAllById(ids);
    }

    @Override
    public Optional<Correction> findById(Long id) {
        if (id == null) {
            return empty();
        }
        return correctionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return correctionRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(Long aLong) {
        //TODO add implementation
        return false;
    }

    @Override
    public long count() {
        return correctionRepository.count();
    }

    @Override
    public Correction save(Correction entity) {
        return null;
    }

    @Override
    public List<Correction> saveAll(Iterable<Correction> entities) {
        if (entities == null) {
            return null;
        }
        return correctionRepository.saveAll(entities);
    }

    @Override
    public void delete(Correction entity) {
        if (entity == null) {
            return;
        }

        try {
            correctionRepository.delete(entity);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Delete non existing entity with id=" + entity.getId(), e);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            return;
        }

        try {
            correctionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Delete non existing entity with id=" + id, e);
        }
    }
}
