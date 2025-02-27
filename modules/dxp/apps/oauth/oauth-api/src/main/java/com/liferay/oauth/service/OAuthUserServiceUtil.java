/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.oauth.service;

import com.liferay.oauth.model.OAuthUser;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for OAuthUser. This utility wraps
 * <code>com.liferay.oauth.service.impl.OAuthUserServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ivica Cardic
 * @see OAuthUserService
 * @generated
 */
public class OAuthUserServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.oauth.service.impl.OAuthUserServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static OAuthUser addOAuthUser(
			String consumerKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addOAuthUser(consumerKey, serviceContext);
	}

	public static OAuthUser deleteOAuthUser(long oAuthApplicationId)
		throws PortalException {

		return getService().deleteOAuthUser(oAuthApplicationId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static OAuthUserService getService() {
		return _service;
	}

	private static volatile OAuthUserService _service;

}