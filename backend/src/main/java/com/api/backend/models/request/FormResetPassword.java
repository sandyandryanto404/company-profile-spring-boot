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

public class FormResetPassword {
	
	private String Email;
	private String Password;
	private String PasswordConfirm;
	
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
	
	public String getPasswordConfirm() {
		return PasswordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		PasswordConfirm = passwordConfirm;
	}

}
