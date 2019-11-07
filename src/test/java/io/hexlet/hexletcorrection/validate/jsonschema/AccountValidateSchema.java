package io.hexlet.hexletcorrection.validate.jsonschema;

import com.github.reinert.jjschema.v1.JsonSchemaFactory;
import com.github.reinert.jjschema.v1.JsonSchemaV4Factory;
import com.github.reinert.jjschema.v1.SchemaWrapper;
import io.hexlet.hexletcorrection.controller.AbstractControllerTest;
import io.hexlet.hexletcorrection.domain.Account;
import io.hexlet.hexletcorrection.dto.account.AccountGetDto;
import io.hexlet.hexletcorrection.dto.account.AccountPostDto;
import io.hexlet.hexletcorrection.dto.account.AccountPutDto;
import io.hexlet.hexletcorrection.dto.account.AccountViewDto;
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
import static io.hexlet.hexletcorrection.controller.ControllerConstants.ACCOUNTS_PATH;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.API_PATH_V1;
import static io.hexlet.hexletcorrection.controller.ControllerConstants.TEST_HOST;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountValidateSchema extends AbstractControllerTest {

    private static JsonSchemaFactory schemaFactory;
    @LocalServerPort
    private int port;
    private Account testAccountOne;
    private Account testAccountTwo;

    @BeforeClass
    public static void initialize() {
        schemaFactory = new JsonSchemaV4Factory();
        schemaFactory.setAutoPutDollarSchema(true);
    }

    @Before
    public void setUp() {
        testAccountOne = createAccount("testAccountOne", "testAccountOne@email.com");
        testAccountTwo = createAccount("testAccountTwo", "testAccountTwo@email.com");
    }

    @After
    public void tearDown() {
        deleteAccount(testAccountOne.getId());
        deleteAccount(testAccountTwo.getId());
        testAccountOne = null;
        testAccountTwo = null;
    }

    @Test
    public void accountViewDtoSchemaValidationTest() {
        String accountViewDtoSchema = schemaFactory.createSchema(AccountViewDto.class).toString();

        given().when()
            .get(TEST_HOST + ":" + port + API_PATH_V1 + ACCOUNTS_PATH + "/" + testAccountOne.getId())
            .then()
            .assertThat()
            .body(matchesJsonSchema(accountViewDtoSchema));
    }

    @Test
    public void accountGetDtoSchemaValidationTest() {
        SchemaWrapper arrayWrapper = createArrayWrapper(List.class, AccountGetDto.class, false);
        String accountGetDtoSchema = arrayWrapper.putDollarSchema().asJson().toString();

        given().when()
            .get(TEST_HOST + ":" + port + API_PATH_V1 + ACCOUNTS_PATH)
            .then()
            .assertThat()
            .body(matchesJsonSchema(accountGetDtoSchema));
    }

    @Test
    public void accountPutDtoSchemaValidationTest() {
        String accountPutDtoSchema = schemaFactory.createSchema(AccountPutDto.class).toString();

        given().when()
            .get(TEST_HOST + ":" + port + API_PATH_V1 + ACCOUNTS_PATH + "/edit/" + testAccountOne.getId())
            .then()
            .assertThat()
            .body(matchesJsonSchema(accountPutDtoSchema));
    }

    @Test
    public void accountPostDtoSchemaValidationTest() {
        String accountPostDtoSchema = schemaFactory.createSchema(AccountPostDto.class).toString();

        given().when()
            .get(TEST_HOST + ":" + port + API_PATH_V1 + ACCOUNTS_PATH + "/new")
            .then()
            .assertThat()
            .body(matchesJsonSchema(accountPostDtoSchema));
    }
}
