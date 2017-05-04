package exam.util.maybe;

import java.util.function.Function;
import java.util.function.Supplier;

import exam.util.Tuple;
import exam.util.Tuple.Cns;

/**
 * 
 * @param <T>
 */
public abstract class Maybe<T> {
	
	private static final None<?> NONE = new None<>();

	@SuppressWarnings("unchecked")
	public static <T> Maybe<T> none(){
		return (Maybe<T>)NONE;
	}
	public static <T> Maybe<T> of(T value){
		return (value == null) ? none() :  new NotNull<>(value);
	}
	
	
	
	private Maybe(){}
	
	
	public abstract boolean isPresent();
	
	public abstract <R, X extends Exception> Maybe<R> map(
			IFunctionE<? super T, ? extends R, X> converter) throws X;
	public abstract <R, X extends Exception> Maybe<R> flatMap(
			IFunctionE<? super T, Maybe<? extends R>, X> converter) throws X;
	public abstract <R> Maybe<R> cast();
	public abstract <R, X extends Exception> Maybe<Cns<T, R>> both(
			IFunctionE<? super T, ? extends R, X> converter) throws X;
	
	public abstract <X extends Exception> Maybe<T> filter(
			IPredicateE<? super T, X> cond) throws X;
	
	public final <X extends Exception> Some<T> orElse(T defaultValue){
		return orElse(() -> defaultValue);
	}
	public final <X extends Exception> Some<T> orElse(Some<T> defaultValue){
		return orElse(() -> defaultValue.value);
	}
	public abstract <X extends Exception> Some<T> orElse(ISupplierE<? extends T, X> supplier) throws X;
	
	public abstract <X extends Exception> Maybe<T> whenSome(
			IConsumerE<T, X> receiver) throws X;
	public abstract <X extends Exception> Maybe<T> whenNone(
			IActionE<X> receiver) throws X;
	
	public final <X extends Exception> Maybe<T> throwWhenSome(
			Function<? super T, ? extends X> supplier) throws X{
		return whenSome(v -> { throw supplier.apply(v);});
	}
	public final <X extends Exception> Maybe<T> throwWhenNone(
			Supplier<? extends X> supplier) throws X{
		return whenNone(() -> { throw supplier.get();});
	}
	
	
	private static final class NotNull<T> extends Maybe<T>{
		private final Some<T> value;
		
		NotNull(T value){
			this.value = Some.of(value);
		}
		
		@Override
		public boolean isPresent(){
			return true;
		}

		@Override
		public <R, X extends Exception> Maybe<R> map(IFunctionE<? super T, ? extends R, X> converter) throws X {
			return new NotNull<>(converter.apply(value.value));
		}

		@Override
		public <R, X extends Exception> Maybe<R> flatMap(IFunctionE<? super T, Maybe<? extends R>, X> converter)
				throws X {
			return converter.apply(value.value).cast();
		}
		
		@Override
		public <R, X extends Exception> Maybe<Cns<T, R>> both(
				IFunctionE<? super T, ? extends R, X> converter) throws X{
			return map(converter).map(v -> Tuple.cons(value.value, v));
		}

		@Override
		@SuppressWarnings("unchecked")
		public <R> Maybe<R> cast(){
			return new NotNull<>((R)value.value);
		}
		
		@Override
		public <X extends Exception> Maybe<T> filter(IPredicateE<? super T, X> cond) throws X {
			return cond.test(value.value) ? this : none();
		}

		@Override
		public <X extends Exception> Some<T> orElse(ISupplierE<? extends T, X> supplier) throws X {
			return value;
		}

		@Override
		public <X extends Exception> Maybe<T> whenSome(IConsumerE<T, X> receiver) throws X {
			receiver.accept(value.value);
			return this;
		}

		@Override
		public <X extends Exception> Maybe<T> whenNone(IActionE<X> receiver) throws X {
			return this;
		}
	}
	
	private static final class None<T> extends Maybe<T>{
		
		@Override
		public boolean isPresent(){
			return false;
		}

		@Override
		public <R, X extends Exception> Maybe<R> map(IFunctionE<? super T, ? extends R, X> converter){
			return cast();
		}

		@Override
		public <R, X extends Exception> Maybe<R> flatMap(IFunctionE<? super T, Maybe<? extends R>, X> converter){
			return cast();
		}
		
		@Override
		public <R, X extends Exception> Maybe<Cns<T, R>> both(
				IFunctionE<? super T, ? extends R, X> converter) throws X{
			return cast();
		}

		@Override
		public <R> Maybe<R> cast(){
			return none();
		}
		
		@Override
		public <X extends Exception> Maybe<T> filter(IPredicateE<? super T, X> cond) throws X {
			return this;
		}

		@Override
		public <X extends Exception> Some<T> orElse(ISupplierE<? extends T, X> supplier) throws X {
			return Some.of(supplier.get());
		}

		@Override
		public <X extends Exception> Maybe<T> whenSome(IConsumerE<T, X> receiver) throws X {
			return this;
		}

		@Override
		public <X extends Exception> Maybe<T> whenNone(IActionE<X> receiver) throws X {
			receiver.run();
			return this;
		}
	}
	
}
