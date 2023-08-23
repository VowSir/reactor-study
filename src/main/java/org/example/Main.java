package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;

public class Main {

//	private static final Pattern PATTERN = Pattern.compile(".*nulls last$", Pattern.CASE_INSENSITIVE);
	private static final Pattern PATTERN = Pattern.compile(".*nulls last", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	public static void main(String[] args) {
		System.out.println("Hello world!");

//		handleNode();
//		handleReg();
//		basicAuth("mspbots@shartega.com","3g1kH0AOZsmXjM0Rdou2iy7FZ3UNW4RP4viMd+KT2R0LEti5");  // auvik
		basicAuth("TNP+cuTXRINqC6VWawIA","wCfo4fYbkJDDNql0");  // connectwisemanage

	}

	public static void handleNode() {

		JsonNodeFactory factory = new JsonNodeFactory(false);
		JsonNode textNode = factory.textNode("Hello");
		JsonNode textNode1 = factory.textNode("123");
		JsonNode intNode = factory.numberNode(123);
		JsonNode nullNode = factory.nullNode();

		System.out.println(textNode.asText());  // 输出 "Hello"
		System.out.println(textNode.textValue());  // 输出 "Hello"

		System.out.println(textNode1.asText());  // 输出 "123"
		System.out.println(textNode1.textValue());  // 输出 "123"

		System.out.println(intNode.asText());  // 输出 "123"
		System.out.println(intNode.textValue());  // 输出 null

		System.out.println(nullNode.asText());  // 输出 ""
		System.out.println(nullNode.textValue());  // 输出 null
	}

	public static void handleReg() {
		String s =
				" and su.username not like '%'||replace(sump3.teams_user_id,'-','')||'%'\n" +
						") as tdetail) as twrap  order by \"User Name\" ASC nulls last";

		System.out.println(PATTERN.matcher(s).matches());
	}

	public static void basicAuth(String username,String apiKey) {
		String auth = username + ":" + apiKey;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		System.out.println(authHeader);
	}



}