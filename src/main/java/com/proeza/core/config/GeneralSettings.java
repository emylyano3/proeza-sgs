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

	@Value("${app.view.cache}")
	private boolean	viewCacheEnabled;

	public String getCrossOriginSource () {
		return crossOriginSource;
	}

	public void setCrossOriginSource (String crossOriginSource) {
		this.crossOriginSource = crossOriginSource;
	}

	public String getMaxUploadSize () {
		return maxUploadSize;
	}

	public void setMaxUploadSize (String maxUploadSize) {
		this.maxUploadSize = maxUploadSize;
	}

	public boolean isViewCacheEnabled () {
		return viewCacheEnabled;
	}

	public void setViewCacheEnabled (boolean viewCacheEnabled) {
		this.viewCacheEnabled = viewCacheEnabled;
	}
}