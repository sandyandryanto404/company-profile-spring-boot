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
import com.api.backend.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public Long TotalRows() {
        return this.repo.count();
    }

    @Override
    public User saveOrUpdate(User model) {
        repo.save(model);
        return model;
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public List<User> getRandomNot(int limit, long user_id) {
        return repo.getRandomNot(limit, user_id);
    }

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}
}
