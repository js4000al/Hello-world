package test;

import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class IterableMathcer<E> extends BaseMatcher<Iterable<E>> {
	
	private final IteratorMathcer<E> m;
	
	public IterableMathcer(List<E> expected) {
		this.m = new IteratorMathcer<>(expected);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean matches(Object v){
		return m.matches(((Iterable<E>)v).iterator());
	}

	@Override
	public void describeTo(Description desc) {
		m.describeTo(desc);
	}

}
