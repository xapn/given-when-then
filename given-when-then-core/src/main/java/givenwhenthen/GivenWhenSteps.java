package givenwhenthen;

import java.util.function.Consumer;
import java.util.function.Function;

class GivenWhenSteps<$SystemUnderTest, $Result> {

    private $SystemUnderTest systemUnderTest;
    private Consumer<$SystemUnderTest> givenStep;
    private Function<$SystemUnderTest, $Result> whenStep;

    GivenWhenSteps($SystemUnderTest systemUnderTest) {
        this.systemUnderTest = systemUnderTest;
    }

    $Result returnResult() {
        if (givenStep != null) {
            givenStep.accept(systemUnderTest);
        }
        return whenStep.apply(systemUnderTest);
    }

    void setGivenStep(Consumer<$SystemUnderTest> givenStep) {
        this.givenStep = givenStep;
    }

    void setWhenStep(Function<$SystemUnderTest, $Result> whenStep) {
        this.whenStep = whenStep;
    }
}