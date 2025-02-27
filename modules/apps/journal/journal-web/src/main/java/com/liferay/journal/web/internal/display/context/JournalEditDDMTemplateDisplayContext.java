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

package com.liferay.journal.web.internal.display.context;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMTemplateHelper;
import com.liferay.journal.configuration.JournalFileUploadsConfiguration;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.web.internal.configuration.JournalWebConfiguration;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.resource.bundle.ResourceBundleLoader;
import com.liferay.portal.kernel.resource.bundle.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.template.TemplateContextHelper;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Eudaldo Alonso
 */
public class JournalEditDDMTemplateDisplayContext {

	public JournalEditDDMTemplateDisplayContext(
		HttpServletRequest httpServletRequest) {

		_httpServletRequest = httpServletRequest;

		_ddmTemplateHelper =
			(DDMTemplateHelper)_httpServletRequest.getAttribute(
				DDMTemplateHelper.class.getName());

		_journalFileUploadsConfiguration =
			(JournalFileUploadsConfiguration)_httpServletRequest.getAttribute(
				JournalFileUploadsConfiguration.class.getName());

		_journalWebConfiguration =
			(JournalWebConfiguration)_httpServletRequest.getAttribute(
				JournalWebConfiguration.class.getName());
	}

	public boolean autogenerateDDMTemplateKey() {
		return _journalWebConfiguration.autogenerateDDMTemplateKey();
	}

	public String getAutocompleteJSON() throws Exception {
		return _ddmTemplateHelper.getAutocompleteJSON(
			_httpServletRequest, getLanguage());
	}

	public long getClassPK() {
		if (_classPK != null) {
			return _classPK;
		}

		_classPK = BeanParamUtil.getLong(
			getDDMTemplate(), _httpServletRequest, "classPK");

		return _classPK;
	}

	public DDMStructure getDDMStructure() {
		if (_ddmStructure != null) {
			return _ddmStructure;
		}

		_ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
			getClassPK());

		if (_ddmStructure != null) {
			return _ddmStructure;
		}

		DDMTemplate ddmTemplate = getDDMTemplate();

