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

import com.api.backend.config.JwtTokenUtil;
import com.api.backend.models.request.FormForgotPassword;
import com.api.backend.models.request.FormLogin;
import com.api.backend.models.request.FormRegister;
import com.api.backend.models.request.FormResetPassword;
import com.api.backend.models.services.JwtUserDetailsService;
import com.api.backend.utilities.JsonResponse;
import com.api.backend.utilities.JwtResponse;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

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

			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Object>(new JsonResponse(false, "These credentials do not match our records.", null), HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/auth/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Register(@RequestBody FormRegister form) {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/auth/confirm/{token}", method = RequestMethod.GET)
	public ResponseEntity<?> Confirm(@PathVariable String token) throws ParseException {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/auth/email/forgot", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> ForgotPassword(@RequestBody FormForgotPassword form) {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/auth/email/reset/{token}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> reset(@PathVariable String token, @RequestBody FormResetPassword form)
			throws ParseException {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

}
