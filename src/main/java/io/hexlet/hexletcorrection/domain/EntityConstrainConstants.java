package io.hexlet.hexletcorrection.domain;

public class EntityConstrainConstants {


    //Account
    public static final String ACCOUNT_PATTERN_STRING = "^(.*)$";
    public static final String ACCOUNT_PATTERN_EMAIL = "^(.*)$";
    public static final String ACCOUNT_PATTERN_PASSWORD = "^(.*)$";

    public static final String ACCOUNT_ID_ERROR_NULL = "Account id must not be null";

    public static final int ACCOUNT_USERNAME_LENGTH_MIN = 2;
    public static final int ACCOUNT_USERNAME_LENGTH_MAX = 50;
    public static final String ACCOUNT_USERNAME_ERROR_LENGTH_SIZE = "Username length must be between {min} and {max} characters";
    public static final String ACCOUNT_USERNAME_ERROR_BLANK = "Username must not be blank";

    public static final int ACCOUNT_LAST_NAME_LENGTH_MAX = 50;
    public static final String ACCOUNT_LAST_NAME_ERROR_LENGTH_MAX = "Last name length must be less than or equal to {value} characters";

    public static final int ACCOUNT_FIRST_NAME_LENGTH_MAX = 50;
    public static final String ACCOUNT_FIRST_NAME_ERROR_LENGTH_MAX = "First name length must be less than or equal to {value} characters";

    public static final int ACCOUNT_PASSWORD_LENGTH_MIN = 6;
    public static final String ACCOUNT_PASSWORD_ERROR_LENGTH_MIN = "Password length must be greater than or equal to {value} characters";

    public static final String ACCOUNT_EMAIL_ERROR_INVALID = "Email must be a well-formed address";

    public static final String ACCOUNT_NBR_COR_IN_PROGRESS_ERROR_NULL = "Number of corrections in progress must not be null";
    public static final String ACCOUNT_NBR_COR_RESOLVED_ERROR_NULL = "Number of corrections resolved must not be null";

    //Correction
    public static final String CORRECTION_ID_ERROR_NULL = "Correction id must not be null";

    public static final String CORRECTION_URL_ERROR_BLANK = "Page URL must not be blank";

    public static final int CORRECTION_REP_COMMENT_LENGTH_MAX = 200;
    public static final String CORRECTION_REP_COMMENT_ERROR_LENGTH_MAX = "Reporter's comment length must be less than or equal to {value} characters";
    public static final String CORRECTION_REP_COMMENT_ERROR_BLANK = "Reporter's comment must not be blank";

    public static final int CORRECTION_COR_COMMENT_LENGTH_MAX = 200;
    public static final String CORRECTION_COR_COMMENT_ERROR_LENGTH_MAX = "Correcter's comment length must be less than or equal to {value} characters";
    public static final String CORRECTION_COR_COMMENT_ERROR_BLANK = "Correcter's comment must not be blank";

    public static final int CORRECTION_RES_COMMENT_LENGTH_MAX = 200;
    public static final String CORRECTION_RES_COMMENT_ERROR_LENGTH_MAX = "Resolver's comment length must be less than or equal to {value} characters";
    public static final String CORRECTION_RES_COMMENT_ERROR_BLANK = "Resolver's comment must not be blank";

    public static final int CORRECTION_TEXT_BEFORE_CORRECTION_LENGTH_MAX = 1000;
    public static final String CORRECTION_TEXT_BEFORE_CORRECTION_ERROR_LENGTH_MAX = "Length of text before correction must be less than or equal to {value} characters";

    public static final int CORRECTION_TEXT_CORRECTION_LENGTH_MIN = 1;
    public static final int CORRECTION_TEXT_CORRECTION_LENGTH_MAX = 200;
    public static final String CORRECTION_TEXT_CORRECTION_ERROR_LENGTH_SIZE = "Correction length must be between {min} and {max} characters";
    public static final String CORRECTION_TEXT_CORRECTION_ERROR_NULL = "Text correction must not be null";

    public static final int CORRECTION_TEXT_AFTER_CORRECTION_LENGTH_MAX = 1000;
    public static final String CORRECTION_TEXT_AFTER_CORRECTION_ERROR_LENGTH_MAX = "Length of text after correction must be less than or equal to {value} characters";

    public static final int CORRECTION_REPORTER_NAME_LENGTH_MIN = 50;
    public static final int CORRECTION_REPORTER_NAME_LENGTH_MAX = 50;
    public static final String CORRECTION_REPORTER_ERROR_LENGTH_SIZE = "Reporter name length must be between {min} and {max} characters";
    public static final String CORRECTION_REPORTER_ERROR_BLANK = "Reporter name must not be blank";

    public static final String NOT_EMPTY = "must not be empty";

    public static final String NOT_NULL = "must not be null";
}
