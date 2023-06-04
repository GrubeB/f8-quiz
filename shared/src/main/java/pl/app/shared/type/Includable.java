package pl.app.shared.type;

public interface Includable<T>  {
    boolean include(T t);
}
