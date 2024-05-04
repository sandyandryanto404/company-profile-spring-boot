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

import com.api.backend.models.entities.User;

import java.util.List;

public interface UserService {
    Long TotalRows();
    User saveOrUpdate(User model);
    List<User> getAll();
    List<User> getRandomNot(int limit, long user_id);
    User findByEmail(String email);
}
