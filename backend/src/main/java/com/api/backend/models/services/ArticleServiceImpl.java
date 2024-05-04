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

package com.api.backend.models.services;

import com.api.backend.models.entities.Article;
import com.api.backend.models.entities.Portfolio;
import com.api.backend.models.repositories.ArticleRepository;
import com.api.backend.models.request.ArticleResult;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository repo;

	@Override
	public Long TotalRows() {
		return this.repo.count();
	}

	@Override
	public Article saveOrUpdate(Article model) {
		repo.save(model);
		return model;
	}

	@Override
	public List<ArticleResult> findAll(String orderBy, int Limit) {
		// TODO Auto-generated method stub
		return repo.findAll(orderBy, Limit);
	}

	@Override
	public ArticleResult NewFirst() {
		// TODO Auto-generated method stub
		return repo.NewFirst();
	}

	@Override
	public List<ArticleResult> Teaser() {
		// TODO Auto-generated method stub
		return repo.Teaser();
	}

	@Override
	public long TotalPublished() {
		// TODO Auto-generated method stub
		return repo.TotalPublished();
	}

	@Override
	public Article findBy(Long id) {
		// TODO Auto-generated method stub
		Optional<Article> optional = repo.findById(id);
		Article model = null;
		if (optional.isPresent()) {
			model = optional.get();
		} else {
			return null;
		}
		return model;
	}

	@Override
	public ArticleResult findBySlug(String slug) {
		// TODO Auto-generated method stub
		return repo.findBySlug(slug);
	}

	
}
