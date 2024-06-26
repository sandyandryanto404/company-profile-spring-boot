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

import java.util.List;

import com.api.backend.models.entities.Article;
import com.api.backend.models.request.ArticleResult;

public interface ArticleService {
	
	Long TotalRows();

	Article saveOrUpdate(Article model);
	
	List<ArticleResult> findAll(String orderBy, int Limit);
	
	ArticleResult findBySlug(String slug);
	
	ArticleResult NewFirst();
	
	List<ArticleResult> Teaser();
	
	long TotalPublished();
	
	Article findBy(Long id);
}
