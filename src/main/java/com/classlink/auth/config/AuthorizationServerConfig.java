package com.classlink.auth.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.oauth2.jwt.signkey}")
	private String signKey;

	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userDetailsService;

	private final TokenStore tokenStore;

	private final JwtAccessTokenConverter jwtAccessTokenConverter;

	private final TokenEnhancer jwtTokenEnhancer;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("classlink")
				// .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("classlink!02$"))
				.secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("classlink!02$"))
				.authorizedGrantTypes("password", "refresh_token").scopes("client")
				.refreshTokenValiditySeconds((24 * 60 * 60) * 30).accessTokenValiditySeconds(24 * 60 * 60)
				// .scopes("client").refreshTokenValiditySeconds((24*60*60)*30).accessTokenValiditySeconds(10)
				.and().withClient("appServer")
				// .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("appServer"))
				.secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("appServer"))
				.authorizedGrantTypes("client_credentials").scopes("server").accessTokenValiditySeconds(0);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter));

		endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter).tokenEnhancer(tokenEnhancerChain)
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()") // allow check token
				.allowFormAuthenticationForClients();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		// more config
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
