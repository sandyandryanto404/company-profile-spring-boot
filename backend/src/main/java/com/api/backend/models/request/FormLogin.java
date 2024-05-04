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

import java.io.Serializable;

public class FormLogin implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;

	private String Email;
	private String Password;
	private Boolean RememberMe;
	
	public FormLogin() {}
	
	public FormLogin(String email, String password, Boolean remember) {
        this.setEmail(email);
        this.setPassword(password);
		this.setRememberMe(remember);
    }

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Boolean getRememberMe() {
		return RememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		RememberMe = rememberMe;
	}

}
