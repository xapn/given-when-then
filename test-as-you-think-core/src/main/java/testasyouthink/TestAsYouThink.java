/*-
 * #%L
 * Test As You Think
 * %%
 * Copyright (C) 2017 Xavier Pigeon and TestAsYouThink contributors
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

package testasyouthink;

import testasyouthink.GivenWhenThenDsl.PreparationStage.Given;
import testasyouthink.function.CheckedConsumer;
import testasyouthink.function.CheckedFunction;

public class TestAsYouThink {

    public static <$SystemUnderTest> Given<$SystemUnderTest> givenSut($SystemUnderTest systemUnderTest) {
        return new GivenWhenSteps<>(systemUnderTest);
    }

    public static <$SystemUnderTest> Given<$SystemUnderTest> givenSutClass(Class<$SystemUnderTest> sutClass) {
        try {
            return new GivenWhenSteps<>(sutClass.newInstance());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public static ThenWithoutResultStep<Void> when(Runnable whenStep) {
        Preparation<Void> nothingToPrepare = new Preparation<>(null);
        CheckedConsumer<Void> whenStepAsVoidConsumer = Void -> whenStep.run();
        Event<Void, Void> event = new Event<>(null, whenStepAsVoidConsumer);
        GivenWhenContext<Void, Void> context = new GivenWhenContext<>(nothingToPrepare, event);
        return new ThenWithoutResultStep<>(context);
    }

    public static <$SystemUnderTest, $Result> CheckedFunction<$SystemUnderTest, $Result> withReturn(
            CheckedFunction<$SystemUnderTest, $Result> whenStep) {
        return whenStep;
    }
}
