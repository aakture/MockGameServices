package com.alper.common.controller;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sfs2x.client.SmartFox;
import sfs2x.client.core.SFSEvent;

@Configuration
public class AppConfig {
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public Startup initStartup() {
		return new Startup();
	}

	@Bean
	public SmartFox initSmartFox() {
		SmartFox sfs = new SmartFox(true);
		URL configFileURL = this.getClass().getResource("/config.xml");
		if (configFileURL == null) {
			log.error("error loading SmartFox config.xml file");
			throw new RuntimeException(
					"SmartFoxServer config.xml could not be found. Make sure it's available in the classpath.");
		}
		sfs.loadConfig(configFileURL.getPath());
		sfs.addEventListener(SFSEvent.LOGIN, new LoginEventHandler());
		return sfs;
	}

}
