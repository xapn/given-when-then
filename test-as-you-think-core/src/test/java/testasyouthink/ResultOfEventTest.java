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

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testasyouthink.execution.ExecutionError;
import testasyouthink.fixture.GivenWhenThenDefinition;
import testasyouthink.fixture.UnexpectedException;
import testasyouthink.function.CheckedSuppliers.CheckedStringSupplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static testasyouthink.TestAsYouThink.resultOf;
import static testasyouthink.fixture.Specifications.ExpectedMessage.EXPECTED_EXECUTION_FAILURE_MESSAGE;

public class ResultOfEventTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultOfEventTest.class);
    private GivenWhenThenDefinition gwtMock;

    @Before
    public void prepareFixtures() {
        gwtMock = mock(GivenWhenThenDefinition.class);
    }

    @Test
    public void should_verify_an_actual_string_is_conform_to_an_expected_result() {
        assertThat(resultOf(() -> {
            gwtMock.whenAnEventHappensInRelationToAnActionOfTheConsumer();
            return "expected result";
        })
                .isEqualTo("expected result")
                .contains("result")).hasSameClassAs(assertThat(""));
        verify(gwtMock).whenAnEventHappensInRelationToAnActionOfTheConsumer();
        verifyNoMoreInteractions(gwtMock);
    }

    @Test
    public void should_fail_to_verify_an_actual_string_is_conform_to_an_expected_result() {
        // WHEN
        Throwable thrown = catchThrowable(() -> resultOf(() -> {
            gwtMock.whenAnEventHappensInRelationToAnActionOfTheConsumer();
            return "actual";
        }).isEqualTo("expected"));

        // THEN
        LOGGER.debug("Stack trace", thrown);
        assertThat(thrown).isInstanceOf(AssertionError.class);
    }

    @Test
    public void should_fail_to_execute_because_of_an_unexpected_failure() {
        // WHEN
        Throwable thrown = catchThrowable(() -> resultOf((CheckedStringSupplier) () -> {
            gwtMock.whenAnEventHappensInRelationToAnActionOfTheConsumer();
            throw new UnexpectedException();
        }).isEqualTo("expected"));

        // THEN
        LOGGER.debug("Stack trace", thrown);
        assertThat(thrown)
                .isInstanceOf(ExecutionError.class)
                .hasMessage(EXPECTED_EXECUTION_FAILURE_MESSAGE);
    }

    @Test
    public void should_verify_an_actual_integer_is_conform_to_an_expected_result() {
        assertThat(resultOf(() -> {
            gwtMock.whenAnEventHappensInRelationToAnActionOfTheConsumer();
            return 123;
        }).isEqualTo(123)).hasSameClassAs(assertThat(123));
        verify(gwtMock).whenAnEventHappensInRelationToAnActionOfTheConsumer();
        verifyNoMoreInteractions(gwtMock);
    }

    @Test
    public void should_verify_an_actual_long_is_conform_to_an_expected_result() {
        assertThat(resultOf(() -> {
            gwtMock.whenAnEventHappensInRelationToAnActionOfTheConsumer();
            return 123L;
        }).isEqualTo(123L)).hasSameClassAs(assertThat(123L));
        verify(gwtMock).whenAnEventHappensInRelationToAnActionOfTheConsumer();
        verifyNoMoreInteractions(gwtMock);
    }

    @Test
    public void should_verify_an_actual_boolean_is_conform_to_an_expected_result() {
        assertThat(resultOf(() -> {
            gwtMock.whenAnEventHappensInRelationToAnActionOfTheConsumer();
            return false;
        }).isFalse()).hasSameClassAs(assertThat(false));
        verify(gwtMock).whenAnEventHappensInRelationToAnActionOfTheConsumer();
        verifyNoMoreInteractions(gwtMock);
    }
}
