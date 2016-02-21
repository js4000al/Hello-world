package test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UT {

	@SafeVarargs
	public static <E> List<E> lst(E ... vs){
		return Arrays.asList(vs);
	}
	@SafeVarargs
	public static <E> Iterator<E> it(E ... vs){
		return lst(vs).iterator();
	}
	@SafeVarargs
	public static <E> E[] arr(E ... vs){
		return vs;
	}

	@SafeVarargs
	public static <E> IteratorMathcer<E> isIterator(E ... vs){
		return new IteratorMathcer<>(lst(vs));
	}
	@SafeVarargs
	public static <E> IterableMathcer<E> isIterable(E ... vs){
		return new IterableMathcer<>(lst(vs));
	}
}
