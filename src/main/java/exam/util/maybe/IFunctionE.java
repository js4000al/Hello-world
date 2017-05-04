package exam.util.maybe;

public interface IFunctionE<T, R, X extends Exception> {
	R apply(T v) throws X;
}
