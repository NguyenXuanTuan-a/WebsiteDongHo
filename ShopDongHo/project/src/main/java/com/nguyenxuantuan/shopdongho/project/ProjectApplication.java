package com.nguyenxuantuan.shopdongho.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nguyenxuantuan.shopdongho.project.service.UserServiceImpl;

@SpringBootApplication(scanBasePackages = { "com.nguyenxuantuan.shopdongho.project" })
public class ProjectApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().antMatchers("/user/**")
				.hasAnyRole("USER").antMatchers("/admin/**").hasAnyRole("ADMIN").and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/member", true).failureUrl("/login?e=error").permitAll().and().logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/login?e=deny");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/css/**", "/assets/js/**", "/images/**","/assets/fonts/**","/assets/img/**","/assets/weather/**",
				"/assets/calendar/**","/user/css/**","/user/**","/user/js/**"); // để các đường dẫn trong static js hay css sẽ không
																	// bị chặn .spring Security sẽ không chặn đường dẫn
																	// /css/
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
	}
}
