package info.rayan.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public class FacesMessageBuilder {
	private String summary;
	private String details;
	private Severity servity;

	public static FacesMessageBuilder create(String summary) {
		FacesMessageBuilder builder = new FacesMessageBuilder();
		builder.summary = summary;
		return builder;
	}

	public static FacesMessageBuilder create() {
		return new FacesMessageBuilder();
	}

	public FacesMessageBuilder summary(String summary) {
		this.summary = summary;
		return this;
	}

	public FacesMessageBuilder details(String details) {
		this.details = details;
		return this;

	}

	public FacesMessageBuilder servity(FacesMessage.Severity servity) {
		this.servity = servity;
		return this;
	}

	public FacesMessage build() {
		return new FacesMessage(servity == null ? FacesMessage.SEVERITY_INFO
				: servity, summary, details);
	}
}
