package com.proeza.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneralSettings extends Settings {

	public GeneralSettings() {
	}

	@Value("${app.view.cache}")
	private boolean viewCacheEnabled;

	public boolean isViewCacheEnabled() {
		return viewCacheEnabled;
	}

	public void setViewCacheEnabled(boolean viewCacheEnabled) {
		this.viewCacheEnabled = viewCacheEnabled;
	}
}