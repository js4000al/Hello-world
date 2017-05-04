package exam.util.maybe;

public interface ISupplierE<R, X extends Exception> {
	R get() throws X;
}
