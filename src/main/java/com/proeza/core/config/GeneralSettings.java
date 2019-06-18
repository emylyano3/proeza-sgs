package com.proeza.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneralSettings extends Settings {

	public GeneralSettings () {
	}

	@Value(value = "${cross.origin.source}")
	private String	crossOriginSource;

	@Value(value = "${app.maxUploadSize}")
	private String	maxUploadSize;

	@Value(value = "${app.sessionTime}")
	private String	sessionTime;

	@Value("${app.view.cache}")
	private boolean	viewCacheEnabled;

	public String getCrossOriginSource () {
		return this.crossOriginSource;
	}

	public void setCrossOriginSource (String crossOriginSource) {
		this.crossOriginSource = crossOriginSource;
	}

	public String getMaxUploadSize () {
		return this.maxUploadSize;
	}

	public void setMaxUploadSize (String maxUploadSize) {
		this.maxUploadSize = maxUploadSize;
	}

	public String getSessionTime () {
		return this.sessionTime;
	}

	public void setSessionTime (String sessionTime) {
		this.sessionTime = sessionTime;
	}

	public boolean isViewCacheEnabled () {
		return this.viewCacheEnabled;
	}

	public void setViewCacheEnabled (boolean viewCacheEnabled) {
		this.viewCacheEnabled = viewCacheEnabled;
	}
}