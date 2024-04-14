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

import com.api.backend.models.entities.Customer;
import com.api.backend.models.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Override
    public Long TotalRows() {
        return this.repo.count();
    }

    @Override
    public Customer saveOrUpdate(Customer model) {
        repo.save(model);
        return model;
    }

    @Override
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Customer> getRandom(int limit) {
        return repo.getRandom(limit);
    }
}
