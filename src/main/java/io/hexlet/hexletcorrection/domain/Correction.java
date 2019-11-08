package io.hexlet.hexletcorrection.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_COR_COMMENT_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_COR_COMMENT_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_COR_COMMENT_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_ERROR_LENGTH_SIZE;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REPORTER_NAME_LENGTH_MIN;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_REP_COMMENT_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_RES_COMMENT_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_RES_COMMENT_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.CORRECTION_RES_COMMENT_LENGTH_MAX;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "correction")
public class Correction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporter_comment", length = CORRECTION_REP_COMMENT_LENGTH_MAX)
    @Max(message = CORRECTION_REP_COMMENT_ERROR_LENGTH_MAX, value = CORRECTION_REP_COMMENT_LENGTH_MAX)
    @NotBlank(message = CORRECTION_REP_COMMENT_ERROR_BLANK)
    private String reporterComment;

    @Column(name = "correcter_comment")
    @Max(message = CORRECTION_COR_COMMENT_ERROR_LENGTH_MAX, value = CORRECTION_COR_COMMENT_LENGTH_MAX)
    @NotBlank(message = CORRECTION_COR_COMMENT_ERROR_BLANK)
    private String correcterComment;

    @Column(name = "resolver_comment")
    @Max(message = CORRECTION_RES_COMMENT_ERROR_LENGTH_MAX, value = CORRECTION_RES_COMMENT_LENGTH_MAX)
    @NotBlank(message = CORRECTION_RES_COMMENT_ERROR_BLANK)
    private String resolverComment;

    @Column(name = "text_before_correction", updatable = false, nullable = false, length = CORRECTION_TEXT_BEFORE_CORRECTION_LENGTH_MAX)
    @Max(message = CORRECTION_TEXT_BEFORE_CORRECTION_ERROR_LENGTH_MAX, value = CORRECTION_TEXT_BEFORE_CORRECTION_LENGTH_MAX)
    private String textBeforeCorrection;

    @Column(name = "text_correction", updatable = false, nullable = false, length = CORRECTION_TEXT_CORRECTION_LENGTH_MAX)
    @Size(message = CORRECTION_TEXT_CORRECTION_ERROR_LENGTH_SIZE,
        min = CORRECTION_TEXT_CORRECTION_LENGTH_MIN,
        max = CORRECTION_TEXT_CORRECTION_LENGTH_MAX)
    @NotNull(message = CORRECTION_TEXT_CORRECTION_ERROR_NULL)
    private String textCorrection;

    @Column(name = "text_after_correction", updatable = false, nullable = false, length = CORRECTION_TEXT_AFTER_CORRECTION_LENGTH_MAX)
    @Max(message = CORRECTION_TEXT_AFTER_CORRECTION_ERROR_LENGTH_MAX, value = CORRECTION_TEXT_AFTER_CORRECTION_LENGTH_MAX)
    private String textAfterCorrection;

    @Column(name = "reporter_name", updatable = false, nullable = false, length = CORRECTION_REPORTER_NAME_LENGTH_MAX)
    @Size(message = CORRECTION_REPORTER_ERROR_LENGTH_SIZE,
        min = CORRECTION_REPORTER_NAME_LENGTH_MIN,
        max = CORRECTION_REPORTER_NAME_LENGTH_MAX)
    @NotBlank(message = CORRECTION_REPORTER_ERROR_BLANK)
    private String reporterName;

    @ManyToOne
    @JsonIgnoreProperties({"correctionsResolved", "correctionsInProgress"})
    private Account correcter;

    @ManyToOne
    @JsonIgnoreProperties({"correctionsResolved", "correctionsInProgress"})
    @JoinColumn(updatable = false)
    private Account resolver;

    @NotBlank(message = CORRECTION_URL_ERROR_BLANK)
    @Column(name = "page_url", nullable = false)
    private String pageURL;
}
