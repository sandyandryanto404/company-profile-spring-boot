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

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.backend.config.EncoderConfig;
import com.api.backend.helper.CommonHelper;
import com.api.backend.models.entities.User;
import com.api.backend.models.request.FormChangePassword;
import com.api.backend.models.request.FormProfile;
import com.api.backend.models.request.FormUploadFile;
import com.api.backend.models.services.FileStorageService;
import com.api.backend.utilities.JsonResponse;
import com.api.backend.models.services.UserService;


@RestController
public class AccountController {

	@Autowired
	private UserService UserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(value = "/account/profile/detail", method = RequestMethod.GET)
	public ResponseEntity<?> DetailProfile() {
		String credential = CommonHelper.AuthCredential();
		User user = UserService.findByEmail(credential);
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", user), HttpStatus.OK);
	}

	@RequestMapping(value = "/account/profile/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> UpdateProfile(@RequestBody FormProfile form) throws ParseException {

		String credential = CommonHelper.AuthCredential();
		User user = UserService.findByEmail(credential);

		Boolean ChangePhone = false;

		if (form.getEmail() == null || form.getEmail().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'email' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		User UserByEmail = UserService.findByEmail(form.getEmail());
		User UserByPhone = UserService.findByPhone(form.getPhone());

		if (UserByEmail != null && UserByEmail.getId() != user.getId()) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The email address has already been taken.!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getPhone() != null || !form.getPhone().trim().isBlank()) {
			ChangePhone = true;
			if (UserByPhone != null && UserByPhone.getId() != user.getId()) {
				return new ResponseEntity<Object>(
						new JsonResponse(false, "The phone number has already been taken.!", null),
						HttpStatus.BAD_REQUEST);
			}
		}

		user.setEmail(form.getEmail());
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setGender(form.getGender());
		user.setCountry(form.getCountry());
		user.setAboutMe(form.getAboutMe());
		user.setAddress(form.getAddress());
		user.setUpdatedAt(CommonHelper.DateNow());

		if (ChangePhone) {
			user.setPhone(form.getPhone());
		}

		UserService.saveOrUpdate(user);

		user = UserService.findByEmail(credential);

		return new ResponseEntity<Object>(new JsonResponse(true, "Your profile has been changed!", user),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/account/password/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> ChangePassword(@RequestBody FormChangePassword form) throws ParseException {

		String credential = CommonHelper.AuthCredential();
		User user = UserService.findByEmail(credential);

		if (form.getOldPassword() == null || form.getOldPassword().trim().isBlank()) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The field 'oldPassword' can not be empty!", null), HttpStatus.BAD_REQUEST);
		}

		if (form.getNewPassword() == null || form.getNewPassword().trim().isBlank()) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The field 'newPassword' can not be empty!", null), HttpStatus.BAD_REQUEST);
		}

		if (form.getPasswordConfirm() == null || form.getPasswordConfirm().trim().isBlank()) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The field 'passwordConfirm' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getNewPassword().length() < 8) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The password must be at least 8 characters.!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (!form.getNewPassword().equalsIgnoreCase(form.getPasswordConfirm())) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The password confirmation does not match.!", null),
					HttpStatus.BAD_REQUEST);
		}

		boolean matches = passwordEncoder.matches(form.getOldPassword(), user.getPassword());
		if (!matches) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The current password does not match!", null),
					HttpStatus.BAD_REQUEST);
		}

		EncoderConfig enc = new EncoderConfig();
		String password = enc.passwordEncoder().encode(form.getNewPassword());

		user.setPassword(password);
		user.setUpdatedAt(CommonHelper.DateNow());
		UserService.saveOrUpdate(user);

		return new ResponseEntity<Object>(new JsonResponse(true, "Your password has been changed", user),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/account/profile/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Upload(@RequestParam("file") MultipartFile file) throws ParseException {

		String credential = CommonHelper.AuthCredential();
		User user = UserService.findByEmail(credential);

		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/page/file/")
				.path(fileName).toUriString();

		FormUploadFile result = new FormUploadFile(fileName, fileDownloadUri, file.getContentType(), file.getSize());

		if (result.getFileDownloadUri() != null) {
			user.setImage(fileName);
			user.setUpdatedAt(CommonHelper.DateNow());
			UserService.saveOrUpdate(user);
		}

		return new ResponseEntity<Object>(new JsonResponse(true, "ok", result), HttpStatus.OK);
	}

}
