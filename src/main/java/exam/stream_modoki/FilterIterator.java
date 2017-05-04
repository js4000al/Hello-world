package exam.stream_modoki;

import java.util.Iterator;
import java.util.function.Predicate;


/**
 * 他のイテレータにかぶせ、条件を満たす要素のみに絞る。
 * @param <E>
 */
public class FilterIterator<E> implements Iterator<E>{

	private final DelayIterator<E> it;
	private Predicate<? super E> filter;
	
	public FilterIterator(Iterator<? extends E> base, Predicate<? super E> filter){
		it = new DelayIterator<>(base);
		this.filter = filter;
	}
	
	/**
	 * 条件を満たす要素が現れるまで先読みを繰り返す。
	 */
	@Override
	public final boolean hasNext(){
		while(it.hasNext() && ! filter.test(it.peek())){
			it.next();
		}
		return it.hasNext();
	}

	@Override
	public E next() {
		return it.next();
	}
}
