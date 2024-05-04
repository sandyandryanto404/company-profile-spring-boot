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

package com.api.backend.models.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public interface  ArticleCommentResult {

	public Long getId();
	
	@Value(value = "#{target.parent_id}")
	public Long getParentId();
	
	public String getComment();
	
	@Value(value = "#{target.created_at}")
	public Date getCreatedAt();
	
	@Value(value = "#{target.first_name}")
	public String getFirstName();
	
	@Value(value = "#{target.last_name}")
	public String getLastName();
	
	@Value(value = "#{target.gender}")
	public String getGender();
	
	@Value(value = "#{target.about_me}")
	public String getAboutMe();
	
}
