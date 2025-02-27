<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String notificationNavigationItem = ParamUtil.getString(request, "notificationNavigationItem", "view-all-notification-queue-entries");

CommerceNotificationQueueEntriesDisplayContext commerceNotificationQueueEntriesDisplayContext = (CommerceNotificationQueueEntriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = commerceNotificationQueueEntriesDisplayContext.getPortletURL();

portletURL.setParameter("notificationNavigationItem", notificationNavigationItem);
%>

<clay:data-set-display
	contextParams='<%=
		HashMapBuilder.<String, String>put(
			"commerceChannelId", String.valueOf(commerceNotificationQueueEntriesDisplayContext.getCommerceChannelId())
		).build()
	%>'
	dataProviderKey="<%= CommerceNotificationEntryClayTable.NAME %>"
	id="<%= CommerceNotificationEntryClayTable.NAME %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= liferayPortletResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= portletURL %>"
	showManagementBar="<%= false %>"
/>