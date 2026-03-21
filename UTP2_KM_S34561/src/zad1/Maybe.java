package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private T value;
    public static <X> Maybe<X> of(X x) {
        return new Maybe<X>(x);
    }
    private Maybe() {

    }
    private Maybe(T value) {
        this.value = value;
    }
    public void ifPresent(Consumer<T> cons) {
        if (value != null) cons.accept(value);
    }
    public <U> Maybe<U> map(Function<T,U> func) {
        if (this.value != null) {
            return new Maybe<U>(func.apply(this.value));
        }
        return new Maybe<U>();
    }
    public T get() {
        if (this.value != null) return this.value;
        throw new NoSuchElementException("maybe is empty");
    }
    public boolean isPresent() {
        return this.value != null;
    }
    public T orElse(T defVal) {
        if (this.value != null) return this.value;
        return defVal;
    }
    public Maybe<T> filter(Predicate<T> pred) {
        if (pred.test(this.value)) {
            return this;
        }
        return new Maybe<>();
    }
    public String toString(){
        if (this.value == null) return "Maybe is empty";
        return "Maybe has value " + this.value;
    }
}
