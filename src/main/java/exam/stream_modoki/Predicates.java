package exam.stream_modoki;

import java.util.function.Predicate;

public class Predicates {

	public static <T> Predicate<? super T> not(Predicate<? super T> p){
		return v -> ! p.test(v);
	}
	
	public static <T> Predicate<? super T> whileDo(Predicate<? super T> p){
		return until(v -> !p.test(v));
	}
	
	public static <T> Predicate<? super T> until(Predicate<? super T> p){
		return new Until<>(p);
	}

}
