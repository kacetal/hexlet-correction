package io.hexlet.hexletcorrection.controller.api.v1;

import io.hexlet.hexletcorrection.controller.AbstractControllerTest;
import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.hexlet.hexletcorrection.controller.ControllerConstants.API_PATH_V1;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.CORRECTIONS_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.TEST_HOST;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.MAX_COMMENT_LENGTH;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_EMPTY;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_NULL;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CorrectionControllerTest extends AbstractControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void getAllCorrectionsTest() {
        final Account account = createAccount(LOGIN, "getAllCorrections@mail.com");
        createCorrection(account, account);
        given().when()
                .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);
    }

    @Test
    public void getAllCorrectionsRecursionInfiniteTest() {
        createCorrection();
        List<Correction> list = given().when()
                .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getList("account.corrections", Correction.class);
        Assert.assertNull(list.get(0));
        assertThatExceptionOfType(StackOverflowError.class);
    }

    @Test
    public void getCorrectionByIdTest() {
        Correction savedCorrection = createCorrection();

        given().when()
                .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH + "/" + savedCorrection.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);
    }

    @Test
    public void getCorrectionByFalseIdTest() {
        long falseId = createCorrection().getId() + 1L;

        given().when()
                .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH + "/" + falseId)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(ContentType.JSON)
                .body("correction", equalTo("Correction with ID '" + falseId + "' not found"));
    }

    @Test
    public void getCorrectionByUrlTest() {
        Correction savedCorrection = createCorrection();

        given().when()
                .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH + "/?url=" + savedCorrection.getPageURL())
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);
    }

    @Test
    public void postCorrectionTest() {
        Correction correction = getCorrection();

        given().when()
                .body(correction)
                .contentType(ContentType.JSON)
                .post(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void postCorrectionCommentEmptyTest() {
        Correction correction = getCorrection();

        given().when()
                .body(correction)
                .contentType(ContentType.JSON)
                .post(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("comment", equalTo("Comment " + NOT_EMPTY));
    }

    @Test
    public void postCorrectionCommentTooLongTest() {
        Correction correction = getCorrection();

        given().when()
                .body(correction)
                .contentType(ContentType.JSON)
                .post(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("comment", equalTo("Comment not be more than " + MAX_COMMENT_LENGTH + " characters"));
    }

    @Test
    public void postCorrectionHighlightTextEmptyTest() {
        Correction correction = getCorrection();

        given().when()
                .body(correction)
                .contentType(ContentType.JSON)
                .post(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("highlightText", equalTo("Highlight text " + NOT_EMPTY));
    }

    @Test
    public void postCorrectionAccountNullTest() {
        Correction correction = getCorrection();

        given().when()
                .body(correction)
                .contentType(ContentType.JSON)
                .post(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("account", equalTo("Account " + NOT_NULL));
    }

    @Test
    public void postCorrectionURLEmptyTest() {
        Correction correction = getCorrection();

        given().when()
                .body(correction)
                .contentType(ContentType.JSON)
                .post(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("pageURL", equalTo("URL " + NOT_EMPTY));
    }

    @Test
    public void deleteCorrectionTest() {
        Correction savedCorrection = createCorrection();

        given().when()
                .delete(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH + "/" + savedCorrection.getId())
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
