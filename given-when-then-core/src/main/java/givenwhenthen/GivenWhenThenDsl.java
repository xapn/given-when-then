package givenwhenthen;

import java.util.function.Consumer;
import java.util.function.Function;

public interface GivenWhenThenDsl {

    public static interface Given<$SystemUnderTest> {

        When<$SystemUnderTest> given(Runnable givenStep);

        When<$SystemUnderTest> given(Consumer<$SystemUnderTest> givenStep);
    }

    public static interface When<$SystemUnderTest> {

        <$Result> Then<$SystemUnderTest, $Result> when(Function<$SystemUnderTest, $Result> whenStep);

        Then<$SystemUnderTest, Void> when(Consumer<$SystemUnderTest> whenStep);
    }

    public static interface Then<$SystemUnderTest, $Result> {

        void then(Consumer<$Result> thenStep);

        void then(Runnable thenStep);
    }
}