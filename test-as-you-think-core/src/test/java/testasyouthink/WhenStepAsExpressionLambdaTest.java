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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import testasyouthink.fixture.GivenWhenThenDefinition;
import testasyouthink.fixture.SystemUnderTest;
import testasyouthink.function.CheckedConsumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static testasyouthink.TestAsYouThink.givenSutClass;
import static testasyouthink.TestAsYouThink.withReturn;

/**
 * Acceptance testing for the execution step to use lambda expressions without ambiguity.</br>
 * <ul>
 * <li>use {@code whenSutReturns} if the target methdd returns a result</li>
 * <li>use {@code whenSutRuns} otherwise</li>
 * </ul>
 */
class WhenStepAsExpressionLambdaTest {

    private GivenWhenThenDefinition givenWhenThenDefinitionMock;

    @BeforeEach
    void prepareFixtures() {
        givenWhenThenDefinitionMock = mock(GivenWhenThenDefinition.class);
    }

    @AfterEach
    void verifyMocks() {
        // THEN
        verify(givenWhenThenDefinitionMock).thenTheActualResultIsInKeepingWithTheExpectedResult();
        verifyNoMoreInteractions(givenWhenThenDefinitionMock);
    }

    @Nested
    class When_receiving_a_function {

        @Test
        void should_accept_a_function_as_a_lambda_without_ambiguity_by_using_an_intermediary_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .when(withReturn(sut -> sut.nonVoidMethod()))
                    .then((CheckedConsumer<String>) result -> givenWhenThenDefinitionMock
                            .thenTheActualResultIsInKeepingWithTheExpectedResult());
        }

        @Test
        void should_accept_a_function_as_a_lambda_without_ambiguity_by_using_an_alternate_when_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .whenSutReturns(sut -> sut.nonVoidMethod())
                    .then((CheckedConsumer<String>) result -> givenWhenThenDefinitionMock
                            .thenTheActualResultIsInKeepingWithTheExpectedResult());
        }

        @Test
        void should_accept_a_bifunction_as_a_lambda_without_ambiguity_by_using_an_alternate_when_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .givenArgument("one argument", "argument")
                    .whenSutReturns((sut, argument) -> sut.nonVoidMethodWithParameter(argument))
                    .then((CheckedConsumer<String>) result -> givenWhenThenDefinitionMock
                            .thenTheActualResultIsInKeepingWithTheExpectedResult());
        }

        @Test
        void should_accept_a_trifunction_as_a_lambda_without_ambiguity_by_using_an_alternate_when_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .givenArgument("first argument", "argument")
                    .andArgument("second argument", 20170622)
                    .whenSutReturns(
                            (sut, argument1, argument2) -> sut.nonVoidMethodWithTwoParameters(argument1, argument2))
                    .then((CheckedConsumer<String>) result -> givenWhenThenDefinitionMock
                            .thenTheActualResultIsInKeepingWithTheExpectedResult());
        }

        @Test
        void should_accept_a_quadrifunction_as_a_lambda_without_ambiguity_by_using_an_alternate_when_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .givenArgument("first argument", "argument")
                    .andArgument("second argument", 20170622)
                    .andArgument("third argument", true)
                    .whenSutReturns(
                            (sut, argument1, argument2, argument3) -> sut.nonVoidMethodWithThreeParameters(argument1,
                                    argument2, argument3))
                    .then((CheckedConsumer<String>) result -> givenWhenThenDefinitionMock
                            .thenTheActualResultIsInKeepingWithTheExpectedResult());
        }
    }

    @Nested
    class When_receiving_a_consumer {

        @Test
        void should_accept_a_consumer_as_a_lambda_without_ambiguity_by_using_an_alternate_when_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .whenSutRuns(sut -> sut.voidMethod())
                    .then(() -> givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult());
        }

        @Test
        void should_accept_a_biconsumer_as_a_lambda_without_ambiguity_by_using_an_alternate_when_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .givenArgument("one argument", "argument")
                    .whenSutRuns((sut, argument) -> sut.voidMethodWithParameter(argument))
                    .then(() -> givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult());
        }

        @Test
        void should_accept_a_triconsumer_as_a_lambda_without_ambiguity_by_using_an_alternate_when_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .givenArgument("first argument", "argument")
                    .andArgument("second argument", 20170622)
                    .whenSutRuns((sut, argument1, argument2) -> sut.voidMethodWithTwoParameters(argument1, argument2))
                    .then(() -> givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult());
        }

        @Test
        void should_accept_a_quadriconsumer_as_a_lambda_without_ambiguity_by_using_an_alternate_when_method() {
            // WHEN
            givenSutClass(SystemUnderTest.class)
                    .givenArgument("first argument", "argument")
                    .andArgument("second argument", 20170622)
                    .andArgument("third argument", true)
                    .whenSutRuns((sut, argument1, argument2, argument3) -> sut.voidMethodWithThreeParameters(argument1,
                            argument2, argument3))
                    .then(() -> givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult());
        }
    }
}
