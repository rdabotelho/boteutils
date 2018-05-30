package com.m2r.scaffolding.utils;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserScript {
	
	private List<Token> tokens;
	private int pos;

	public static void parser(String sequence, Map<String, String> properties, int newId) throws ParseException {
		ParserScript p = new ParserScript();
		p.execute(sequence, properties, newId);
	}
	
	private ParserScript() {
		super();
	}

	private void execute(String sequence, Map<String, String> properties, int newId) throws ParseException {
		lex(sequence);
		syn();
		sem(properties, newId);
	}
	
	private void lex(String sequence) throws ParseException {
		tokens = new LinkedList<>();
		Token token;
		pos = 0;
		while ((token = nextToken(sequence)) != null) {
			if (!TokenType.WHITESPACE.equals(token.getType())) {
				tokens.add(token);
			}
		}
	}
	
	private void sem(Map<String, String> properties, int newId) throws ParseException {
		Token tokenName = getTokens().get(1);
		Token tokenType = getTokens().get(3);
		boolean hasParams = getTokens().size() > 4;
		
		properties.put("MODEL_PROPERTY_NAME_" + newId, tokenName.getValue());
		properties.put("MODEL_PROPERTY_LABEL_" + newId, NameUtils.modelPropertyToLabelValue(NameUtils.modelNameToModelProperty(tokenName.getValue())));
		properties.put("MODEL_PROPERTY_COLUMN_NAME_" + newId, NameUtils.modelPropertyToEnum(NameUtils.modelNameToModelProperty(tokenName.getValue())));
		
		properties.put("MODEL_PROPERTY_ENUM_NAME_" + newId, null);
		properties.put("MODEL_PROPERTY_RELATED_MODEL_" + newId, null);
		properties.put("MODEL_PROPERTY_SELECT_ITEM_LABEL_" + newId, null);
		properties.put("MODEL_PROPERTY_SELECT_WITH_FILTER_" + newId, null);
		properties.put("MODEL_PROPERTY_LENGTH_" + newId, null);
		properties.put("MODEL_PROPERTY_PROPERTY_TRANSIENT_" + newId, null);
		properties.put("MODEL_PROPERTY_DECIMAL_PRECISION_" + newId, null);
		properties.put("MODEL_PROPERTY_DECIMAL_SCALE_" + newId, null);
		properties.put("MODEL_PROPERTY_DECIMAL_PLACES_" + newId, null);
		properties.put("MODEL_PROPERTY_DECIMAL_SEPARATOR_" + newId, null);
		properties.put("MODEL_PROPERTY_DECIMAL_SYMBOL_" + newId, null);
		properties.put("MODEL_PROPERTY_DECIMAL_PATTERN_" + newId, null);
		properties.put("MODEL_PROPERTY_COLUMN_WIDTH_" + newId, null);
		properties.put("MODEL_PROPERTY_REQUIRED_" + newId, "0");
		properties.put("MODEL_PROPERTY_DISABLED_" + newId, "0");
		properties.put("MODEL_PROPERTY_FILTER_" + newId, "0");
		properties.put("MODEL_PROPERTY_VIEWED_" + newId, "1");
		properties.put("MODEL_PROPERTY_VIEWED_ON_TABLE_" + newId, "1");
		properties.put("REPEAT_PROPERTY_BEGIN_" + newId, "1");
		
		switch (tokenType.getValue()) {
		case "String":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "0");
			if (hasParams) {
				properties.put("MODEL_PROPERTY_LENGTH_" + newId, getTokens().get(5).getValue());
			}
			break;
		case "Integer":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "2");
			break;
		case "Long":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "3");
			break;
		case "BigInteger":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "4");
			break;
		case "Float":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "5");
			break;
		case "Double":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "6");
			break;
		case "BigDecimal":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "7");
			properties.put("MODEL_PROPERTY_DECIMAL_PRECISION_" + newId, "8");
			properties.put("MODEL_PROPERTY_DECIMAL_SCALE_" + newId, "2");
			break;
		case "LocalDate":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "8");
			break;
		case "LocalTime":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "9");
			break;
		case "LocalDateTime":
			properties.put("MODEL_PROPERTY_TYPE_" + newId, "10");
			break;
		default:
			if (tokenType.getValue().endsWith("Enum") ) {
				properties.put("MODEL_PROPERTY_TYPE_" + newId, "11");
				properties.put("MODEL_PROPERTY_ENUM_NAME_" + newId, tokenType.getValue());
				if (hasParams) {
					StringBuilder str = new StringBuilder();
					for (int i=5; i<(getTokens().size()-1); i++) {
						str.append(getTokens().get(i).getValue());
					}
					properties.put("MODEL_PROPERTY_ENUM_VALUES_" + newId, str.toString());
				}
			}
			else if (tokenType.getValue().startsWith("Set")) {
				properties.put("MODEL_PROPERTY_TYPE_" + newId, "13");
				properties.put("MODEL_PROPERTY_RELATED_MODEL_" + newId, getTokens().get(5).getValue());
			}
			else if (tokenType.getValue().startsWith("List")) {
				properties.put("MODEL_PROPERTY_TYPE_" + newId, "14");				
				properties.put("MODEL_PROPERTY_RELATED_MODEL_" + newId, getTokens().get(5).getValue());
			}
			else {
				properties.put("MODEL_PROPERTY_TYPE_" + newId, "12");
			}
		}
		
	}
	
	public List<Token> getTokens() {
		return tokens;
	}
	
	private Token nextToken(String sequence) throws ParseException {
		String subSequence = sequence.substring(pos);
		for (TokenType type : TokenType.values()) {
			Matcher m = type.getRegex().matcher(subSequence);
			if (m.find()) {
				String value = m.group().trim();
				pos += m.group().length();
				return new Token(type, value, pos);
			}
		}
		if (subSequence.length() > 0) {
			throw new ErroLexicoException("Token não identificado na posição " + (pos+1), pos+1);
		}
		return null;
	}
	
	static private class Token {
		
		public TokenType type;
		public String value;
		private int pos;

		public Token(TokenType type, String value, int pos) {
			this.type = type;
			this.value = value;
			this.pos = pos;
		}
		
		public TokenType getType() {
			return type;
		}
		
		public String getValue() {
			return value;
		}
		
		public int getPos() {
			return pos;
		}
		
		@Override
		public String toString() {
			return "<"+type.name()+","+value+">";
		}
		
	}
	
	static private enum TokenType {
		
		ID("[a-zA-Z][a-zA-Z0-9_]*"),
		NUMBER("\\d+"),
		DELIMITER("[\\:\\-\\<\\>\\,]"),
		WHITESPACE("\\s");
		
		private Pattern regex;

		private TokenType(String regex) {
			this.regex = Pattern.compile("^("+regex+")");
		}
		
		public Pattern getRegex() {
			return regex;
		}
		
	}
	
	/**
	 * Gramática Livre de Contexto
	 * 
	 * ATTR			-> - <ID> : <ID> ATTR-BODY
	 * ATTR-BODY 	-> < LIST-PARAMS > | <END>
	 * LIST-PARAMS 	-> PARAM , LIST-PARAMS | PARAM 
	 * PARAM 		-> <ID> | <NUMBER> 
	 * 
	 */
	
	private void syn() throws ParseException {
		pos = 0;
		if (!(attr() && isEnd())) {
			throw new ErroSintaticoException("Estrutura da expressão incorreta", pos+1);
		}
	}
	
	private boolean attr() {
		int start = pos;
		return ((term(TokenType.DELIMITER, "-") && term(TokenType.ID) && term(TokenType.DELIMITER, ":") && term(TokenType.ID)) && attr_body() || reset(start));
	}
	
	private boolean attr_body() {
		int start = pos;
		return ((term(TokenType.DELIMITER, "<") && list_params() && term(TokenType.DELIMITER, ">")) || reset(start)) ||
			   (isEnd() || reset(start));
	}
	
	private boolean list_params() {
		int start = pos;
		return ((param() && term(TokenType.DELIMITER, ",") && list_params()) || reset(start)) ||
			   (param() || reset(start));
	}
	
	private boolean param() {
		int start = pos;
		return ((term(TokenType.ID)) || reset(start)) ||
			   (term(TokenType.NUMBER) || reset(start));
	}

	private boolean term(TokenType type) {
		return term(type, null);
	}
	
	private boolean term(TokenType type, String value) {
		Token token = nextToken();
		return token != null && token.getType().equals(type) && (value == null || value.equals(token.getValue()));
	}

	private Token nextToken() {
		return tokens.size() > pos ? tokens.get(pos++) : null;
	}

	private boolean reset(int newPos) {
		pos = newPos;
		return false;
	}
	
	private boolean isEnd() {
		return pos == tokens.size();
	}
	
	@SuppressWarnings("serial")
	public static class ErroLexicoException extends ParseException {
		public ErroLexicoException(String s, int errorOffset) {
			super(s, errorOffset);
		}
	}
	@SuppressWarnings("serial")
	public static class ErroSintaticoException extends ParseException {
		public ErroSintaticoException(String s, int errorOffset) {
			super(s, errorOffset);
		}
	}
	@SuppressWarnings("serial")
	public static class ErroSemanticoException extends ParseException {
		public ErroSemanticoException(String s, int errorOffset) {
			super(s, errorOffset);
		}
	}
	
}
