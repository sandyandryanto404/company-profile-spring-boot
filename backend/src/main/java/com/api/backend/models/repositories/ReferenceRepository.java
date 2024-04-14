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

import com.api.backend.models.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {

    @Query(value = "select count(*) from public.references x where x.id <> 0", nativeQuery = true)
    long count();

    @Query(value = "select * from public.references WHERE type =?1 order by random() limit ?2", nativeQuery = true)
    List<Reference> getRandom(int type, int limit);
}
