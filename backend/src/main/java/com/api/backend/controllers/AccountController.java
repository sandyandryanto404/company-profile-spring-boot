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

import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.backend.models.request.FormChangePassword;
import com.api.backend.models.request.FormProfile;
import com.api.backend.models.request.FormUploadFile;
import com.api.backend.models.services.FileStorageService;
import com.api.backend.utilities.JsonResponse;

@RestController
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(value = "/account/profile/detail", method = RequestMethod.GET)
	public ResponseEntity<?> DetailProfile() {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/account/profile/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> UpdateProfile(@RequestBody FormProfile form) {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/account/password/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> ChangePassword(@RequestBody FormChangePassword form) {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/account/profile/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Upload(@RequestParam("file") MultipartFile file) {

		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/download/")
				.path(fileName).toUriString();

		FormUploadFile result = new FormUploadFile(fileName, fileDownloadUri, file.getContentType(), file.getSize());

		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

}
