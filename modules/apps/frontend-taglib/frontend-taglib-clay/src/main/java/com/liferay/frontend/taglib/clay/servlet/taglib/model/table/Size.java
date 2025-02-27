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

package com.liferay.frontend.taglib.clay.servlet.taglib.model.table;

/**
 * @author     Iván Zaera Avellón
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 */
@Deprecated
public enum Size {

	EXTRA_LARGE("xl"), LARGE("lg"), MEDIUM("md"), SMALL("sm");

	public String getValue() {
		return _value;
	}

	private Size(String value) {
		_value = value;
	}

	private final String _value;

}