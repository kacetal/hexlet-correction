package io.hexlet.hexletcorrection.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.MAX_COMMENT_LENGTH;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_EMPTY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorrectionPutDto {

    @NotBlank(message = "Id " + NOT_EMPTY)
    private Long id;

    @NotEmpty(message = "Comment " + NOT_EMPTY)
    @Size(message = "Comment not be more than " + MAX_COMMENT_LENGTH + " characters", max = MAX_COMMENT_LENGTH)
    private String comment;

    private String beforeHighlight;

    @NotEmpty(message = "Highlight text " + NOT_EMPTY)
    private String highlightText;

    private String afterHighlight;

    @NotBlank(message = "URL " + NOT_EMPTY)
    private String pageURL;
}
