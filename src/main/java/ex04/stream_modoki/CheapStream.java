package ex04.stream_modoki;

import static ex04.stream_modoki.Predicates.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ストリームAPIを模したモノ。
 * 
 */
public class CheapStream<E> implements Iterable<E>{
	
	/**
	 * 配列をストリーム化。
	 * @param vs
	 * @return
	 */
	@SafeVarargs
	public static <E> CheapStream<E> of(E ... vs){
		return of(Arrays.asList(vs));
	}
	/**
	 * {@link Iterable}をストリーム化。
	 * @param iterable
	 * @return
	 */
	public static <E> CheapStream<E> of(Iterable<E> iterable){
		return new CheapStream<>(iterable);
	}

	/** 元になるイテレータ */
	private final Iterable<E> iterable;
	/**
	 * 
	 * @param iterable 元になるイテレータ。
	 */
	private CheapStream(Iterable<E> iterable){
		this.iterable = iterable;
	}

	/**
	 * 条件を満たす要素に絞る。
	 * @param p
	 * @return
	 */
	public CheapStream<E> select(Predicate<? super E> filter){
		return of(() -> new FilterIterator<>(iterator(), filter));
	}
	/**
	 * 写像。
	 * @param converter
	 * @return
	 */
	public <R> CheapStream<R> map(Function<? super E, ? extends R> converter){
		return of(() -> new MapIterator<>(iterator(), converter));
	}

	/**
	 * 条件を満たす最初の要素の直前まで。
	 * @param stopCond
	 * @return
	 */
	public CheapStream<E> takeUntil(Predicate<? super E> stopCond){
		return of(() -> new MiddleStopper<>(iterator(), stopCond));
	}
	/**
	 * 最初に現れる指定した値の直前まで。
	 * @param stopCond
	 * @return
	 */
	public CheapStream<E> takeUntil(E stop){
		return takeUntil(v -> stop.equals(v));
	}
	/**
	 * 条件を外れる最初の要素の直前まで。
	 * @param p
	 * @return
	 */
	public CheapStream<E> take(Predicate<? super E> p){
		return takeUntil(not(whileDo(p)));
	}
	/**
	 * 先頭から指定数分に絞る。
	 * @param n
	 * @return
	 */
	public CheapStream<E> take(int n){
		return take(new CountLimitter<>(n));
	}
	
	/**
	 * 条件を外れる最初の要素の直前までを除く。
	 * @param skipCond
	 * @return
	 */
	public CheapStream<E> skipUntil(Predicate<? super E> skipCond){
		return select(not(Predicates.whileDo(skipCond)));
	}
	/**
	 * 先頭から指定数分を除く。
	 * @param n
	 * @return
	 */
	public CheapStream<E> skip(int n){
		return skipUntil(not(new CountLimitter<>(n)));
	}

	/**
	 * 通過する要素を捕捉する。
	 * @param consumer
	 * @return
	 */
	public CheapStream<E> peek(Consumer<? super E> consumer){
		return of(() -> new PeekIterator<>(iterator(), consumer));
	}

	@Override
	public Iterator<E> iterator(){
		return iterable.iterator();
	}
	
}
