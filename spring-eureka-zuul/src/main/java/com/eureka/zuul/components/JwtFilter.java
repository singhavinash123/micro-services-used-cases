package com.eureka.zuul.components;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eureka.zuul.constants.SecurityConstants;
import com.eureka.zuul.services.CustomUserDetailsService;
import com.eureka.zuul.utils.JwtUtil;


@Component
public class JwtFilter extends OncePerRequestFilter{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	CustomUserDetailsService service;


	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		String token = null;
		String userName = null;

		try {
		String authorizationHeader = httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
		if(authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.BEARER_PREFIX)) {
			token = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(token);
			logger.info("username =>"+userName);
		}

		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = service.loadUserByUsername(userName);
			if(Boolean.TRUE.equals(jwtUtil.validateToken(token, userDetails))) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 	new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
		}catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
}
