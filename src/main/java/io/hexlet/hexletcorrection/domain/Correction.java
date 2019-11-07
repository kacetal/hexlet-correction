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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.MAX_COMMENT_LENGTH;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_EMPTY;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_NULL;

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

    @Column(name = "reporter_comment")
    @Size(message = "Reporter's comment not be more than " + MAX_COMMENT_LENGTH + " characters", max = MAX_COMMENT_LENGTH)
    private String reporterComment;

    @Column(name = "correcter_comment")
    @Size(message = "Correcter's comment not be more than " + MAX_COMMENT_LENGTH + " characters", max = MAX_COMMENT_LENGTH)
    private String correcterComment;

    @Column(name = "resolver_comment")
    @Size(message = "Resolver's comment not be more than " + MAX_COMMENT_LENGTH + " characters", max = MAX_COMMENT_LENGTH)
    private String resolverComment;

    @Column(name = "before_highlight_text", updatable = false, nullable = false)
    @NotNull(message = "text before highlight text" + NOT_NULL)
    private String beforeHighlightText;

    @Column(name = "highlight_text", updatable = false, nullable = false)
    @NotNull(message = "Highlight text " + NOT_NULL)
    private String highlightText;

    @Column(name = "after_highlight_text", updatable = false, nullable = false)
    @NotNull(message = "text after highlight text " + NOT_NULL)
    private String afterHighlightText;

    @NotBlank(message = "Reporter " + NOT_EMPTY)
    @Column(updatable = false, nullable = false)
    private String reporter;

    @ManyToOne
    @JsonIgnoreProperties({"correctionsResolved", "correctionsInProgress"})
    private Account correcter;

    @ManyToOne
    @JsonIgnoreProperties({"correctionsResolved", "correctionsInProgress"})
    @JoinColumn(updatable = false)
    private Account resolver;

    @NotBlank(message = "URL " + NOT_EMPTY)
    @Column(name = "page_url", nullable = false)
    private String pageURL;
}
