package org.example;

import org.apache.commons.lang3.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class FluxUtils {



	// 公共方法
	public static <T> Flux<T> handleCtxValue(Flux<T> flux, Map<String,Object> map) {
//		AtomicLong tenantId = new AtomicLong(0L);

		AtomicReference<Long> tenantId = new AtomicReference<>(new Long(0L));
		return flux.contextWrite(ctx -> {
			for (String s : map.keySet()) {
				ctx = ctx.put(s, map.get(s));
			}
			System.out.println("set before:"+ctx);
;			tenantId.set(ctx.get("resultSync"));
			System.out.println("handleCtxValue:"+ctx);
			return ctx;
		}).map(x->{
			System.out.println("map:"+tenantId);
			return x;
		}
		);
	}
	public static void test(){
		// 在链式调用中使用公共方法
		Flux<Integer> transformedFlux = handleCtxValue(Flux.just(1,2,3),Map.of("key","value","key1","value1"))
				.filter(value -> value % 2 == 0)
				.map(value -> value * 2)
				.contextWrite(ctx -> {
					ctx =	ctx.put("resultSync", 1);
					System.out.println("main:"+ctx);
					return ctx;
				});

		transformedFlux.subscribe(System.out::println);
	}

	public static void main(String[] args) {


//		Flux<Integer> transformedFlux = handleCtxValue(Flux.just(1,2,3),Map.of("key","value","key1","value1"))
//				.filter(value -> value % 2 == 0)
//				.map(value -> value * 2)
//				.contextWrite(ctx -> {
//					ctx =	ctx.put("resultSync", 888L);
//					System.out.println("main:"+ctx);
//					return ctx;
//				});
//
//		transformedFlux.subscribe(System.out::println);
//2035-03-20T04:13:46Z
//		recordAnchorTime("","2035-03-20T04:13:46Z");

		Flux.concatDelayError(
				Mono.just(1).delayElement(Duration.ofSeconds(5)).doOnNext(x -> System.out.println("1111111111111")),
				Mono.just(2).delayElement(Duration.ofSeconds(5)).doOnNext(x -> System.out.println("222222222222"))
		).subscribe();
	}
	protected static final DateTimeFormatter UTF= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

	protected static void recordAnchorTime(String key, String value) {
//        log.debug("recordAnchorTime: {}",value);
		LocalDateTime nowAnchorTime = LocalDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		Object str = "2023-10-13T04:55:53Z";
		if (ObjectUtils.isEmpty(str)){
			System.out.println("1");
		}else {
			LocalDateTime redisTime = LocalDateTime.parse(LocalDateTime.parse(str+"",DateTimeFormatter.ISO_DATE_TIME).format(UTF),DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			if (redisTime.isBefore(nowAnchorTime) && nowAnchorTime.isBefore(LocalDateTime.now())) {
				System.out.println("1");
			}else {
				System.out.println("2");
			}
		}
	}

}