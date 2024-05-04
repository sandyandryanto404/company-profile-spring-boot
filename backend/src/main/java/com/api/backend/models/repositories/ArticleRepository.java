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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select count(*) from public.articles x where x.id <> 0", nativeQuery = true)
    long count();
    
    @Query(value = "select x.* from public.articles x where x.status = 1 order by ?1 LIMIT ?2", nativeQuery = true)
    List<Article> findAll(String orderBy,  int Limit);
}
