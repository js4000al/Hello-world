package ex04.stream_modoki;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class DelayIterator<E> implements Iterator<E> {
	
	private static final Object End = new Object();
	
	private final Iterator<E> base;
	protected Object last;
	
	public DelayIterator(Iterator<E> base){
		this.base = base;
		readNext();
	}
	
	@Override
	public boolean hasNext() {
		return last != End;
	}

	@SuppressWarnings("unchecked")
	public E peek(){
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		return (E)last;
	}
	
	@Override
	public E next() {
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		@SuppressWarnings("unchecked")
		E ret = (E)last;
		readNext();
		return ret;
	}

	private void readNext(){
		last = base.hasNext() ? base.next() : End;
	}
}
