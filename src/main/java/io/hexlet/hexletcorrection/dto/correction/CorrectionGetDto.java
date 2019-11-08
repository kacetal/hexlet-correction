package io.hexlet.hexletcorrection.dto.correction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.reinert.jjschema.Attributes;
import com.github.reinert.jjschema.Nullable;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_ID_ERROR_NULL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_ERROR_LENGTH_SIZE;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_NAME_LENGTH_MIN;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorrectionGetDto implements Comparable<CorrectionGetDto> {

    @NotNull(message = CORRECTION_ID_ERROR_NULL)
    @Nullable
    @Attributes(required = true)
    private Long id;

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

    @JsonIgnoreProperties({"correctionsResolved", "correctionsInProgress"})
    private AccountGetDto correcter;

    @JsonIgnoreProperties({"correctionsResolved", "correctionsInProgress"})
    private AccountGetDto resolver;

    @NotBlank(message = CORRECTION_URL_ERROR_BLANK)
    private String pageURL;

    @Override
    public int compareTo(CorrectionGetDto correctionGetDto) {
        return this.id.compareTo(correctionGetDto.getId());
    }
}
