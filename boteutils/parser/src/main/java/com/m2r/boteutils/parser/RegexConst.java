package com.m2r.boteutils.parser;

public interface RegexConst {

    public static String IDENTIFIER = "[a-zA-Z][a-zA-Z0-9_\\.\\<\\>]*";
    public static String DELIMITER = "[\\{\\}\\(\\)\\;\\,\\[,\\],:]";
    public static String OPERATOR = "[\\+\\-\\*\\/]";
    public static String STRING = "'(.*?)'|\"(.*?)\"";
    public static String STRING_SIMPLE_QUOTES = "'(.*?)'";
    public static String STRING_DOUBLE_QUOTES = "\"(.*?)\"";
    public static String INTEGER = "[+-]?\\d+";
    public static String FLOAT = "[+-]?\\d+\\.?\\d+";
    public static String SPACE = "\\s";
    public static String EVERYTHING = ".";

}
