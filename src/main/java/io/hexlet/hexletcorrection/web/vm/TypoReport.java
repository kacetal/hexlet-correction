package io.hexlet.hexletcorrection.web.vm;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * View Model object for receiving the typo report.
 */
@Data
@Accessors(chain = true)
public class TypoReport {

    @NotBlank
    private String pageUrl;

    @NotNull
    @Size(max = 50)
    private String reporterName;

    @Size(max = 1000)
    private String reporterRemark;

    @Size(max = 100)
    private String textBeforeTypo;

    @NotNull
    @Size(max = 1000)
    private String textTypo;

    @Size(max = 100)
    private String textAfterTypo;
}
