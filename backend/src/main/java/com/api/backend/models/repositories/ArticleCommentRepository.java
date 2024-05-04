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

package com.api.backend.models.repositories;

import com.api.backend.models.entities.ArticleComment;
import com.api.backend.models.request.ArticleCommentResult;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

	@Query(value = "select c.id, (case when parent_id is null then 0 else parent_id end) parent_id, c.comment, c.created_at, u.first_name, u.last_name, u.gender, u.about_me from articles_comments c inner join users u on u.id = c.user_id where c.article_id = ?1 order by c.id desc", nativeQuery = true)
	List<ArticleCommentResult> findAll(long ArticleId);

	@Query(value = "select c.id, (case when parent_id is null then 0 else parent_id end) parent_id, c.comment, c.created_at, u.first_name, u.last_name, u.gender, u.about_me from articles_comments c inner join users u on u.id = c.user_id where c.article_id = ?1 and c.parent_id = ?2 order by c.id desc", nativeQuery = true)
	List<ArticleCommentResult> findByParent(long ArticleId, long ParentId);

}
