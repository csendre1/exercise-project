package edu.example.loginapp.security;

public final class JwtTokenUtils {

    public static final String SECRET_KEY = "BLgDmgxSbk";

    public static final Long EXPIRATION_DATE = 120L * 60 * 1000;

    public static final String ACCESS_TOKEN = "access_token";

    public static final String BEARER = "Bearer ";

    public static final String ERROR = "error";
}
