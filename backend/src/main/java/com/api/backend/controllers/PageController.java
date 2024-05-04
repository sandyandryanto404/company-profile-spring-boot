/**
 * This file is part of the Sandy Andryanto Company Profile Website.
 *
 * @author     Sandy Andryanto <sandy.andryanto404@gmail.com>
 * @copyright  2024
 *
 * For the full copyright and license information,
 * please view the LICENSE.md file that was distributed
 * with this source code.
 */

package com.api.backend.controllers;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.api.backend.models.request.FormMessage;
import com.api.backend.models.request.FormSubscribe;
import com.api.backend.models.services.FileStorageService;
import com.api.backend.utilities.JsonResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(value = "/page/ping", method = RequestMethod.GET)
	public ResponseEntity<?> Ping() {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/home", method = RequestMethod.GET)
	public ResponseEntity<?> Home() {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/about", method = RequestMethod.GET)
	public ResponseEntity<?> About() {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/service", method = RequestMethod.GET)
	public ResponseEntity<?> Service() {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/faq", method = RequestMethod.GET)
	public ResponseEntity<?> Faq() {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/contact", method = RequestMethod.GET)
	public ResponseEntity<?> Contact() {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Message(@RequestBody FormMessage form) {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/subscribe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Subscribe(@RequestBody FormSubscribe form) {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@GetMapping("/page/download/{fileName:.+}")
	public ResponseEntity<?> Download(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
