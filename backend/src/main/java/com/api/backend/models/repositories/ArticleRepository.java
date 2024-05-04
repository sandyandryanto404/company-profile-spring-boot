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

import com.api.backend.models.entities.Article;
import com.api.backend.models.request.ArticleResult;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select count(*) from public.articles x where x.id <> 0", nativeQuery = true)
    long count();
    
    @Query(value = "select a.id, a.title, a.content, a.created_at, a.slug, a.description, u.first_name, u.last_name, u.gender, u.about_me,( SELECT string_agg(r.name, ',') AS r FROM public.references r WHERE r.id IN ( SELECT reference_id FROM articles_references WHERE article_id = a.id) ) as categories from articles a inner join users u on u.id = a.user_id where a.slug = ?1", nativeQuery = true)
    ArticleResult findBySlug(String slug);
    
    @Query(value = "select a.id, a.title, a.content, a.created_at, a.slug, a.description, u.first_name, u.last_name, u.gender, u.about_me,( SELECT string_agg(r.name, ',') AS r FROM public.references r WHERE r.id IN ( SELECT reference_id FROM articles_references WHERE article_id = a.id) ) as categories from articles a inner join users u on u.id = a.user_id where a.status = 1 order by ?1 limit ?2", nativeQuery = true)
    List<ArticleResult> findAll(String orderBy,  int Limit);
    
    @Query(value = "select a.id, a.title, a.content, a.created_at, a.slug, a.description, u.first_name, u.last_name, u.gender, u.about_me,( SELECT string_agg(r.name, ',') AS r FROM public.references r WHERE r.id IN ( SELECT reference_id FROM articles_references WHERE article_id = a.id) ) as categories from articles a inner join users u on u.id = a.user_id where a.status = 1 order by id desc limit 1", nativeQuery = true)
    ArticleResult NewFirst();
    
    @Query(value = "select a.id, a.title, a.content, a.created_at, a.slug, a.description, u.first_name, u.last_name, u.gender, u.about_me,( SELECT string_agg(r.name, ',') AS r FROM public.references r WHERE r.id IN ( SELECT reference_id FROM articles_references WHERE article_id = a.id) ) as categories from articles a inner join users u on u.id = a.user_id where a.status = 1 order by id desc limit 3 offset 1", nativeQuery = true)
    List<ArticleResult> Teaser();
    
    @Query(value = "select count(*) from public.articles x where x.status = 1", nativeQuery = true)
    long TotalPublished();
}
