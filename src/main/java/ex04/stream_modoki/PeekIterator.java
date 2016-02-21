package ex04.stream_modoki;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 取り出された要素を指定した処理に渡す。
 * @param <E>
 */
public final class PeekIterator<E> implements Iterator<E> {
	
	private final Iterator<E> base;
	private final Function<E,E> consumer;
	
	public PeekIterator(Iterator<E> base, Consumer<? super E> consumer){
		this.base = base;
		this.consumer = v -> { consumer.accept(v); return v;};
	}

	@Override
	public boolean hasNext() {
		return base.hasNext();
	}

	@Override
	public E next() {
		return consumer.apply(base.next());
	}

}
