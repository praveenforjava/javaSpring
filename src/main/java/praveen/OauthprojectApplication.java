package praveen;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class OauthprojectApplication extends WebSecurityConfigurerAdapter {


	@RequestMapping("/user")
	public Principal user( Principal principal){
		return principal;////principal is the user who is  logged in in the system. it is from securty classes java
		
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthprojectApplication.class, args);
	}
	@Override
	protected  void configure(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception{
		http
		
		       .antMatcher("/**")
		       .authorizeRequests()
		       .antMatchers("/","/login**","/webjars/**")
		       .permitAll()
		       .anyRequest()
		       .authenticated().and().logout()
		       .logoutSuccessUrl("/")
		       .permitAll()
		       .and()
		       .csrf()
		       .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
}
