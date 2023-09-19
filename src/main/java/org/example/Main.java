package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

//	private static final Pattern PATTERN = Pattern.compile(".*nulls last$", Pattern.CASE_INSENSITIVE);
	private static final Pattern PATTERN = Pattern.compile(".*nulls last", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public static void main(String[] args) {
		System.out.println("Hello world!");

		String str ="helloword";
		str.replace("ll","");
		System.out.println(String.valueOf(str));
//		LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
//		System.out.println(todayStart);
//		System.out.println(todayStart.format(formatter));
//		handleNode();
//		handleReg();
//		basicAuth("mspbots@shartega.com","3g1kH0AOZsmXjM0Rdou2iy7FZ3UNW4RP4viMd+KT2R0LEti5");  // auvik
//		basicAuth("TNP+cuTXRINqC6VWawIA","wCfo4fYbkJDDNql0");  // connectwisemanage

//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//		System.out.println(differMin(LocalDateTime.parse("2023-08-22T14:57:46.317"),
//				LocalDateTime.parse("2023-08-22 18:57:46.287", formatter)));

//		handleList();
//		handleTimeZoneId();
//		test1();

//		LocalDateTime now = LocalDateTime.now();
//		LocalDateTime localDateTime = now.minusHours(3);
//		System.out.println(now);
//		System.out.println(localDateTime);
//		System.out.println(Duration.between(localDateTime,now).toHours());


//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString().length());




	}
	public static void test1() {
		String timeZoneId = "AE02"; // 替换为你想要查询的时区ID
		ZoneId zoneId = ZoneId.of(timeZoneId);
		ZonedDateTime now = ZonedDateTime.now(zoneId);

		int totalOffset = now.getOffset().getTotalSeconds() / 60;

		System.out.println("Time Zone: " + timeZoneId);
		System.out.println("Total Offset: " + totalOffset + " minutes");
	}
	public static void handleTimeZoneId() {
		String timeZoneId = "America/New_York"; // 替换为你想要查询的时区ID
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);

		int rawOffset = timeZone.getRawOffset();
		int dstOffset = timeZone.getDSTSavings();

		int totalOffset = (rawOffset + dstOffset) / (60 * 1000); // 转换为分钟

		System.out.println("Time Zone: " + timeZoneId);
		System.out.println("Raw Offset: " + (rawOffset / (60 * 1000)) + " minutes");
		System.out.println("DST Offset: " + (dstOffset / (60 * 1000)) + " minutes");
		System.out.println("Total Offset: " + totalOffset + " minutes");
	}

	private static void handleList() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("1");
		//list 生成一个map key为元素 value为元素出现的次数
		Map<String, Integer> map = list.stream().collect(Collectors.groupingBy(String::toString, Collectors.summingInt(x->1)));
		System.out.println(map);
	}


	private static long differMin(LocalDateTime date, LocalDateTime utcDate){
		long differ = Duration.between(date,utcDate).toMinutes();
		if(differ % 15 > 3){
			return (differ/15L+1) * 15;
		}else{
			return differ/15L * 15;
		}
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