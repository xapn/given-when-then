package givenwhenthen;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface GivenWhenThenDsl {

    interface Given<$SystemUnderTest> extends When<$SystemUnderTest> {

        AndGiven<$SystemUnderTest> given(Runnable givenStep);

        AndGiven<$SystemUnderTest> given(Consumer<$SystemUnderTest> givenStep);

        AndGiven<$SystemUnderTest> given(String fixtureSpecification, Runnable givenStep);

        AndGiven<$SystemUnderTest> given(String fixtureSpecification, Consumer<$SystemUnderTest> givenStep);

        <$Argument> AndGivenArgument<$SystemUnderTest, $Argument> givenArgument(Supplier<$Argument> givenStep);

        <$Argument> AndGivenArgument<$SystemUnderTest, $Argument> givenArgument($Argument argument);
    }

    interface AndGiven<$SystemUnderTest> extends When<$SystemUnderTest> {

        AndGiven<$SystemUnderTest> and(String fixtureSpecification, Runnable givenStep);

        AndGiven<$SystemUnderTest> and(String fixtureSpecification, Consumer<$SystemUnderTest> givenStep);

        <$Argument> AndGivenArgument<$SystemUnderTest, $Argument> givenArgument(Supplier<$Argument> givenStep);

        <$Argument> AndGivenArgument<$SystemUnderTest, $Argument> givenArgument($Argument argument);
    }

    interface AndGivenArgument<$SystemUnderTest, $Argument> extends WhenApplyingOneArgument<$SystemUnderTest,
            $Argument> {

        <$Argument2> AndGivenTwoArguments<$SystemUnderTest, $Argument, $Argument2> andArgument(
                Supplier<$Argument2> givenStep);

        <$Argument2> AndGivenTwoArguments<$SystemUnderTest, $Argument, $Argument2> andArgument($Argument2 argument);
    }

    interface WhenApplyingOneArgument<$SystemUnderTest, $Argument> {

        ThenWithoutResult<$SystemUnderTest> when(CheckedBiConsumer<$SystemUnderTest, $Argument> whenStep);

        <$Result> Then<$SystemUnderTest, $Result> when(
                CheckedBiFunction<$SystemUnderTest, $Argument, $Result> whenStep);
    }

    interface AndGivenTwoArguments<$SystemUnderTest, $Argument1, $Argument2> extends
            WhenApplyingTwoArguments<$SystemUnderTest, $Argument1, $Argument2> {

        <$Argument3> WhenApplyingThreeArguments<$SystemUnderTest, $Argument1, $Argument2, $Argument3> andArgument(
                Supplier<$Argument3> givenStep);

        <$Argument3> WhenApplyingThreeArguments<$SystemUnderTest, $Argument1, $Argument2, $Argument3> andArgument(
                $Argument3 argument);
    }

    interface WhenApplyingTwoArguments<$SystemUnderTest, $Argument1, $Argument2> {

        ThenWithoutResult<$SystemUnderTest> when(CheckedTriConsumer<$SystemUnderTest, $Argument1, $Argument2> whenStep);

        <$Result> Then<$SystemUnderTest, $Result> when(
                CheckedTriFunction<$SystemUnderTest, $Argument1, $Argument2, $Result> whenStep);
    }

    interface WhenApplyingThreeArguments<$SystemUnderTest, $Argument1, $Argument2, $Argument3> {

        ThenWithoutResult<$SystemUnderTest> when(
                CheckedQuadriConsumer<$SystemUnderTest, $Argument1, $Argument2, $Argument3> whenStep);

        <$Result> Then<$SystemUnderTest, $Result> when(
                QuadriFunction<$SystemUnderTest, $Argument1, $Argument2, $Argument3, $Result> whenStep);
    }

    interface When<$SystemUnderTest> {

        <$Result> Then<$SystemUnderTest, $Result> when(CheckedFunction<$SystemUnderTest, $Result> whenStep);

        ThenWithoutResult<$SystemUnderTest> when(CheckedConsumer<$SystemUnderTest> whenStep);

        ThenFailure whenSutRunsOutsideOperatingConditions(CheckedConsumer<$SystemUnderTest> whenStep);
    }

    interface Then<$SystemUnderTest, $Result> {

        AndThen<$SystemUnderTest, $Result> then(Consumer<$Result> thenStep);

        AndThen<$SystemUnderTest, $Result> then(String expectationSpecification, Consumer<$Result> thenStep);

        AndThen<$SystemUnderTest, $Result> then(Runnable thenStep);

        AndThen<$SystemUnderTest, $Result> then(String expectationSpecification, Runnable thenStep);

        AndThen<$SystemUnderTest, $Result> then(Predicate<$Result> thenStep);

        void then(List<Predicate<$Result>> thenSteps);

        void then(BiConsumer<$SystemUnderTest, $Result> thenStep);

        void then(BiPredicate<$SystemUnderTest, $Result> thenStep);

        void then(Predicate<$Result> thenStepAboutResult, Predicate<$SystemUnderTest> thenStepAboutSystemUnderTest);
    }

    interface AndThen<$SystemUnderTest, $Result> {

        AndThen<$SystemUnderTest, $Result> and(Consumer<$Result> thenStep);

        AndThen<$SystemUnderTest, $Result> and(String expectationSpecification, Consumer<$Result> thenStep);

        AndThen<$SystemUnderTest, $Result> and(Runnable thenStep);

        AndThen<$SystemUnderTest, $Result> and(String expectationSpecification, Runnable thenStep);

        AndThen<$SystemUnderTest, $Result> and(Predicate<$Result> thenStep);
    }

    interface ThenWithoutResult<$SystemUnderTest> {

        AndThenWithoutResult<$SystemUnderTest> then(Runnable thenStep);

        AndThenWithoutResult<$SystemUnderTest> then(String expectationSpecification, Runnable thenStep);

        AndThenWithoutResult<$SystemUnderTest> then(Consumer<$SystemUnderTest> thenStep);

        AndThenWithoutResult<$SystemUnderTest> then(String expectationSpecification,
                Consumer<$SystemUnderTest> thenStep);

        AndThenWithoutResult<$SystemUnderTest> then(BooleanSupplier thenStep);
    }

    interface AndThenWithoutResult<$SystemUnderTest> {

        AndThenWithoutResult<$SystemUnderTest> and(Runnable thenStep);

        AndThenWithoutResult<$SystemUnderTest> and(String expectationSpecification, Runnable thenStep);

        AndThenWithoutResult<$SystemUnderTest> and(Consumer<$SystemUnderTest> thenStep);

        AndThenWithoutResult<$SystemUnderTest> and(String expectationSpecification,
                Consumer<$SystemUnderTest> thenStep);

        AndThenWithoutResult<$SystemUnderTest> and(BooleanSupplier thenStep);
    }

    interface ThenFailure {

        void thenItFails();

        void thenItFails(Class<? extends Throwable> expectedThrowableClass);

        void thenItFails(Class<? extends Throwable> expectedThrowableClass, String expectedMessage);
    }
}