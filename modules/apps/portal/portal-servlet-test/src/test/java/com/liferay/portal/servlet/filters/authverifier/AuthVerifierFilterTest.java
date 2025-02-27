/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.servlet.filters.authverifier;

import com.liferay.portal.kernel.security.access.control.AccessControl;
import com.liferay.portal.kernel.security.access.control.AccessControlUtil;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.security.access.control.AccessControlImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PortalImpl;
import com.liferay.portal.util.PropsValues;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Eric Yan
 */
public class AuthVerifierFilterTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() {
		_portalUtil.setPortal(_portalImpl);

		Registry registry = new BasicRegistryImpl();

		RegistryUtil.setRegistry(registry);

		registry.registerService(
			AccessControl.class, new TestAccessControlImpl());
	}

	@After
	public void tearDown() {
		AccessControlUtil.setAccessControlContext(null);
	}

	@Test
	public void testHttpsRequiredDisabled() {
		_mockFilterConfig.addInitParameter("https.required", "false");

		_processFilter();

		String redirectURL = _mockHttpServletResponse.getRedirectedUrl();

		Assert.assertNull(redirectURL);
	}

	@Test
	public void testHttpsRequiredWithHttpRequest() {
		_mockFilterConfig.addInitParameter("https.required", "true");

		_processFilter();

		String redirectURL = _mockHttpServletResponse.getRedirectedUrl();

		String expectedRedirectURL = "https://localhost";

		Assert.assertEquals(expectedRedirectURL, redirectURL);
	}

	@Test
	public void testHttpsRequiredWithHttpRequestAndProxyPath() {
		String portalProxyPath = PropsValues.PORTAL_PROXY_PATH;

		try {
			_setPortalProperty("PORTAL_PROXY_PATH", "/liferay123");

			_portalUtil.setPortal(new PortalImpl());

			_mockFilterConfig.addInitParameter("https.required", "true");

			_processFilter();
		}
		finally {
			_portalUtil.setPortal(_portalImpl);
			_setPortalProperty("PORTAL_PROXY_PATH", portalProxyPath);
		}

		String redirectURL = _mockHttpServletResponse.getRedirectedUrl();

		String expectedRedirectURL = "https://localhost/liferay123";

		Assert.assertEquals(expectedRedirectURL, redirectURL);
	}

	@Test
	public void testHttpsRequiredWithHttpRequestAndProxyPathAndRequestURI() {
		String portalProxyPath = PropsValues.PORTAL_PROXY_PATH;

		try {
			_setPortalProperty("PORTAL_PROXY_PATH", "/liferay123");

			_portalUtil.setPortal(new PortalImpl());

			_mockHttpServletRequest.setQueryString("a=1");
			_mockHttpServletRequest.setRequestURI("/abc123");

			_mockFilterConfig.addInitParameter("https.required", "true");

			_processFilter();
		}
		finally {
			_portalUtil.setPortal(_portalImpl);
			_setPortalProperty("PORTAL_PROXY_PATH", portalProxyPath);
		}

		String redirectURL = _mockHttpServletResponse.getRedirectedUrl();

		String expectedRedirectURL = "https://localhost/liferay123/abc123?a=1";

		Assert.assertEquals(expectedRedirectURL, redirectURL);
	}

	@Test
	public void testHttpsRequiredWithHttpRequestAndXForwardedHostAndXForwardedPort() {
		boolean webServerForwardedHostEnabled =
			PropsValues.WEB_SERVER_FORWARDED_HOST_ENABLED;
		boolean webServerForwardedPortEnabled =
			PropsValues.WEB_SERVER_FORWARDED_PORT_ENABLED;

		try {
			_setPortalProperty(
				"WEB_SERVER_FORWARDED_HOST_ENABLED", Boolean.TRUE);
			_setPortalProperty(
				"WEB_SERVER_FORWARDED_PORT_ENABLED", Boolean.TRUE);

			_mockHttpServletRequest.addHeader(
				"X-Forwarded-Host", "test.liferay.com");
			_mockHttpServletRequest.addHeader("X-Forwarded-Port", "1234");

			_mockFilterConfig.addInitParameter("https.required", "true");

			_processFilter();
		}
		finally {
			_setPortalProperty(
				"WEB_SERVER_FORWARDED_HOST_ENABLED",
				webServerForwardedHostEnabled);
			_setPortalProperty(
				"WEB_SERVER_FORWARDED_PORT_ENABLED",
				webServerForwardedPortEnabled);
		}

		String redirectURL = _mockHttpServletResponse.getRedirectedUrl();

		String expectedRedirectURL = "https://test.liferay.com:1234";

		Assert.assertEquals(expectedRedirectURL, redirectURL);
	}

	@Test
	public void testHttpsRequiredWithHttpRequestAndXForwardedProto() {
		boolean webServerForwardedProtocolEnabled =
			PropsValues.WEB_SERVER_FORWARDED_PROTOCOL_ENABLED;

		try {
			_setPortalProperty(
				"WEB_SERVER_FORWARDED_PROTOCOL_ENABLED", Boolean.TRUE);

			_mockHttpServletRequest.addHeader("X-Forwarded-Proto", Http.HTTPS);

			_mockFilterConfig.addInitParameter("https.required", "true");

			_processFilter();
		}
		finally {
			_setPortalProperty(
				"WEB_SERVER_FORWARDED_PROTOCOL_ENABLED",
				webServerForwardedProtocolEnabled);
		}

		String redirectURL = _mockHttpServletResponse.getRedirectedUrl();

		Assert.assertNull(redirectURL);
	}

	@Test
	public void testHttpsRequiredWithHttpsRequest() {
		_mockHttpServletRequest.setScheme(Http.HTTPS);
		_mockHttpServletRequest.setSecure(true);

		_mockFilterConfig.addInitParameter("https.required", "true");

		_processFilter();

		String redirectURL = _mockHttpServletResponse.getRedirectedUrl();

		Assert.assertNull(redirectURL);
	}

	private void _processFilter() {
		_authVerifierFilter.init(_mockFilterConfig);

		ReflectionTestUtil.invoke(
			_authVerifierFilter, "processFilter",
			new Class<?>[] {
				HttpServletRequest.class, HttpServletResponse.class,
				FilterChain.class
			},
			_mockHttpServletRequest, _mockHttpServletResponse,
			_mockFilterChain);
	}

	private void _setPortalProperty(String propertyName, Object value) {
		ReflectionTestUtil.setFieldValue(
			PropsValues.class, propertyName, value);
	}

	private static final PortalImpl _portalImpl = new PortalImpl();
	private static final PortalUtil _portalUtil = new PortalUtil();

	private final AuthVerifierFilter _authVerifierFilter =
		new AuthVerifierFilter();
	private final MockFilterChain _mockFilterChain = new MockFilterChain();
	private final MockFilterConfig _mockFilterConfig = new MockFilterConfig();
	private final MockHttpServletRequest _mockHttpServletRequest =
		new MockHttpServletRequest();
	private final MockHttpServletResponse _mockHttpServletResponse =
		new MockHttpServletResponse();

	private static class TestAccessControlImpl extends AccessControlImpl {

		@Override
		public void initAccessControlContext(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			Map<String, Object> settings) {

			super.initAccessControlContext(
				httpServletRequest, httpServletResponse, settings);

			AccessControlContext accessControlContext =
				AccessControlUtil.getAccessControlContext();

			AuthVerifierResult authVerifierResult = new AuthVerifierResult();

			authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);

			accessControlContext.setAuthVerifierResult(authVerifierResult);
		}

		@Override
		public void initContextUser(long userId) {
		}

		@Override
		public AuthVerifierResult.State verifyRequest() {
			return AuthVerifierResult.State.SUCCESS;
		}

	}

}