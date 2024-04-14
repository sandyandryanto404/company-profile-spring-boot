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

import java.util.List;

public interface ReferenceService {
    Long TotalRows();
    Reference saveOrUpdate(Reference model);
    List<Reference> getRandom(int type, int limit);
}
