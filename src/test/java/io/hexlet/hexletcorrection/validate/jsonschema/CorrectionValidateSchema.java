package io.hexlet.hexletcorrection.validate.jsonschema;

import com.github.reinert.jjschema.v1.JsonSchemaFactory;
import com.github.reinert.jjschema.v1.JsonSchemaV4Factory;
import com.github.reinert.jjschema.v1.SchemaWrapper;
import io.hexlet.hexletcorrection.controller.AbstractControllerTest;
import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.domain.Correction;
import io.hexlet.hexletcorrection.dto.correction.CorrectionGetDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPostDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionPutDto;
import io.hexlet.hexletcorrection.dto.correction.CorrectionViewDto;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.reinert.jjschema.v1.SchemaWrapperFactory.createArrayWrapper;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.API_PATH_V1;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.CORRECTIONS_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.TEST_HOST;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CorrectionValidateSchema extends AbstractControllerTest {

    @LocalServerPort
    private int port;

    private static JsonSchemaFactory schemaFactory;

    private Account testAccountResolver;
    private Account testAccountCorrecter;
    private Correction testCorrectionOne;
    private Correction testCorrectionTwo;

    @BeforeClass
    public static void initialize() {
        schemaFactory = new JsonSchemaV4Factory();
        schemaFactory.setAutoPutDollarSchema(true);
    }

    @Before
    public void setUp() {
        testAccountResolver = createAccount("testAccountResolver", "testAccountResolver@email.com");
        testAccountCorrecter = createAccount("testAccountCorrecter", "testAccountCorrecter@email.com");
        testCorrectionOne = createCorrection(testAccountResolver, testAccountCorrecter);
        testCorrectionTwo = createCorrection(testAccountResolver, testAccountCorrecter);
    }

    @After
    public void tearDown() {
        deleteAccount(testAccountResolver.getId());
        deleteAccount(testAccountCorrecter.getId());
        testAccountResolver = null;
        testAccountCorrecter = null;
        testCorrectionOne = null;
        testCorrectionTwo = null;
    }

    @Test
    public void correctionViewDtoSchemaValidationTest() {
        String correctionViewDtoSchema = schemaFactory.createSchema(CorrectionViewDto.class).toString();

        given().when()
            .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH + "/" + testCorrectionOne.getId())
            .then()
            .assertThat()
            .body(matchesJsonSchema(correctionViewDtoSchema));
    }

    @Test
    public void correctionGetDtoSchemaValidationTest() {
        SchemaWrapper arrayWrapper = createArrayWrapper(List.class, CorrectionGetDto.class, false);
        String correctionGetDtoSchema = arrayWrapper.putDollarSchema().asJson().toString();

        given().when()
            .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH)
            .then()
            .assertThat()
            .body(matchesJsonSchema(correctionGetDtoSchema));
    }

    @Test
    public void correctionPutDtoSchemaValidationTest() {
        String correctionPutDtoSchema = schemaFactory.createSchema(CorrectionPutDto.class).toString();

        given().when()
            .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH + "/edit/" + testCorrectionOne.getId())
            .then()
            .assertThat()
            .body(matchesJsonSchema(correctionPutDtoSchema));
    }

    @Test
    public void correctionPostDtoSchemaValidationTest() {
        String correctionPostDtoSchema = schemaFactory.createSchema(CorrectionPostDto.class).toString();

        given().when()
            .get(TEST_HOST + ":" + port + API_PATH_V1 + CORRECTIONS_PATH + "/new")
            .then()
            .assertThat()
            .body(matchesJsonSchema(correctionPostDtoSchema));
    }
}
