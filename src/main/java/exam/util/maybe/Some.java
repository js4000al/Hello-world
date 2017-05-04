package exam.util.maybe;

import exam.util.Tuple;
import exam.util.Tuple.Cns;

/**
 * Not null value.
 * @param <T>
 */
public final class Some<T> {
	
	/**
	 * Wrap not-null value.
	 * @param v
	 * @return
	 * @throw {@link IllegalArgumentException} when the value is null.
	 */
	public static <T> Some<T> of(T v){
		if(v == null){
			throw new IllegalArgumentException();
		}
		return new Some<>(v);
	}
	
	
	public final T value;
	
	private Some(T value){
		this.value = value;
	}

	public <R, X extends Exception> Some<R> map(IFunctionE<T, R, X> converter) throws X{
		return Some.of(converter.apply(value));
	}
	public <R, X extends Exception> Some<R> flatMap(IFunctionE<T, Some<R>, X> converter) throws X{
		return map(v -> converter.apply(v).value);
	}
	public <X extends Exception> Some<T> does(IConsumerE<T, X> receiver) throws X{
		receiver.accept(value);
		return this;
	}
	
	public <R, X extends Exception> Some<Cns<T, R>> map2(IFunctionE<T, R, X> converter) throws X{
		return Some.of(Tuple.cons(value, map(converter).value));
	}
	
	@SuppressWarnings("unchecked")
	public <R> Some<R> cast(){
		return Some.of((R)value);
	}
	
}
