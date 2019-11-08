package io.hexlet.hexletcorrection.dto.correction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_COR_COMMENT_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_COR_COMMENT_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_COR_COMMENT_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_ID_ERROR_NULL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_RES_COMMENT_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_RES_COMMENT_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_RES_COMMENT_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_URL_ERROR_BLANK;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorrectionPutDto implements Comparable<CorrectionPutDto> {

    @NotNull(message = CORRECTION_ID_ERROR_NULL)
    private Long id;

    @Max(message = CORRECTION_REP_COMMENT_ERROR_LENGTH_MAX, value = CORRECTION_REP_COMMENT_LENGTH_MAX)
    @NotBlank(message = CORRECTION_REP_COMMENT_ERROR_BLANK)
    private String reporterComment;

    @Max(message = CORRECTION_COR_COMMENT_ERROR_LENGTH_MAX, value = CORRECTION_COR_COMMENT_LENGTH_MAX)
    @NotBlank(message = CORRECTION_COR_COMMENT_ERROR_BLANK)
    private String correcterComment;

    @Max(message = CORRECTION_RES_COMMENT_ERROR_LENGTH_MAX, value = CORRECTION_RES_COMMENT_LENGTH_MAX)
    @NotBlank(message = CORRECTION_RES_COMMENT_ERROR_BLANK)
    private String resolverComment;

    @NotBlank(message = CORRECTION_URL_ERROR_BLANK)
    private String pageURL;

    @Override
    public int compareTo(CorrectionPutDto correctionPutDto) {
        return this.id.compareTo(correctionPutDto.getId());
    }
}
