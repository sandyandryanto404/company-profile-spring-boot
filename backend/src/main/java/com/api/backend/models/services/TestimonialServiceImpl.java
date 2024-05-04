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

import com.api.backend.models.entities.Testimonial;
import com.api.backend.models.repositories.TestimonialRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository repo;

    @Override
    public Long TotalRows() {
        return this.repo.count();
    }

    @Override
    public Testimonial saveOrUpdate(Testimonial model) {
        repo.save(model);
        return model;
    }

	@Override
	public List<Testimonial> findAll(String orderBy, int Limit) {
		// TODO Auto-generated method stub
		return repo.findAll(orderBy,  Limit);
	}
}
