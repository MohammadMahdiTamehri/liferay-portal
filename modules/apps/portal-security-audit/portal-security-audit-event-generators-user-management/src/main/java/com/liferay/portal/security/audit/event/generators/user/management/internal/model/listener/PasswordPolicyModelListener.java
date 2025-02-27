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

package com.liferay.portal.security.audit.event.generators.user.management.internal.model.listener;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.security.audit.event.generators.constants.EventTypes;
import com.liferay.portal.security.audit.event.generators.util.AuditMessageBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yousef Ghadiri
 */
@Component(immediate = true, service = ModelListener.class)
public class PasswordPolicyModelListener
	extends BaseModelListener<PasswordPolicy> {

	@Override
	public void onAfterCreate(PasswordPolicy passwordPolicy)
		throws ModelListenerException {

		audit(EventTypes.ADD, passwordPolicy);
	}

	@Override
	public void onAfterRemove(PasswordPolicy passwordPolicy)
		throws ModelListenerException {

		audit(EventTypes.DELETE, passwordPolicy);
	}

	@Override
	public void onAfterUpdate(PasswordPolicy passwordPolicy)
		throws ModelListenerException {

		audit(EventTypes.UPDATE, passwordPolicy);
	}

	protected void audit(String eventType, PasswordPolicy passwordPolicy)
		throws ModelListenerException {

		try {
			long passwordPolicyId = passwordPolicy.getPasswordPolicyId();

			AuditMessage auditMessage = AuditMessageBuilder.buildAuditMessage(
				eventType, PasswordPolicy.class.getName(), passwordPolicyId,
				null);

			JSONObject additionalInfoJSONObject =
				auditMessage.getAdditionalInfo();

			additionalInfoJSONObject.put(
				"passwordPolicyId", passwordPolicyId
			).put(
				"passwordPolicyName", passwordPolicy.getName()
			);

			_auditRouter.route(auditMessage);
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	@Reference
	private AuditRouter _auditRouter;

}