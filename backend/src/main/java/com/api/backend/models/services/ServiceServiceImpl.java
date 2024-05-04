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

import com.api.backend.models.repositories.ServiceRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRepository repo;

    @Override
    public Long TotalRows() {
        return this.repo.count();
    }

    @Override
    public com.api.backend.models.entities.Service saveOrUpdate(com.api.backend.models.entities.Service model) {
        repo.save(model);
        return model;
    }

	@Override
	public List<com.api.backend.models.entities.Service> findAll(String orderBy, int Limit) {
		// TODO Auto-generated method stub
		return repo.findAll(orderBy,  Limit);
	}
}
