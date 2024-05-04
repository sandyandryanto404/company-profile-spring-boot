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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

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
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.api.backend.models.entities.Contact;
import com.api.backend.models.entities.Faq;
import com.api.backend.models.entities.Slider;
import com.api.backend.models.entities.Team;
import com.api.backend.models.entities.Testimonial;
import com.api.backend.models.entities.Service;
import com.api.backend.helper.CommonHelper;
import com.api.backend.models.entities.Article;
import com.api.backend.models.entities.Customer;
import com.api.backend.models.request.FormMessage;
import com.api.backend.models.request.FormSubscribe;
import com.api.backend.models.services.FileStorageService;
import com.api.backend.models.services.SliderService;
import com.api.backend.models.services.ServiceService;
import com.api.backend.models.services.TestimonialService;
import com.api.backend.models.services.ArticleService;
import com.api.backend.models.services.TeamService;
import com.api.backend.models.services.CustomerService;
import com.api.backend.models.services.FaqService;
import com.api.backend.models.services.ContactService;
import com.api.backend.utilities.JsonResponse;
import com.github.javafaker.Faker;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private SliderService SliderService;
	
	@Autowired
	private ServiceService ServiceService;
	
	@Autowired
	private TestimonialService TestimonialService;
	
	@Autowired
	private ArticleService ArticleService;
	
	@Autowired
	private CustomerService CustomerService;
	
	@Autowired
	private TeamService TeamService;
	
	@Autowired
	private FaqService FaqService;
	
	@Autowired
	private ContactService ContactService;

	@RequestMapping(value = "/page/ping", method = RequestMethod.GET)
	public ResponseEntity<?> Ping() {
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/home", method = RequestMethod.GET)
	public ResponseEntity<?> Home() {
		
		Faker faker = new Faker();
		HashMap<String, Object> result = new HashMap<>();
		
		HashMap<String, String> header = new HashMap<>();
		header.put("title", faker.lorem().paragraph(3));
		header.put("description", faker.lorem().paragraph(10));
		
		List<Slider> sliders = SliderService.findAll("random()", 100);
		List<Service> services = ServiceService.findAll("random()", 4);
		List<Testimonial> testimonials = TestimonialService.findAll("random()", 1);
		List<Article> articles = ArticleService.findAll("random()", 3);
		
		result.put("header", header);
		result.put("sliders", sliders);
		result.put("services", services);
		result.put("testimonial", testimonials.get(0));
		result.put("articles", articles);
		
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", result), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/about", method = RequestMethod.GET)
	public ResponseEntity<?> About() {
		
		Faker faker = new Faker();
		HashMap<String, Object> result = new HashMap<>();
		
		HashMap<String, String> header = new HashMap<>();
		header.put("title", faker.lorem().paragraph(3));
		header.put("description", faker.lorem().paragraph(10));
		
		HashMap<String, String> section1 = new HashMap<>();
		section1.put("title", faker.lorem().paragraph(3));
		section1.put("description", faker.lorem().paragraph(10));
		
		HashMap<String, String> section2 = new HashMap<>();
		section2.put("title", faker.lorem().paragraph(3));
		section2.put("description", faker.lorem().paragraph(10));
		
		List<Team> teams = TeamService.findAll("random()", 100);
		
		result.put("header", header);
		result.put("section1", section1);
		result.put("section2", section2);
		result.put("teams", teams);
		
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", result), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/service", method = RequestMethod.GET)
	public ResponseEntity<?> Service() {
		
		Faker faker = new Faker();
		HashMap<String, Object> result = new HashMap<>();
		
		HashMap<String, String> header = new HashMap<>();
		header.put("title", faker.lorem().paragraph(3));
		header.put("description", faker.lorem().paragraph(10));
		
		List<Service> services = ServiceService.findAll("random()", 100);
		List<Customer> customers = CustomerService.getRandom(100);
		List<Testimonial> testimonials = TestimonialService.findAll("random()", 100);
		
		result.put("header", header);
		result.put("services", services);
		result.put("customers", customers);
		result.put("testimonials", testimonials);
		
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", result), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/faq", method = RequestMethod.GET)
	public ResponseEntity<?> Faq() {
		
		HashMap<String, Object> result = new HashMap<>();
		
		List<Faq> faq1 = FaqService.findAll1();
		List<Faq> faq2 = FaqService.findAll2();
		
		result.put("faq1", faq1);
		result.put("faq2", faq2);
		
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", result), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/contact", method = RequestMethod.GET)
	public ResponseEntity<?> Contact() {
		HashMap<String, Object> result = new HashMap<>();
		List<Service> services = ServiceService.findAll("random()", 4);
		result.put("services", services);
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", result), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Message(@RequestBody FormMessage form) throws ParseException {

		if (form.getSubject() == null || form.getSubject().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'subject' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getEmail() == null || form.getEmail().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'email' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getName() == null || form.getName().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'name' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		if (form.getMessage() == null || form.getMessage().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'message' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		Contact contact = new Contact();
		contact.setName(form.getName());
		contact.setEmail(form.getEmail());
		contact.setSubject(form.getSubject());
		contact.setMessage(form.getMessage());
		contact.setCreatedAt(CommonHelper.DateNow());
		contact.setUpdatedAt(CommonHelper.DateNow());
		contact.setStatus(0);
		ContactService.saveOrUpdate(contact);

		return new ResponseEntity<Object>(new JsonResponse(true, "ok", contact), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/subscribe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Subscribe(@RequestBody FormSubscribe form) {
		
		if (form.getEmail() == null || form.getEmail().trim().isBlank()) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "The field 'email' can not be empty!", null), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", null), HttpStatus.OK);
	}

	@GetMapping(value = "/page/file/{fileName:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] GetFile(@PathVariable String fileName) throws IOException {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		File initialFile = new File(resource.getFile().getAbsolutePath());
		InputStream inputStream = new FileInputStream(initialFile);
		return IOUtils.toByteArray(inputStream);
	}

}
