package io.hexlet.hexletcorrection.dto.correction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.MAX_COMMENT_LENGTH;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_EMPTY;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorrectionPostDto implements Comparable<CorrectionPostDto>{

    @Size(message = "Reporter's comment not be more than " + MAX_COMMENT_LENGTH + " characters", max = MAX_COMMENT_LENGTH)
    private String reporterComment;

    @NotNull(message = "text before highlight text" + NOT_NULL)
    private String beforeHighlightText;

    @NotNull(message = "Highlight text " + NOT_NULL)
    private String highlightText;

    @NotNull(message = "text after highlight text " + NOT_NULL)
    private String afterHighlightText;

    @NotBlank(message = "Reporter " + NOT_EMPTY)
    private String reporter;

    @NotBlank(message = "URL " + NOT_EMPTY)
    private String pageURL;

    @Override
    public int compareTo(CorrectionPostDto correctionPostDto) {
        return this.reporter.compareTo(correctionPostDto.getReporter());
    }
}
