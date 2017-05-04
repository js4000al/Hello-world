package exam.util.maybe;

public interface IPredicateE<T, X extends Exception> {
	boolean test(T v) throws X;
}
