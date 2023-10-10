package org.example;

import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class FluxUtils {



	// 公共方法
	public static <T> Flux<T> handleCtxValue(Flux<T> flux, Map<String,Object> map) {
//		AtomicLong tenantId = new AtomicLong(0L);

		AtomicReference<Long> tenantId = new AtomicReference<>(new Long(0L));
		// 执行一些公共逻辑
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


		Flux<Integer> transformedFlux = handleCtxValue(Flux.just(1,2,3),Map.of("key","value","key1","value1"))
				.filter(value -> value % 2 == 0)
				.map(value -> value * 2)
				.contextWrite(ctx -> {
					ctx =	ctx.put("resultSync", 888L);
					System.out.println("main:"+ctx);
					return ctx;
				});

		transformedFlux.subscribe(System.out::println);
	}
}
