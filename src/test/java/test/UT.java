package test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Utilities for unit test.
 */
public class UT {

	/**
	 * 配列→リスト
	 * @param vs
	 * @return
	 */
	@SafeVarargs
	public static <E> List<E> lst(E ... vs){
		return Arrays.asList(vs);
	}
	/**
	 * 配列→イテレータ
	 * @param vs
	 * @return
	 */
	@SafeVarargs
	public static <E> Iterator<E> it(E ... vs){
		return lst(vs).iterator();
	}
	/**
	 * 配列化
	 * @param vs
	 * @return
	 */
	@SafeVarargs
	public static <E> E[] arr(E ... vs){
		return vs;
	}

	/**
	 * 指定した要素から成るイテレータであることを検証する
	 * Matcherを生成。
	 * @param vs
	 * @return
	 */
	@SafeVarargs
	public static <E> IteratorMathcer<E> isIterator(E ... vs){
		return new IteratorMathcer<>(lst(vs));
	}
	/**
	 * 指定した要素から成る{@link Iterable}であることを検証する
	 * Matcherを生成。
	 * @param vs
	 * @return
	 */
	@SafeVarargs
	public static <E> IterableMathcer<E> isIterable(E ... vs){
		return new IterableMathcer<>(lst(vs));
	}
}
