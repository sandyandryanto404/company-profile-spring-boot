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

import com.api.backend.models.entities.Contact;
import com.api.backend.models.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements  ContactService {

    @Autowired
    private ContactRepository repo;

    @Override
    public Long TotalRows() {
        return this.repo.count();
    }

    @Override
    public Contact saveOrUpdate(Contact model) {
        repo.save(model);
        return model;
    }
}
