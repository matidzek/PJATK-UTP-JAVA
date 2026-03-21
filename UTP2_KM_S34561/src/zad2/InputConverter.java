package zad2;

import java.util.function.Function;

public class InputConverter<T> {
    private T value;

    public InputConverter(T value) {
        this.value = value;
    }

    public <X> X convertBy(Function ... parser) {
        Function combinedFun = parser[0];
        for (int i = 1; i < parser.length; i++) {
            combinedFun = combinedFun.andThen(parser[i]);
        }

        return (X) combinedFun.apply(this.value);
    }
}