		if (ddmTemplate != null) {
			_ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
				ddmTemplate.getClassPK());
		}

		return _ddmStructure;
	}

	public DDMTemplate getDDMTemplate() {
		if (_ddmTemplate != null) {
			return _ddmTemplate;
		}

		_ddmTemplate = DDMTemplateLocalServiceUtil.fetchDDMTemplate(
			getDDMTemplateId());

		return _ddmTemplate;
	}

	public long getDDMTemplateId() {
		if (_ddmTemplateId != null) {
			return _ddmTemplateId;
		}

		_ddmTemplateId = ParamUtil.getLong(
			_httpServletRequest, "ddmTemplateId");

		return _ddmTemplateId;
	}

	public String getEditorMode() {
		if (Objects.equals(getLanguage(), "ftl")) {
			return "ftl";
		}

		if (Objects.equals(getLanguage(), "xsl")) {
			return "xml";
		}

		return "velocity";
	}

	public String[] getExtendedTemplateLanguageTypes() {
		DDMTemplate ddmTemplate = getDDMTemplate();

		String[] extendedTemplateLanguageTypes = getTemplateLanguageTypes();

		if ((ddmTemplate != null) &&
			!ArrayUtil.contains(
				extendedTemplateLanguageTypes, ddmTemplate.getLanguage())) {

			extendedTemplateLanguageTypes = ArrayUtil.append(
				extendedTemplateLanguageTypes, ddmTemplate.getLanguage());
		}

		return extendedTemplateLanguageTypes;
	}

	public long getGroupId() {
		if (_groupId != null) {
			return _groupId;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		_groupId = BeanParamUtil.getLong(
			getDDMTemplate(), _httpServletRequest, "groupId",
			themeDisplay.getScopeGroupId());

		return _groupId;
	}

	public String getLanguage() {
		if (_language != null) {
			return _language;
		}

		_language = BeanParamUtil.getString(
			getDDMTemplate(), _httpServletRequest, "language",
			TemplateConstants.LANG_TYPE_FTL);

		return _language;
	}

	public String getRedirect() {
		if (_redirect != null) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		return _redirect;
	}

	public String getScript() {
		if (_script != null) {
			return _script;
		}

		_language = BeanParamUtil.getString(
			getDDMTemplate(), _httpServletRequest, "language",
			TemplateConstants.LANG_TYPE_FTL);

		_script = BeanParamUtil.getString(
			getDDMTemplate(), _httpServletRequest, "script");

		if (Validator.isNull(_script)) {
			TemplateHandler templateHandler =
				TemplateHandlerRegistryUtil.getTemplateHandler(
					PortalUtil.getClassNameId(JournalArticle.class));

			_script = templateHandler.getTemplatesHelpContent(_language);
		}

		String scriptContent = ParamUtil.getString(
			_httpServletRequest, "scriptContent");

		if (Validator.isNotNull(scriptContent)) {
			_script = scriptContent;
		}

		return _script;
	}

	public String getSmallImageSource() {
		if (Validator.isNotNull(_smallImageSource)) {
			return _smallImageSource;
		}

		DDMTemplate ddmTemplate = getDDMTemplate();

		if (ddmTemplate == null) {
			_smallImageSource = "none";

			return _smallImageSource;
		}

		_smallImageSource = ParamUtil.getString(
			_httpServletRequest, "smallImageSource");

		if (Validator.isNotNull(_smallImageSource)) {
			return _smallImageSource;
		}

		if (!ddmTemplate.isSmallImage()) {
			_smallImageSource = "none";
		}
		else if (Validator.isNotNull(ddmTemplate.getSmallImageURL())) {
			_smallImageSource = "url";
		}
		else if ((ddmTemplate.getSmallImageId() > 0) &&
				 Validator.isNull(ddmTemplate.getSmallImageURL())) {

			_smallImageSource = "file";
		}

		return _smallImageSource;
	}

	public ResourceBundle getTemplateHandlerResourceBundle() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		TemplateHandler templateHandler =
			TemplateHandlerRegistryUtil.getTemplateHandler(
				PortalUtil.getClassNameId(JournalArticle.class));

		Class<?> clazz = getClass();

		if (templateHandler != null) {
			clazz = templateHandler.getClass();
		}

		Bundle bundle = FrameworkUtil.getBundle(clazz);

		ResourceBundleLoader resourceBundleLoader =
			ResourceBundleLoaderUtil.
				getResourceBundleLoaderByBundleSymbolicName(
					bundle.getSymbolicName());

		return resourceBundleLoader.loadResourceBundle(
			themeDisplay.getLocale());
	}

	public String getTemplateLanguageTypeLabel(String templateLanguageType) {
		StringBundler sb = new StringBundler(6);

		sb.append(
			LanguageUtil.get(
				_httpServletRequest, templateLanguageType + "[stands-for]"));
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(StringPool.PERIOD);
		sb.append(templateLanguageType);
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	public String[] getTemplateLanguageTypes() {
		return _journalWebConfiguration.journalDDMTemplateLanguageTypes();
	}

	public Collection<TemplateVariableGroup> getTemplateVariableGroups()
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Map<String, TemplateVariableGroup> templateVariableGroups =
			TemplateContextHelper.getTemplateVariableGroups(
				PortalUtil.getClassNameId(JournalArticle.class), getClassPK(),
				getLanguage(), themeDisplay.getLocale());

		return templateVariableGroups.values();
	}

	public String getTitle() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		DDMStructure ddmStructure = getDDMStructure();

		DDMTemplate ddmTemplate = getDDMTemplate();

		if ((ddmStructure != null) && (ddmTemplate != null)) {
			return StringUtil.appendParentheticalSuffix(
				ddmTemplate.getName(themeDisplay.getLocale()),
				ddmStructure.getName(themeDisplay.getLocale()));
		}

		if (ddmStructure != null) {
			return LanguageUtil.format(
				_httpServletRequest, "new-template-for-structure-x",
				ddmStructure.getName(themeDisplay.getLocale()), false);
		}

		if (ddmTemplate != null) {
			return ddmTemplate.getName(themeDisplay.getLocale());
		}

		return LanguageUtil.get(_httpServletRequest, "new-template");
	}

	public String[] imageExtensions() {
		return _journalFileUploadsConfiguration.imageExtensions();
	}

	public boolean isAutocompleteEnabled() {
		return _ddmTemplateHelper.isAutocompleteEnabled(getLanguage());
	}

	public boolean isCacheable() {
		if (_cacheable != null) {
			return _cacheable;
		}

		_cacheable = BeanParamUtil.getBoolean(
			getDDMTemplate(), _httpServletRequest, "cacheable", true);

		return _cacheable;
	}

	public boolean isShowSpecificLanguageType() {
		DDMTemplate ddmTemplate = getDDMTemplate();

		String[] templateLanguageTypes = getTemplateLanguageTypes();

		if ((templateLanguageTypes.length == 1) &&
			((ddmTemplate == null) ||
			 Objects.equals(
				 templateLanguageTypes[0], ddmTemplate.getLanguage()))) {

			return true;
		}

		return false;
	}

	public boolean isSmallImage() {
		if (_smallImage != null) {
			return _smallImage;
		}

		_smallImage = BeanParamUtil.getBoolean(
			getDDMTemplate(), _httpServletRequest, "smallImage");

		return _smallImage;
	}

	public void setLanguage(String language) {
		_language = language;
	}

	public long smallImageMaxSize() {
		return _journalFileUploadsConfiguration.smallImageMaxSize();
	}

	private Boolean _cacheable;
	private Long _classPK;
	private DDMStructure _ddmStructure;
	private DDMTemplate _ddmTemplate;
	private final DDMTemplateHelper _ddmTemplateHelper;
	private Long _ddmTemplateId;
	private Long _groupId;
	private final HttpServletRequest _httpServletRequest;
	private final JournalFileUploadsConfiguration
		_journalFileUploadsConfiguration;
	private final JournalWebConfiguration _journalWebConfiguration;
	private String _language;
	private String _redirect;
	private String _script;
	private Boolean _smallImage;
	private String _smallImageSource;

}