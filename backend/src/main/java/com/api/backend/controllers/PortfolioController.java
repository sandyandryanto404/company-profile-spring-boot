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

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.backend.models.entities.Portfolio;
import com.api.backend.models.entities.Service;
import com.api.backend.models.services.PortfolioService;
import com.api.backend.utilities.JsonResponse;

@RestController
public class PortfolioController {

	@Autowired
	private PortfolioService PortfolioService;

	@RequestMapping(value = "/portfolio/list", method = RequestMethod.GET)
	public ResponseEntity<?> List() {
		List<Portfolio> portfolios = PortfolioService.findAll();
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", portfolios), HttpStatus.OK);
	}

	@RequestMapping(value = "/portfolio/detail/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> detail(@PathVariable(value = "id") Long Id) {

		Portfolio DataModel = PortfolioService.findBy(Id);
		if (DataModel == null) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "Record with id " + Id + " was not found.!!", null),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(new JsonResponse(true, "ok", DataModel), HttpStatus.OK);
	}

}
