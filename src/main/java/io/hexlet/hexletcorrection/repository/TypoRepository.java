package io.hexlet.hexletcorrection.repository;

import io.hexlet.hexletcorrection.domain.Typo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypoRepository extends JpaRepository<Typo, Long> {

    int deleteTypoById(Long id);
}
