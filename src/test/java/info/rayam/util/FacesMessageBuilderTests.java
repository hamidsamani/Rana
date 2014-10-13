package info.rayam.util;

import static org.junit.Assert.assertEquals;

import javax.faces.application.FacesMessage;

import info.rayan.util.FacesMessageBuilder;

import org.junit.Test;

public class FacesMessageBuilderTests {
	private static final String SUMMARY = "summary";
	private static final String DETAILS = "details";

	@Test
	public void summarySetsCorrectly() {
		FacesMessage message = FacesMessageBuilder.create(SUMMARY).build();
		assertEquals(message.getSummary(), SUMMARY);
	}

	@Test
	public void servitySetsCorrectlyInTheCaseOfNullValue() {
		assertEquals(FacesMessage.SEVERITY_INFO, FacesMessageBuilder.create()
				.build().getSeverity());
	}

	@Test
	public void summaryDetailsSetCorrectly() {
		FacesMessage message = FacesMessageBuilder.create().summary(SUMMARY)
				.details(DETAILS).build();
		assertEquals(message.getSummary(), SUMMARY);
		assertEquals(message.getDetail(), DETAILS);
	}
}
