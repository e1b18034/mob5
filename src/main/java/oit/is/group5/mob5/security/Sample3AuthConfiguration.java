package oit.is.group5.mob5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Sample3AuthConfiguration
 */
@Configuration
@EnableWebSecurity
public class Sample3AuthConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * 誰がログインできるか(認証処理)
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // pAssw0rd
    auth.inMemoryAuthentication().withUser("customer")
        .password("$2y$10$9ksX.rSLfmBFT.EsNFE/ZOr5JtaMJ8dvGIaImpB0kuH12Pg.wa81G").roles("CUSTOMER");
    auth.inMemoryAuthentication().withUser("seller")
        .password("$2y$10$9ksX.rSLfmBFT.EsNFE/ZOr5JtaMJ8dvGIaImpB0kuH12Pg.wa81G").roles("SELLER");
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 認証されたユーザがどこにアクセスできるか（認可処理）
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Spring Securityのフォームを利用してログインを行う
    http.formLogin();

    http.authorizeRequests().antMatchers("/sample5/**").authenticated();

    http.csrf().disable();
    http.headers().frameOptions().disable();

    // Spring Securityの機能を利用してログアウト．ログアウト時は http://localhost:8000/ に戻る
    http.logout().logoutSuccessUrl("/");
  }

}
