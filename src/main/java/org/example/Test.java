package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {


	void test() {
		LocalDate currentDate = LocalDate.now();
		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		int weekday = dayOfWeek.getValue();

		System.out.println("Current Date: " + currentDate);
		System.out.println("Weekday: " + weekday);
		System.out.println();
		Arrays.stream(DayOfWeek.values()).flatMap(x -> {
			System.out.println(x.getValue());
			System.out.println(x);
			return Arrays.stream(x.values());
		}).collect(Collectors.toList());

	}

	public static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {

		List<JsonNode> list = new ArrayList<>();

		List<JsonNode> data = new ArrayList<>();
		for (JsonNode node : pre()) {
			if (node.has("com") && "pageheadergadget".equals(node.get("com").asText())) {
				list.add(node);
			}else {
				data.add(node);
			}
		}
		if (!data.isEmpty()){
			ObjectNode objectNode = objectMapper.createObjectNode();
			objectNode.set("data",objectMapper.valueToTree(data));
			list.add(objectNode);
		}

		System.out.println("parse aft ret : "+objectMapper.valueToTree(list));

	}


	public static List<JsonNode> pre(){
		String str = "[{\"h\":3,\"i\":\"de50fbac-0ef5-4749-ad8b-2fd846e6ac37\",\"w\":12,\"x\":0,\"y\":0,\"com\":\"pageheadergadget\",\"moved\":false,\"static\":true},{\"data\":[{\"x\":0,\"y\":0,\"w\":16,\"h\":4,\"i\":\"db98f7d1-b2ab-430e-8cda-a7b435d8fdbf\",\"com\":\"gridgadget\",\"id\":\"1719983493146161154\",\"name\":\"aa\",\"integrationList\":[{\"name\":\"Teams\",\"code\":\"TEAMS\",\"icon\":\"https://oss.mspbots.ai/files/integration_logo/teams_logo.png\"}],\"businessType\":\"Custom\",\"moved\":false}]}]";

		String str1 = "[{\"h\":3,\"i\":\"de50fbac-0ef5-4749-ad8b-2fd846e6ac37\",\"w\":12,\"x\":0,\"y\":0,\"com\":\"pageheadergadget\",\"moved\":false,\"static\":true},{\"data\":[{\"x\":0,\"y\":0,\"w\":16,\"h\":4,\"i\":\"db98f7d1-b2ab-430e-8cda-a7b435d8fdbf\",\"com\":\"gridgadget\",\"id\":\"1719983493146161154\",\"name\":\"aa\",\"integrationList\":[{\"name\":\"Teams\",\"code\":\"TEAMS\",\"icon\":\"https://oss.mspbots.ai/files/integration_logo/teams_logo.png\"}],\"businessType\":\"Custom\",\"moved\":false}]}]";
		System.out.println(str1);
		List<JsonNode> list = new ArrayList<>();

		try {
			JsonNode jsonNode = objectMapper.readTree(str1);
			for (JsonNode node : jsonNode) {
				if (node.has("com") && node.get("com").asText().equals("pageheadergadget")) {
					String iValue = node.get("i").asText();
					System.out.println("i value for com=pageheadergadget: " + iValue);
					list.add(node);
				}

				if (node.has("data") && node.get("data").isArray()) {
					for (JsonNode data : node.get("data")) {
						list.add(data);
					}
				}
			}

			System.out.println("parse ret : "+objectMapper.valueToTree(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
