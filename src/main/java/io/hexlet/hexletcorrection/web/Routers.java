package io.hexlet.hexletcorrection.web;

public class Routers {

    static final String API = "/api";

    static final String TYPOS = "/typos";

    public static final String API_TYPOS = API + TYPOS;

    static final String REDIRECT_TO_TYPO_ROOT = "redirect:" + TYPOS + "/";

    static final String ACCOUNTS = "/accounts";

    static final String API_ACCOUNTS = API + ACCOUNTS;

    static final String REDIRECT_TO_ACCOUNT_ROOT = "redirect:" + ACCOUNTS + "/";
}
