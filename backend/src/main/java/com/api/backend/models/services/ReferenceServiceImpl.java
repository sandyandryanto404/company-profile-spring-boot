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

import com.api.backend.models.entities.Reference;
import com.api.backend.models.repositories.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    private ReferenceRepository repo;

    @Override
    public Long TotalRows() {
        return this.repo.count();
    }

    @Override
    public Reference saveOrUpdate(Reference model) {
        repo.save(model);
        return model;
    }

    @Override
    public List<Reference> getRandom(int type, int limit) {
        return this.repo.getRandom(type, limit);
    }
}
