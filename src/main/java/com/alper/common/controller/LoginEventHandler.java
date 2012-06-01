package com.alper.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sfs2x.client.core.BaseEvent;
import sfs2x.client.core.IEventListener;

import com.smartfoxserver.v2.exceptions.SFSException;

public class LoginEventHandler implements IEventListener {
	private static final Logger log = LoggerFactory.getLogger(LoginEventHandler.class);

	public void init() {
		log.info("init loginEventHandler");
	}
	@Override
	public void dispatch(BaseEvent baseEvent) throws SFSException {
		log.info("received event: {}", baseEvent.getType());
	}

}
