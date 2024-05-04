# Company Profile Website

<p>
   This profile website serves as a communication medium between a company and external parties such as clients, readers, or other users. 
</p>
<p>
	This website is a specialized digital platform that serves the purpose of introducing a company to its visitors or readers. 
</p>
<p>
	This kind of directory aims to provide specific information about the company's brand, such as its vision, mission, values, products or services, and even historical background.
</p> 

# Preview

<img src="screenshots/home.png">


# Features

<ol type="1">
	<li>
		Authentication
		<ol type="1">
			<li>Login</li>
			<li>Register</li>
			<li>Forgot Password</li>
			<li>Reset Password</li>
		</ol>
	</li>
	<li>
		Account Management
		<ol type="1">
			<li>Change Password</li>
			<li>Manage Profile</li>
		</ol>
	</li>
	<li>
		General Page
		<ol type="1">
			<li>Home</li>
			<li>About</li>
			<li>Service</li>
			<li>Portfolio</li>
			<li>Article</li>
			<li>FAQ</li>
			<li>Contact</li>
		</ol>
	</li>
</ol>

# Technologies Used

<ol type="1">
	<li>Eclipse 4.31.0</li>
	<li>Visual Studio Code</li>
	<li>Modern Web Browser</li>
	<li>Git 2.4</li>
	<li>
		Backend Technologies
		<ol type="1">
			<li>Postgre SQL 13</li>
			<li>Java 21</li>
			<li>Spring Boot 3.2.4</li>
			<li>Maven 3.5.4</li>
		</ol>
	</li>
	<li>
		Frontend Technologies
		<ol type="1">
			<li>CSS3</li>
			<li>HTML5</li>
			<li>Bootstrap 5</li>
			<li>Node JS 20</li>
			<li>Angular JS 17</li>
		</ol>
	</li>
</ol>

## Getting Started
#### 1. Clone the repository and navigate to the directory
```shell
git clone https://github.com/sandyandryanto404/company-profile-spring-boot.git
cd company-profile-spring-boot
```

#### 2. Install backend dependencies, please move to directory company-profile-spring-boot/backend
```shell
mvn dependency:resolve
```

#### 3. Make a .env.properties.example file and customize its settings 
```shell
APP_PORT=8000
DB_HOST=localhost
DB_PORT=5432
DB_NAME={database-name}
DB_USERNAME={database-username}
DB_PASSWORD={database-password}
JWT_SECRET=secret
DDL_AUTO=update
```

#### 4. Start PostgreSQL Service , Seed data and Running REST API
```shell
sudo service postgresql start
createdb {database-name}
mvn spring-boot:run
```

#### 5. Install frontend dependencies, please move to directory company-profile-spring-boot/frontend
```shell
npm install
```

#### 6. Make a environment.development.ts file in company-profile-spring-boot/frontend/src/environments and customize its settings 
```shell
export const environment = {
    production: false,
    title: 'My Website',
    backendURL: 'http://localhost:8000'
};

```

#### 7. Run Application 
```shell
cd frontend
npm start
```

#### 8. Access application by entering [https://localhost:4200](https://localhost:4200) in the browser.

<br/>
<img src="screenshots/article.png">
</br>
<img src="screenshots/service.png">

#### 9. Developer Contact
<ul>
	<li>
		<strong>Linked In</strong> <a target="_blank" href="https://www.linkedin.com/in/sand404/">https://www.linkedin.com/in/sand404/</a>
	</li>
	<li>
		<strong>Facebook</strong> <a target="_blank" href="https://www.facebook.com/sandyandryantz">https://www.facebook.com/sandyandryantz</a>
	</li>
	<li>
		<strong>Instagram</strong> <a target="_blank" href="https://www.instagram.com/sandyandryanto/">https://www.instagram.com/sandyandryanto/</a>
	</li>
	<li>
		<strong>Telegram</strong> <a target="_blank" href="https://t.me/sand404">https://t.me/sand404</a>
	</li>
	<li>
		<strong>Gmail</strong> <a  href="mailto:sandy.andryanto404@gmail.com">sandy.andryanto404@gmail.com</a>
	</li>
</ul>