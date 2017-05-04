package exam.stream_modoki;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * 条件を満たしたところで止まるイテレータ。
 * @param <E>
 */
public final class MiddleStopper<E> implements Iterator<E> {
	
	private final DelayIterator<E> it;
	private final Predicate<? super E> continueCond;
	
	public MiddleStopper(Iterator<? extends E> base, Predicate<? super E> stopCond){
		it = new DelayIterator<>(base);
		continueCond = Predicates.until(v -> stopCond.test(v));
	}
	
	@Override
	public boolean hasNext(){
		return it.hasNext() && continueCond.test(it.peek());
	}

	@Override
	public E next() {
		if(! hasNext()){
			throw new NoSuchElementException();
		}
		return it.next();
	}

}
