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


import com.api.backend.models.entities.Faq;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    @Query(value = "select count(*) from public.faqs x where x.id <> 0", nativeQuery = true)
    long count();
    
    @Query(value = "select x.* from public.faqs x where ?1 order by ?2 LIMIT ?3", nativeQuery = true)
    List<Faq> findAll(String where, String orderBy,  int Limit);
}
