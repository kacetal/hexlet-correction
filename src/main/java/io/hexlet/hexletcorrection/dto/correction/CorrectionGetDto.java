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

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_EMPTY;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorrectionGetDto implements Comparable<CorrectionGetDto> {

    @NotNull(message = "Id " + NOT_NULL)
    private Long id;

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
    public int compareTo(CorrectionGetDto correctionGetDto) {
        return this.id.compareTo(correctionGetDto.getId());
    }
}
