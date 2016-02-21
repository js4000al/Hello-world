package ex04.stream_modoki;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 次に{@link #next()}で返す要素を先読みする。先読みした要素は{@link 
 * #peek()}で参照できる。
 * @param <E>
 */
public final class DelayIterator<E> implements Iterator<E> {
	/** Indicates end of iterator */
	private static final Object End = new Object();
	
	/** base iterator */
	private final Iterator<E> base;
	/** next element. If iterator is end, it is {@link #End} */
	protected Object next;
	
	/**
	 * 
	 * @param base base iterator
	 */
	public DelayIterator(Iterator<E> base){
		this.base = base;
		readNext();
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public boolean hasNext() {
		return next != End;
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public E peek(){
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		return (E)next;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public E next() {
		E ret = peek();
		readNext();
		return ret;
	}

	/**
	 * Reads next element and caches.
	 */
	private void readNext(){
		next = base.hasNext() ? base.next() : End;
	}
	
}
