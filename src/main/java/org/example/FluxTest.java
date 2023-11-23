package org.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class FluxTest {

	public static void main(String[] args) {
//		Flux.just(1,2,3,4).subscribe(System.out::println);

//		Flux.just(1,2,3,4).filter(x-> x <2)
//				.flatMap(v -> {
//					System.out.println(v);
//					return Flux.just(1);
//				}).subscribe();
//				}).subscribe(System.out::println);

//		HashMap<String, Object> map = new HashMap<>();
//		map.put("1",1);
//		map.put("2",2);
//		map.put("3",3);
//		map.put("4",1);
//
////		Flux.fromStream(map.values().parallelStream()).subscribe(System.out::println);
//		Flux.fromStream(map.values().stream().distinct().collect(Collectors.toList()).parallelStream()).subscribe(x->{
//			System.out.println(System.currentTimeMillis() + " distinct " + x);
//		});
//		System.out.println( " =================================== " );
//
//		Flux.fromStream(map.values().parallelStream()).subscribe(x->{
//			System.out.println(System.currentTimeMillis() + " " + x);
//		});
//		test1();

//		String str = "{\"tenantCode\":10086,\"ids\":[712448704,712735086,712735598,712733802,712736110,712755384,712757898,712757832,712756860,712757118,712760476,712762082,712763064,712768912,712768472,712769282,712904792,712755558,712835124,712834656,712835190,712837464,712842472,712849780,712850276,712851232,712853004,712852352,712855870,712870270,712870422,712882724,712887852,712892112,712976978,712980382,712991048,712993332,712993416,712994022,712993810,712995770,712997494,712999036,713000248,713002328,713003420,713002770,713003502,713003470,712991942,713055628,713055770,713055808,713059488,713066030,713070224,713069134,713074716,713107004,713110370,713118002],\"limitTime\":\"2023-09-05T11:54:08Z\",\"idList\":null,\"fieldType\":\"LAST_MODIFIED\"}\n";
//		HashMap<String, Object> map = new HashMap<>();
//		for (int i = 0; i < 10000; i++) {
//			map.put(i+"",str);
//		}
//		Flux.fromStream(map.values().parallelStream())
////		Flux.fromStream(map.values().stream().distinct().collect(Collectors.toList()).parallelStream())
//				.collectList()
//				.doOnNext(ids->{
//					System.out.println(System.currentTimeMillis() + " " + ids);
//				})
//				.subscribe();
//
//		Flux<Integer> dataStream = generateDataStream(20000);
//
//		// 订阅数据流并处理每个元素
//		dataStream.subscribe(
//				value -> System.out.println("Received: " + value),
//				error -> System.err.println("Error: " + error),
//				() -> System.out.println("Data stream completed")
//		);

//		test2();
//		test5();
//		test4();
	}


	public static void test5(){
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
		}

		Mono.just(list).flatMapMany(x->{
			System.out.println(System.currentTimeMillis() + " flatMapMany: " + x);
			return Mono.just(x);
		}).subscribe();



//		while (true){
//
//		}

	}
	public static void test4(){
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
		}

		Mono.just(list).flatMap(x->{
			System.out.println(System.currentTimeMillis() + " flatMap: " + x);
			return Mono.just(x);
		}).block();



//		while (true){
//
//		}

	}


	public static void test3(){
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 70; i++) {
			list.add(String.valueOf(i));
		}

		Mono.just(list).flatMap(x->{
			System.out.println(System.currentTimeMillis() + " flatMap: " + x);
			int a = 1;
			int b = 0;
//			int c = a/b;
			return Mono.just(x);
		}).block(Duration.ofMillis(3000));



//		while (true){
//
//		}

	}
	public static void test2(){
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 70; i++) {
			list.add(String.valueOf(i));
		}
		delaySeconds1(list);
//		delaySeconds2(list);

		while (true){

		}

	}


	public static void  delaySeconds1(List<String> list){
		AtomicLong previousTimestamp = new AtomicLong();

		Flux.fromStream(list.stream())
				.doOnNext(x->{
					System.out.println(System.currentTimeMillis() + " doOnNext1: " + x);
				})
				.delayElements(Duration.ofSeconds(5))
//				.limitRate(2)
				.flatMap(x->{
					long currentTimestamp = System.currentTimeMillis();
					long previous = previousTimestamp.getAndSet(currentTimestamp);
					if (previous != 0) {
						long interval = currentTimestamp - previous;
						System.out.println(x+"延迟: " + interval + " ms");
					}
					System.out.println(System.currentTimeMillis() + " delayElements1: " + x);
					return Mono.just(x);
				})
				.subscribe();
	}
	public static void  delaySeconds2(List<String> list){
		AtomicLong previousTimestamp = new AtomicLong();
		Flux.fromStream(list.stream())
				.doOnNext(x -> {
					System.out.println(System.currentTimeMillis() + " doOnNext2: " + x);
				})
				.concatMap(x -> Mono.delay(Duration.ofSeconds(5)).then(Mono.fromRunnable(() -> {

					long currentTimestamp = System.currentTimeMillis();
					long previous = previousTimestamp.getAndSet(currentTimestamp);
					if (previous != 0) {
						long interval = currentTimestamp - previous;
						System.out.println(x+"延迟: " + interval + " ms");
					}
					System.out.println(System.currentTimeMillis() + " delayElements2: " + x);
				})))
				.subscribe();
	}

	public static Flux<Integer> generateDataStream(int count) {
		return Flux.range(1, count);
	}
	public static void test1(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("1",1);
		map.put("2",2);
		map.put("3",3);
		map.put("4",1);
		Flux.fromStream(map.values().parallelStream())
				.buffer()
				.flatMap(x->Flux.fromIterable(x))
				.doOnNext(jsonNode->{
					System.out.println(System.currentTimeMillis() + " " + jsonNode);
				})
				.doOnComplete(()->{
					System.out.println(System.currentTimeMillis() + " end " );
				})
				.subscribe();


	}

}
