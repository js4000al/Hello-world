package test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class IteratorMathcer<E> extends BaseMatcher<Iterator<E>> {
	
	private final List<E> expected;
	private String msg;
	
	public IteratorMathcer(List<E> expected) {
		this.expected = expected;
	}
	
	@Override
	public boolean matches(Object v){
		@SuppressWarnings("unchecked")
		Iterator<E> it = (Iterator<E>)v; 
		for(E o : expected){
			if(!(it.hasNext() && it.hasNext())){
				msg = "要素数不足、またはhasNextでカーソルが余計に進んでいるかも";
				return false;
			}
			if(! it.next().equals(o)){
				msg = "要素の不一致";
				return false;
			}
		}
		if(it.hasNext()){
			msg = "要素数が多い";
			return false;
		}
		try{
			it.next();
			msg = "終端に達した状態でNoSuchElementExceptionにならない";
			return false; 
		}
		catch(NoSuchElementException e){
			return true; 
		}
	}

	@Override
	public void describeTo(Description desc) {
		desc.appendText(msg);
		desc.appendValue(expected);
	}

}
