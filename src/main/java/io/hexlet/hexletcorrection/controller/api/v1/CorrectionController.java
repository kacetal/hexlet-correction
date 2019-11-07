package io.hexlet.hexletcorrection.controller.api.v1;

import io.hexlet.hexletcorrection.controller.exception.CorrectionNotFoundException;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPostDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPutDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionViewDto;
import io.hexlet.hexletcorrection.dto.mapper.CorrectionMapper;
import io.hexlet.hexletcorrection.service.CorrectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static io.hexlet.hexletcorrection.controller.ControllerConstants.API_PATH_V1;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.CORRECTIONS_PATH;

@Slf4j
@RestController
@RequestMapping(API_PATH_V1 + CORRECTIONS_PATH)
@RequiredArgsConstructor
public class CorrectionController {

    private final CorrectionService correctionService;
    private final CorrectionMapper correctionMapper;

    @GetMapping
    public List<CorrectionGetDto> getCorrections(@RequestParam(required = false) String url) {
        if (url == null) {
            return correctionService.findAll()
                .stream()
                .map(correctionMapper::toCorrectionGetDto)
                .collect(Collectors.toList());
        }
        return correctionService.findByPageURL(url)
            .stream()
            .map(correctionMapper::toCorrectionGetDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CorrectionViewDto getCorrectionById(@PathVariable Long id) {
        final Correction correctionToView = correctionService.findById(id).orElseThrow(() -> new CorrectionNotFoundException(id));
        return correctionMapper.toCorrectionViewDto(correctionToView);
    }

    @GetMapping("/edit/{id}")
    public CorrectionPutDto getEditCorrectionById(@PathVariable Long id) {
        final Correction correctionToEdit = correctionService.findById(id).orElseThrow(() -> new CorrectionNotFoundException(id));
        return correctionMapper.toCorrectionPutDto(correctionToEdit);
    }

    @GetMapping("/new")
    public CorrectionPostDto getNewCorrection() {
        return new CorrectionPostDto();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CorrectionViewDto editCorrection(@Valid @RequestBody CorrectionPutDto correctionPutDto) {
        final Correction putDtoToSave = correctionMapper.putDtoToCorrection(correctionPutDto);
        final Correction savedCorrection = correctionService.save(putDtoToSave);
        return correctionMapper.toCorrectionViewDto(savedCorrection);
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CorrectionViewDto createCorrection(@Valid @RequestBody CorrectionPostDto correctionPostDto) {
        final Correction postDtoToSave = correctionMapper.postDtoToCorrection(correctionPostDto);
        final Correction savedCorrection = correctionService.save(postDtoToSave);
        return correctionMapper.toCorrectionViewDto(savedCorrection);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCorrection(@PathVariable Long id) {
        if (correctionService.existsById(id)) {
            correctionService.deleteById(id);
        }
        log.warn("Delete non existing entity with id=" + id);
    }
}
