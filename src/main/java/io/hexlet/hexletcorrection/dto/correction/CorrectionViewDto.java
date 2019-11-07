package io.hexlet.hexletcorrection.dto.correction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorrectionViewDto implements Comparable<CorrectionViewDto> {

    @NotNull(message = "Id " + NOT_NULL)
    private Long id;

    @Size(message = "Reporter's comment not be more than " + MAX_COMMENT_LENGTH + " characters", max = MAX_COMMENT_LENGTH)
    private String reporterComment;

    @Size(message = "Correcter's comment not be more than " + MAX_COMMENT_LENGTH + " characters", max = MAX_COMMENT_LENGTH)
    private String correcterComment;

    @Size(message = "Resolver's comment not be more than " + MAX_COMMENT_LENGTH + " characters", max = MAX_COMMENT_LENGTH)
    private String resolverComment;

    @NotNull(message = "text before highlight text" + NOT_NULL)
    private String beforeHighlightText;

    @NotNull(message = "Highlight text " + NOT_NULL)
    private String highlightText;

    @NotNull(message = "text after highlight text " + NOT_NULL)
    private String afterHighlightText;

    @NotBlank(message = "Reporter " + NOT_EMPTY)
    private String reporter;

    @JsonIgnoreProperties({"correctionsResolved", "correctionsInProgress"})
    private AccountGetDto correcter;

    @JsonIgnoreProperties({"correctionsResolved", "correctionsInProgress"})
    private AccountGetDto resolver;

    @NotBlank(message = "URL " + NOT_EMPTY)
    private String pageURL;

    @Override
    public int compareTo(CorrectionViewDto correctionViewDto) {
        return this.id.compareTo(correctionViewDto.getId());
    }
}
