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

import java.text.ParseException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.backend.config.EncoderConfig;
import com.api.backend.config.JwtTokenUtil;
import com.api.backend.helper.CommonHelper;
import com.api.backend.models.entities.User;
import com.api.backend.models.request.FormForgotPassword;
import com.api.backend.models.request.FormLogin;
import com.api.backend.models.request.FormRegister;
import com.api.backend.models.request.FormResetPassword;
import com.api.backend.models.services.JwtUserDetailsService;
import com.api.backend.models.services.UserService;
import com.api.backend.utilities.JsonResponse;
import com.api.backend.utilities.JwtResponse;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService UserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> Login(@RequestBody FormLogin authenticationRequest) {
		try {

			String username = authenticationRequest.getEmail();
			String password = authenticationRequest.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
			final User user = UserService.findByEmail(username);

			if (user.getStatus() == 0) {
				return new ResponseEntity<Object>(new JsonResponse(false,
						"You need to confirm your account. We have sent you an activation code, please check your email.!",
						null), HttpStatus.BAD_REQUEST);
			} else {
				final String token = jwtTokenUtil.generateToken(userDetails);
				return ResponseEntity.ok(new JwtResponse(token));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(
					new JsonResponse(false, "These credentials do not match our records.", null),
					HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/auth/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Register(@RequestBody FormRegister form) throws ParseException {

		if (form.getEmail() == null || form.getEmail().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'email' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getPassword() == null || form.getPassword().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'password' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getPasswordConfirm() == null || form.getPasswordConfirm().trim().isBlank()) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The field 'passwordConfirm' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getPassword().length() < 8) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The password must be at least 8 characters.!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (!form.getPassword().equalsIgnoreCase(form.getPasswordConfirm())) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The password confirmation does not match.!", null),
					HttpStatus.BAD_REQUEST);
		}

		User UserByEmail = UserService.findByEmail(form.getEmail());

		if (UserByEmail != null) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The email address has already been taken.!", null),
					HttpStatus.BAD_REQUEST);
		}

		EncoderConfig enc = new EncoderConfig();
		String password = enc.passwordEncoder().encode(form.getPassword());
		String token = UUID.randomUUID().toString();

		User NewUser = new User();
		NewUser.setPassword(password);	
		NewUser.setEmail(form.getEmail());
		NewUser.setStatus(0);
		NewUser.setConfirmToken(token);
		NewUser.setCreatedAt(CommonHelper.DateNow());
		NewUser.setUpdatedAt(CommonHelper.DateNow());
		UserService.saveOrUpdate(NewUser);

		return new ResponseEntity<Object>(new JsonResponse(true,
				"You need to confirm your account. We have sent you an activation code, please check your email.!",
				NewUser), HttpStatus.OK);
	}

	@RequestMapping(value = "/auth/confirm/{token}", method = RequestMethod.GET)
	public ResponseEntity<?> Confirm(@PathVariable String token) throws ParseException {

		User user = UserService.findByToken(token);

		if (user == null) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "These credentials with token do not match our records.!", null),
					HttpStatus.BAD_REQUEST);
		}

		user.setStatus(1);
		user.setUpdatedAt(CommonHelper.DateNow());
		UserService.saveOrUpdate(user);

		return new ResponseEntity<Object>(new JsonResponse(true, "Your e-mail is verified. You can now login.", user),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/auth/email/forgot", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> ForgotPassword(@RequestBody FormForgotPassword form) throws ParseException {

		if (form.getEmail() == null || form.getEmail().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'email' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		User user = UserService.findByEmail(form.getEmail());

		if (user == null) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "We can't find a user with that e-mail address.", null),
					HttpStatus.BAD_REQUEST);
		}

		String token = UUID.randomUUID().toString();
		user.setResetToken(token);
		user.setUpdatedAt(CommonHelper.DateNow());
		UserService.saveOrUpdate(user);

		return new ResponseEntity<Object>(new JsonResponse(true, "We have e-mailed your password reset link!", token),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/auth/email/reset/{token}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> reset(@PathVariable String token, @RequestBody FormResetPassword form)
			throws ParseException {

		if (form.getEmail() == null || form.getEmail().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'email' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		User user = UserService.findByEmail(form.getEmail());

		if (user == null) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "We can't find a user with that e-mail address.", null),
					HttpStatus.BAD_REQUEST);
		}
		
		if (user.getResetToken() == null) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "We can't find a user with token valid.", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getPassword() == null || form.getPassword().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'password' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getPasswordConfirm() == null || form.getPasswordConfirm().trim().isBlank()) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The field 'passwordConfirm' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getPassword().length() < 8) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The password must be at least 8 characters.!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (!form.getPassword().equalsIgnoreCase(form.getPasswordConfirm())) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The password confirmation does not match.!", null),
					HttpStatus.BAD_REQUEST);
		}
		
		

		if (!user.getResetToken().equalsIgnoreCase(token)) {
			return new ResponseEntity<Object>(
					new JsonResponse(false,
							"We can't find a user with that e-mail address or password reset token is invalid.", null),
					HttpStatus.BAD_REQUEST);
		}

		EncoderConfig enc = new EncoderConfig();
		String password = enc.passwordEncoder().encode(form.getPassword());

		user.setPassword(password);
		user.setResetToken(null);
		user.setUpdatedAt(CommonHelper.DateNow());
		UserService.saveOrUpdate(user);

		return new ResponseEntity<Object>(new JsonResponse(true, "Your password has been reset!", user), HttpStatus.OK);
	}

}
