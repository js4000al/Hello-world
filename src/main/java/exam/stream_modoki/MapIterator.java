package exam.stream_modoki;

import java.util.Iterator;
import java.util.function.Function;

/**
 * 写像するイテレータ。
 * @param <E>
 */
public final class MapIterator<E, R> implements Iterator<R> {
	private final Iterator<E> base;
	private final Function<? super E, ? extends R> converter;
	
	public MapIterator(Iterator<E> base, Function<? super E, ? extends R> converter){
		this.base = base;
		this.converter = converter;
	}
	

	@Override
	public boolean hasNext() {
		return base.hasNext();
	}
	@Override
	public R next() {
		return converter.apply(base.next());
	}

}
