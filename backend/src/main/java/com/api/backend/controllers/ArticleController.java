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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.api.backend.models.services.ArticleService;
import com.api.backend.models.services.UserService;
import com.api.backend.utilities.JsonResponse;
import com.api.backend.helper.CommonHelper;
import com.api.backend.models.entities.Article;
import com.api.backend.models.entities.ArticleComment;
import com.api.backend.models.entities.Reference;
import com.api.backend.models.entities.User;
import com.api.backend.models.request.ArticleCommentResult;
import com.api.backend.models.request.ArticleCommentTree;
import com.api.backend.models.request.ArticleResult;
import com.api.backend.models.request.FormArticleComment;
import com.api.backend.models.services.ArticleCommentService;


@RestController
public class ArticleController {

	@Autowired
	private ArticleService ArticleService;

	@Autowired
	private ArticleCommentService ArticleCommentService;

	@Autowired
	private UserService UserService;

	@RequestMapping(value = "/article/list", method = RequestMethod.GET)
	public ResponseEntity<?> List(@RequestParam Map<String, String> reqParam) {

		int page = reqParam.get("page") != null ? Integer.parseInt(reqParam.get("page")) : 1;
		int limit = 3 * page;
		ArticleResult NewArticle = ArticleService.NewFirst();
		List<ArticleResult> NewArticles = ArticleService.Teaser();
		List<ArticleResult> Stories = ArticleService.findAll("id desc", limit);
		Boolean Continue = limit <= ArticleService.TotalPublished();

		HashMap<String, Object> result = new HashMap<>();
		result.put("continue", Continue);
		result.put("new_article", NewArticle);
		result.put("new_articles", NewArticles);
		result.put("page", page);
		result.put("stories", Stories);

		return new ResponseEntity<Object>(new JsonResponse(true, "ok", result), HttpStatus.OK);
	}

	@RequestMapping(value = "/article/detail/{slug}", method = RequestMethod.GET)
	public ResponseEntity<?> detail(@PathVariable(value = "slug") String Slug) {

		ArticleResult article = ArticleService.findBySlug(Slug);
		if (article == null) {
			return new ResponseEntity<Object>(
					new JsonResponse(false, "Article with id " + Slug + " was not found.!!", null),
					HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", article), HttpStatus.OK);
	}

	@RequestMapping(value = "/article/comments/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> Comments(@PathVariable(value = "id") long Id) {
		List<ArticleCommentResult> comments = ArticleCommentService.findAll(Id);
		List<ArticleCommentTree> buildTree = ArticleCommentService.BuildTree(comments, 0);
		return new ResponseEntity<Object>(new JsonResponse(true, "ok", buildTree), HttpStatus.OK);
	}

	@RequestMapping(value = "/article/comment/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> Comment(@PathVariable(value = "id") Long Id, @RequestBody FormArticleComment form)
			throws ParseException {

		String credential = CommonHelper.AuthCredential();
		User user = UserService.findByEmail(credential);

		if (form.getComment() == null || form.getComment().trim().isBlank()) {
			return new ResponseEntity<Object>(new JsonResponse(false, "The field 'comment' can not be empty!", null),
					HttpStatus.BAD_REQUEST);
		}

		Article Article = ArticleService.findBy(Id);

		ArticleComment comment = new ArticleComment();
		comment.setArticle(Article);
		comment.setComment(form.getComment());
		comment.setUser(user);
		comment.setCreatedAt(CommonHelper.DateNow());
		comment.setUpdatedAt(CommonHelper.DateNow());
		comment.setStatus(1);
		ArticleCommentService.saveOrUpdate(comment);

		return new ResponseEntity<Object>(new JsonResponse(true, "ok", form), HttpStatus.OK);
	}

}
