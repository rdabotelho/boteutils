package com.m2r.boteutils.parser;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

public class ParserTest {

    static private enum TokenType implements Parser.ITokenType {

        INTEGER(RegexConst.INTEGER),
        OPERATOR(RegexConst.OPERATOR),
        SPACE(RegexConst.SPACE);

        private Pattern regex;

        private TokenType(String regex) {
            this.regex = Pattern.compile("^("+regex+")");
        }

        public Pattern getRegex() {
            return regex;
        }

    }

    static private class MyParser extends Parser<Integer> {

        protected MyParser() {
            super(true);
        }

        @Override
        protected boolean ignoreToken(Token token) {
            return token.getType() == TokenType.SPACE;
        }

        @Override
        protected ITokenType[] getTokenTypes() {
            return TokenType.values();
        }

        @Override
        protected void sem() throws ParserException {
            String operator = getToken(1).getValue();
            Integer int2 = Integer.parseInt(getToken(2).getValue());
            if (operator.equals("/") && int2 == 0) {
                throw new SemanticException("invalid division", getToken(2).getPos());
            }
        }

        @Override
        protected Integer assembly() {
            Integer int1 = Integer.parseInt(getToken(0).getValue());
            String operator = getToken(1).getValue();
            Integer int2 = Integer.parseInt(getToken(2).getValue());
            switch (operator) {
                case "+": return int1 + int2;
                case "-": return int1 - int2;
                case "*": return int1 * int2;
                case "/": return int1 / int2;
                default: return null;
            }
        }

        /**
         * Grammar
         * CALC	    -> <INTEGER> <OPERATOR> <INTEGER>
         */
        @Override
        protected boolean grammar() {
            return calc();
        }

        protected boolean calc() {
            int start = pos;
            return (term(TokenType.INTEGER) && term(TokenType.OPERATOR) && term(TokenType.INTEGER)) || reset(start);
        }

    }

    @Test
    public void testSuccess() throws ParserException {
        MyParser parser = new MyParser();

        Integer result = parser.execute("25 + 5");
        assertEquals(result, Integer.valueOf(30));

        result = parser.execute("25 - 5");
        assertEquals(result, Integer.valueOf(20));

        result = parser.execute("25 * 5");
        assertEquals(result, Integer.valueOf(125));

        result = parser.execute("25 / 5");
        assertEquals(result, Integer.valueOf(5));
    }

    @Test
    public void testLexErro() throws ParserException {
        MyParser parser = new MyParser();
        try {
            parser.execute("25 + A");
            assertTrue(false);
        }
        catch (Parser.LexicalException e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testSynErro() throws ParserException {
        MyParser parser = new MyParser();
        try {
            parser.execute("25 5 +");
            assertTrue(false);
        }
        catch (Parser.SyntacticException e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testSemErro() throws ParserException {
        MyParser parser = new MyParser();
        try {
            parser.execute("25 / 0");
            assertTrue(false);
        }
        catch (Parser.SemanticException e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }

}