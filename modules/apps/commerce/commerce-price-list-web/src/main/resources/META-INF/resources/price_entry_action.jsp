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
CommercePriceEntryDisplayContext commercePriceEntryDisplayContext = (CommercePriceEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommercePriceEntry commercePriceEntry = null;

if (row != null) {
	commercePriceEntry = (CommercePriceEntry)row.getObject();
}
else {
	commercePriceEntry = (CommercePriceEntry)request.getAttribute("info_panel.jsp-entry");
}
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commercePriceEntryDisplayContext.hasPermission(commercePriceEntry.getCommercePriceListId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="/commerce_price_list/edit_commerce_price_entry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePriceEntryId" value="<%= String.valueOf(commercePriceEntry.getCommercePriceEntryId()) %>" />
			<portlet:param name="commercePriceListId" value="<%= String.valueOf(commercePriceEntry.getCommercePriceListId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:actionURL name="/commerce_price_list/edit_commerce_price_entry" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePriceEntryId" value="<%= String.valueOf(commercePriceEntry.getCommercePriceEntryId()) %>" />
			<portlet:param name="commercePriceListId" value="<%= String.valueOf(commercePriceEntry.getCommercePriceListId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>