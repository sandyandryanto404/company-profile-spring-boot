package com.api.backend.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.api.backend.utilities.JsonResponse;

@RestController
public class PageController {
	
	@RequestMapping(value = "/page/ping", method = RequestMethod.GET)
	public ResponseEntity<?> ping()  {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
    }

}
