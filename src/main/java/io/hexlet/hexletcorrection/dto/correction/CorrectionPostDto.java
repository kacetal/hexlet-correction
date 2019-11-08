package io.hexlet.hexletcorrection.dto.correction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_ERROR_LENGTH_SIZE;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_NAME_LENGTH_MIN;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_TEXT_AFTER_CORRECTION_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_TEXT_AFTER_CORRECTION_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_TEXT_BEFORE_CORRECTION_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_TEXT_BEFORE_CORRECTION_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_TEXT_CORRECTION_ERROR_LENGTH_SIZE;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_TEXT_CORRECTION_ERROR_NULL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_TEXT_CORRECTION_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_TEXT_CORRECTION_LENGTH_MIN;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_URL_ERROR_BLANK;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorrectionPostDto implements Comparable<CorrectionPostDto>{

    @Max(message = CORRECTION_REP_COMMENT_ERROR_LENGTH_MAX, value = CORRECTION_REP_COMMENT_LENGTH_MAX)
    @NotBlank(message = CORRECTION_REP_COMMENT_ERROR_BLANK)
    private String reporterComment;

    @Max(message = CORRECTION_TEXT_BEFORE_CORRECTION_ERROR_LENGTH_MAX, value = CORRECTION_TEXT_BEFORE_CORRECTION_LENGTH_MAX)
    private String textBeforeCorrection;

    @Size(message = CORRECTION_TEXT_CORRECTION_ERROR_LENGTH_SIZE,
        min = CORRECTION_TEXT_CORRECTION_LENGTH_MIN,
        max = CORRECTION_TEXT_CORRECTION_LENGTH_MAX)
    @NotNull(message = CORRECTION_TEXT_CORRECTION_ERROR_NULL)
    private String textCorrection;

    @Max(message = CORRECTION_TEXT_AFTER_CORRECTION_ERROR_LENGTH_MAX, value = CORRECTION_TEXT_AFTER_CORRECTION_LENGTH_MAX)
    private String textAfterCorrection;

    @Size(message = CORRECTION_REPORTER_ERROR_LENGTH_SIZE,
        min = CORRECTION_REPORTER_NAME_LENGTH_MIN,
        max = CORRECTION_REPORTER_NAME_LENGTH_MAX)
    @NotBlank(message = CORRECTION_REPORTER_ERROR_BLANK)
    private String reporterName;

    @NotBlank(message = CORRECTION_URL_ERROR_BLANK)
    private String pageURL;

    @Override
    public int compareTo(CorrectionPostDto correctionPostDto) {
        return this.reporterName.compareTo(correctionPostDto.getReporterName());
    }
}
