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

import com.api.backend.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select count(*) from public.users x where x.id <> 0", nativeQuery = true)
    long count();

    @Query(value = "select * from public.users order by random() limit ?1", nativeQuery = true)
    List<User> getRandom(int limit);

    @Query(value = "select * from public.users WHERE id != ?2 order by random() limit ?1", nativeQuery = true)
    List<User> getRandomNot(int limit, long user_id);
    
    @Query(value = "select * from public.users x where x.email = ?1  limit 1", nativeQuery = true)
	User findByEmail(String email);
}
