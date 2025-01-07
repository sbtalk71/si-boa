package demo.reactor;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class FluxDemo1 {

	public static void main(String[] args) throws Exception{
		List<Integer> numList=Arrays.asList(1,2,3,4,5,6);
		
		//Flux<Integer> flux=Flux.fromIterable(numList);
		
		//Flux<Long> flux=Flux.interval(Duration.ofSeconds(2));
		
		ConnectableFlux<Long> flux=Flux.interval(Duration.ofSeconds(1)).publish();
		
		//flux.delayElements(Duration.ofSeconds(1)).log().subscribe(System.out::println);
		
		

		System.out.println();
		flux.filter(n->n%2==0).log().subscribe(System.out::println);
		flux.connect();
		
		
		Thread.sleep(10000);
		
		System.out.println("Subscriber 2");
		flux.filter(n->n%2==0).log().subscribe(System.out::println);
		flux.connect();
		
		Thread.sleep(Long.MAX_VALUE);
	}

}
