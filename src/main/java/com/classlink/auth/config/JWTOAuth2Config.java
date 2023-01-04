package com.classlink.auth.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class JWTOAuth2Config extends AuthorizationServerConfigurerAdapter {

	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    
    @Autowired
    private TokenEnhancer jwtTokenEnhancer;
    
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("classlink")
				//.secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("classlink!02$"))
				.secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("classlink!02$"))
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("client").refreshTokenValiditySeconds((24*60*60)*30).accessTokenValiditySeconds(24*60*60)
				//.scopes("client").refreshTokenValiditySeconds((24*60*60)*30).accessTokenValiditySeconds(10)
				.and()
				.withClient("appServer")
				//.secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("appServer"))
				.secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("appServer"))
				.authorizedGrantTypes("client_credentials")
				.scopes("server").accessTokenValiditySeconds(0);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter));

        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
	}
}
