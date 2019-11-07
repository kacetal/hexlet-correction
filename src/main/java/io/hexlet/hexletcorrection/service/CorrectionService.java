package io.hexlet.hexletcorrection.service;

import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;

import java.util.List;

public interface CorrectionService extends BaseService<Correction, Long> {

    List<Correction> findByReporter(String reporter);

    List<Correction> findByCorrecter(Account correcter);

    List<Correction> findByResolver(Account correcter);

    List<Correction> findByPageURL(String pageURL);
}
