/*-
 * #%L
 * Test As You Think
 * %%
 * Copyright (C) 2017 - 2018 Xavier Pigeon and TestAsYouThink contributors
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

import testasyouthink.GivenWhenThenDsl.PreparationStage.AndGivenArgument;
import testasyouthink.GivenWhenThenDsl.PreparationStage.AndGivenTwoArguments;
import testasyouthink.GivenWhenThenDsl.VerificationStage.Then;
import testasyouthink.GivenWhenThenDsl.VerificationStage.ThenFailure;
import testasyouthink.GivenWhenThenDsl.VerificationStage.ThenWithoutResult;
import testasyouthink.function.CheckedBiConsumer;
import testasyouthink.function.CheckedBiFunction;
import testasyouthink.function.CheckedConsumer;
import testasyouthink.function.CheckedSupplier;
import testasyouthink.function.Functions;
import testasyouthink.preparation.Preparation;

public class GivenArgumentWhenSteps<$SystemUnderTest, $Argument> implements AndGivenArgument<$SystemUnderTest,
        $Argument> {

    private final Functions functions = Functions.INSTANCE;
    private final Preparation<$SystemUnderTest> preparation;
    private final ThenStepFactory thenStepFactory = ThenStepFactory.INSTANCE;

    GivenArgumentWhenSteps(Preparation<$SystemUnderTest> preparation) {this.preparation = preparation;}

    @Override
    public <$Argument2> AndGivenTwoArguments<$SystemUnderTest, $Argument, $Argument2> andArgument(
            CheckedSupplier<$Argument2> givenStep) {
        preparation.recordGivenStep(givenStep);
        return new GivenTwoArgumentsWhenSteps<>(preparation);
    }

    @Override
    public <$Argument2> AndGivenTwoArguments<$SystemUnderTest, $Argument, $Argument2> andArgument(
            Class<$Argument2> mutableArgumentClass, CheckedConsumer<$Argument2> givenStep) {
        preparation.recordGivenStep(mutableArgumentClass, givenStep);
        return new GivenTwoArgumentsWhenSteps<>(preparation);
    }

    @Override
    public <$Argument2> AndGivenTwoArguments<$SystemUnderTest, $Argument, $Argument2> andArgument(String description,
            CheckedSupplier<$Argument2> givenStep) {
        return andArgument(givenStep);
    }

    @Override
    public <$Argument2> AndGivenTwoArguments<$SystemUnderTest, $Argument, $Argument2> andArgument(String description,
            $Argument2 argument) {
        preparation.recordGivenStep(functions.toCheckedSupplier(argument));
        return new GivenTwoArgumentsWhenSteps<>(preparation);
    }

    @Override
    public ThenWithoutResult<$SystemUnderTest> when(CheckedBiConsumer<$SystemUnderTest, $Argument> whenStep) {
        return thenStepFactory.createThenStep(preparation,
                functions.toConsumer(whenStep, preparation.getArgumentSuppliers()));
    }

    @Override
    public ThenWithoutResult<$SystemUnderTest> whenSutRuns(CheckedBiConsumer<$SystemUnderTest, $Argument> whenStep) {
        return when(whenStep);
    }

    @Override
    public <$Result> Then<$SystemUnderTest, $Result> when(
            CheckedBiFunction<$SystemUnderTest, $Argument, $Result> whenStep) {
        return thenStepFactory.createThenStep(preparation,
                functions.toFunction(whenStep, preparation.getArgumentSuppliers()));
    }

    @Override
    public <$Result> Then<$SystemUnderTest, $Result> whenSutReturns(
            CheckedBiFunction<$SystemUnderTest, $Argument, $Result> whenStep) {
        return when(whenStep);
    }

    @Override
    public ThenFailure whenSutRunsOutsideOperatingConditions(CheckedBiConsumer<$SystemUnderTest, $Argument> whenStep) {
        return thenStepFactory.createThenStep(preparation, functions.toFunctionWithThrowableAsResult(
                functions.toConsumer(whenStep, preparation.getArgumentSuppliers())));
    }
}
