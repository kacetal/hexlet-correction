package io.hexlet.hexletcorrection.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hexlet.hexletcorrection.domain.Typo;
import io.hexlet.hexletcorrection.repository.TypoRepository;
import io.hexlet.hexletcorrection.web.vm.TypoReport;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static io.hexlet.hexletcorrection.domain.enumerator.TypoStatus.REPORTED;
import static io.hexlet.hexletcorrection.web.Routers.API_TYPOS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TypoApiIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TypoRepository typoRepository;

    @Transactional
    @ParameterizedTest
    @MethodSource("io.hexlet.hexletcorrection.service.mapping.TypoMapperTest#getTestTypoReport")
    public void postTypo(final TypoReport report) throws Exception {
        final var contentAsString = mockMvc.perform(post(API_TYPOS)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(report)))
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.pageUrl", is(report.getPageUrl())))
            .andExpect(jsonPath("$.reporterName", is(report.getReporterName())))
            .andExpect(jsonPath("$.reporterRemark", is(report.getReporterRemark())))
            .andExpect(jsonPath("$.textBeforeTypo", is(report.getTextBeforeTypo())))
            .andExpect(jsonPath("$.textTypo", is(report.getTextTypo())))
            .andExpect(jsonPath("$.textAfterTypo", is(report.getTextAfterTypo())))
            .andExpect(jsonPath("$.typoStatus", is(REPORTED.toString())))
            .andReturn().getResponse().getContentAsString();
        final var savedTypo = objectMapper.readValue(contentAsString, Typo.class);
        assertThat(typoRepository.existsById(savedTypo.getId())).isTrue();
    }
}
