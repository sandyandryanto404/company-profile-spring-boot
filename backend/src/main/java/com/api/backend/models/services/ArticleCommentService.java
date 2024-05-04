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

import com.api.backend.models.entities.ArticleComment;
import com.api.backend.models.request.ArticleCommentResult;
import com.api.backend.models.request.ArticleCommentTree;

public interface ArticleCommentService {
	
	ArticleComment saveOrUpdate(ArticleComment model);
	
	List<ArticleCommentResult> findAll(long ArticleId);
	
	List<ArticleCommentResult> findByParent(long ArticleId, long ParentId);
	
	List<ArticleCommentTree> BuildTree(List<ArticleCommentResult> elements, long ParentId);
	
}
