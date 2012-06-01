package com.alper.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sfs2x.client.SmartFox;
import sfs2x.client.requests.LoginRequest;

import com.alper.commons.model.BuyBingoCardResponse;
import com.alper.commons.model.DaubResponse;
import com.alper.commons.model.EnterRoomResponse;
import com.alper.commons.model.LoginResponse;

/**
 * Controller for testing JMeter HTTP requests and smart fox. To access the
 * services, hit this URL:
 * http://localhost:8081/MockGameServices/rest/sfs/login/alper/alper
 * 
 * 
 * @author aakture
 * 
 */
@Controller
@RequestMapping("/sfs")
public class JSONController {

	private static final Logger log = LoggerFactory.getLogger(JSONController.class);
	@Autowired
	private SmartFox smartFox;

	@RequestMapping(value = "/login/{name}/{password}", method = RequestMethod.GET)
	public @ResponseBody
	LoginResponse doLogin(@PathVariable String name, @PathVariable String password) {
		log.info("login, name: {}, password: {}", name, password);
		LoginResponse loginResponse = new LoginResponse(name);
		LoginRequest loginRequest = new LoginRequest(name, password, "BasicExamples");
		smartFox.send(loginRequest);
		return loginResponse;
	}

	@RequestMapping(value = "/simpleLogin/{name}/{password}", method = RequestMethod.POST)
	public @ResponseBody
	LoginResponse doSimpleLogin(@PathVariable String name, @PathVariable String password) {
		log.info("simpleLogin, name: {}, password: {}", name, password);
		LoginResponse loginResponse = new LoginResponse(name);
		return loginResponse;
	}

	@RequestMapping(value = "/enterRoom/{name}", method = RequestMethod.POST)
	public @ResponseBody
	EnterRoomResponse enterRoom(@PathVariable String name) {
		log.info("enterRoom, name: {}", name);
		EnterRoomResponse enterRoomResponse = new EnterRoomResponse(name);
		return enterRoomResponse;
	}

	@RequestMapping(value = "/buyBingoCard", method = RequestMethod.POST)
	public @ResponseBody
	BuyBingoCardResponse buyBingoCard() {
		log.info("buyBingoCard");
		BuyBingoCardResponse buyBingCardResponse = new BuyBingoCardResponse(true);
		return buyBingCardResponse;
	}

	@RequestMapping(value = "/daubCard/{row}/{column}", method = RequestMethod.POST)
	public @ResponseBody 
	DaubResponse daubCard(@PathVariable Integer row, @PathVariable Integer column) {
		log.info("daub card, row {}, column {}", row, column);
		return new DaubResponse(true, false);
	}

	public void setSmartFox(SmartFox smartFox) {
		this.smartFox = smartFox;
	}

}