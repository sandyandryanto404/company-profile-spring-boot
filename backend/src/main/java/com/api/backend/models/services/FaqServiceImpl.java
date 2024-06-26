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

import com.api.backend.models.entities.Faq;
import com.api.backend.models.repositories.FaqRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqRepository repo;

    @Override
    public Long TotalRows() {
        return this.repo.count();
    }

    @Override
    public Faq saveOrUpdate(Faq model) {
        repo.save(model);
        return model;
    }

	@Override
	public List<Faq> findAll1() {
		// TODO Auto-generated method stub
		return repo.findAll1();
	}

	@Override
	public List<Faq> findAll2() {
		// TODO Auto-generated method stub
		return repo.findAll2();
	}

	
}
