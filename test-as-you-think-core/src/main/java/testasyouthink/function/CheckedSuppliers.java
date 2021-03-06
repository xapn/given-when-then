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

package testasyouthink.function;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

public interface CheckedSuppliers {

    interface CheckedCharacterSupplier extends CheckedSupplier<Character> {}

    interface CheckedCharSequenceSupplier extends CheckedSupplier<CharSequence> {}

    interface CheckedStringSupplier extends CheckedSupplier<String> {}

    interface CheckedByteSupplier extends CheckedSupplier<Byte> {}

    interface CheckedShortSupplier extends CheckedSupplier<Short> {}

    interface CheckedIntegerSupplier extends CheckedSupplier<Integer> {}

    interface CheckedLongSupplier extends CheckedSupplier<Long> {}

    interface CheckedFloatSupplier extends CheckedSupplier<Float> {}

    interface CheckedDoubleSupplier extends CheckedSupplier<Double> {}

    interface CheckedBigIntegerSupplier extends CheckedSupplier<BigInteger> {}

    interface CheckedBigDecimalSupplier extends CheckedSupplier<BigDecimal> {}

    interface CheckedOptionalSupplier<$Value> extends CheckedSupplier<Optional<$Value>> {}

    interface CheckedOptionalIntSupplier extends CheckedSupplier<OptionalInt> {}

    interface CheckedOptionalLongSupplier extends CheckedSupplier<OptionalLong> {}

    interface CheckedOptionalDoubleSupplier extends CheckedSupplier<OptionalDouble> {}

    interface CheckedBooleanSupplier extends CheckedSupplier<Boolean> {}

    interface CheckedDateSupplier extends CheckedSupplier<Date> {}

    interface CheckedLocalDateSupplier extends CheckedSupplier<LocalDate> {}

    interface CheckedLocalDateTimeSupplier extends CheckedSupplier<LocalDateTime> {}

    interface CheckedLocalTimeSupplier extends CheckedSupplier<LocalTime> {}

    interface CheckedInstantSupplier extends CheckedSupplier<Instant> {}

    interface CheckedFileSupplier extends CheckedSupplier<File> {}

    interface CheckedPathSupplier extends CheckedSupplier<Path> {}

    interface CheckedUriSupplier extends CheckedSupplier<URI> {}

    interface CheckedUrlSupplier extends CheckedSupplier<URL> {}

    interface CheckedIterableSupplier<$Element> extends CheckedSupplier<Iterable<$Element>> {}

    interface CheckedIteratorSupplier<$Element> extends CheckedSupplier<Iterator<$Element>> {}

    interface CheckedListSupplier<$Element> extends CheckedSupplier<List<$Element>> {}

    interface CheckedMapSupplier<$Key, $Value> extends CheckedSupplier<Map<$Key, $Value>> {}

    interface CheckedAtomicBooleanSupplier extends CheckedSupplier<AtomicBoolean> {}

    interface CheckedAtomicIntegerSupplier extends CheckedSupplier<AtomicInteger> {}

    interface CheckedAtomicIntegerArraySupplier extends CheckedSupplier<AtomicIntegerArray> {}

    interface CheckedAtomicLongSupplier extends CheckedSupplier<AtomicLong> {}

    interface CheckedAtomicLongArraySupplier extends CheckedSupplier<AtomicLongArray> {}

    interface CheckedFutureSupplier<$Value> extends CheckedSupplier<Future<$Value>> {}

    interface CheckedCompletableFutureSupplier<$Value> extends CheckedSupplier<CompletableFuture<$Value>> {}

    interface CheckedClassSupplier extends CheckedSupplier<Class> {}

    interface CheckedPredicateSupplier<$Value> extends CheckedSupplier<Predicate<$Value>> {}

    interface CheckedIntPredicateSupplier extends CheckedSupplier<IntPredicate> {}

    interface CheckedLongPredicateSupplier extends CheckedSupplier<LongPredicate> {}

    interface CheckedDoublePredicateSupplier extends CheckedSupplier<DoublePredicate> {}

    interface CheckedArraySupplier<R> {

        R[] get() throws Throwable;
    }

    interface CheckedInputStreamSupplier extends CheckedSupplier<InputStream> {}
}
