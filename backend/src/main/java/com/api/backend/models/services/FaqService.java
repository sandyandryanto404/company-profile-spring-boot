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

import java.util.List;

import com.api.backend.models.entities.Faq;

public interface FaqService {
	
	Long TotalRows();

	Faq saveOrUpdate(Faq model);
	
	List<Faq> findAll1();
	
	List<Faq> findAll2();
}
