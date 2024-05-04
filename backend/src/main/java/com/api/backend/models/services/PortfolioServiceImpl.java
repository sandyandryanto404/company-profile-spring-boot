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

import com.api.backend.models.entities.Portfolio;
import com.api.backend.models.repositories.PortfolioRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepository repo;

    @Override
    public Long TotalRows() {
        return this.repo.count();
    }

    @Override
    public Portfolio saveOrUpdate(Portfolio model) {
        repo.save(model);
        return model;
    }

	@Override
	public List<Portfolio> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Portfolio findBy(Long id) {
		// TODO Auto-generated method stub
		Optional<Portfolio> optional  = repo.findById(id);
		Portfolio model = null;
		if(optional.isPresent()) {
			model = optional.get();
		}else {
			return null;
		}
		return model;
	}
}
