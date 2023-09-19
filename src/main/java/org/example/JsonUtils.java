package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class JsonUtils {
	public static  ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		System.out.println(1);
		String tree = "{\n" +
				"    \"id\": \"1615266000361951234\",\n" +
				"   \"id1\": 1615266000361951234\n" +
				"}";
		try {
			JsonNode dataWidgets = objectMapper.readTree(tree);
			System.out.println(dataWidgets.get("id").asText());
			System.out.println(dataWidgets.get("id1").asText());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}
}

