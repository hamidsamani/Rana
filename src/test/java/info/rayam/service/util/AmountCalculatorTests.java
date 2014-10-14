package info.rayam.service.util;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class AmountCalculatorTests {
	@Test
	public void bigDecimalComparissionWorksAsExpected() {
		BigDecimal zero = new BigDecimal(0.0);
		Assert.assertEquals(zero, BigDecimal.ZERO);
	}
}
