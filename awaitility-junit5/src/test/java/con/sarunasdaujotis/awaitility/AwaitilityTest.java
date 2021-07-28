package con.sarunasdaujotis.awaitility;

import java.util.concurrent.atomic.AtomicReference;
import org.awaitility.core.ConditionEvaluationListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.awaitility.Awaitility.await;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AwaitilityTest {

	@ParameterizedTest
	@ValueSource(shorts = {1, 2, 3, 6})
	void must_set_atomic_reference(final Short expected) {
		final AtomicReference<Short> shortAtomicReference = new AtomicReference<>();
		await()
				.conditionEvaluationListener((ConditionEvaluationListener<Short>) condition -> shortAtomicReference.set(condition.getValue()))
				.until(() -> expected, value -> value.equals(expected));

		Assertions.assertEquals(expected, shortAtomicReference.get());
	}
}
