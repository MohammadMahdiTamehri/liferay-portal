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

package com.liferay.headless.commerce.admin.site.setting.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@GraphQLName("Warehouse")
@JsonFilter("Liferay.Vulcan")
@Schema(requiredProperties = {"commerceCountryId", "name"})
@XmlRootElement(name = "Warehouse")
public class Warehouse implements Serializable {

	public static Warehouse toDTO(String json) {
		return ObjectMapperUtil.readValue(Warehouse.class, json);
	}

	@Schema
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@JsonIgnore
	public void setActive(
		UnsafeSupplier<Boolean, Exception> activeUnsafeSupplier) {

		try {
			active = activeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean active;

	@Schema
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@JsonIgnore
	public void setCity(UnsafeSupplier<String, Exception> cityUnsafeSupplier) {
		try {
			city = cityUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String city;

	@DecimalMin("0")
	@Schema
	public Long getCommerceCountryId() {
		return commerceCountryId;
	}

	public void setCommerceCountryId(Long commerceCountryId) {
		this.commerceCountryId = commerceCountryId;
	}

	@JsonIgnore
	public void setCommerceCountryId(
		UnsafeSupplier<Long, Exception> commerceCountryIdUnsafeSupplier) {

		try {
			commerceCountryId = commerceCountryIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotNull
	protected Long commerceCountryId;

	@DecimalMin("0")
	@Schema
	public Long getCommerceRegionId() {
		return commerceRegionId;
	}

	public void setCommerceRegionId(Long commerceRegionId) {
		this.commerceRegionId = commerceRegionId;
	}

	@JsonIgnore
	public void setCommerceRegionId(
		UnsafeSupplier<Long, Exception> commerceRegionIdUnsafeSupplier) {

		try {
			commerceRegionId = commerceRegionIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long commerceRegionId;

	@Schema
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public void setDescription(
		UnsafeSupplier<String, Exception> descriptionUnsafeSupplier) {

		try {
			description = descriptionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String description;

	@DecimalMin("0")
	@Schema
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@JsonIgnore
	public void setGroupId(
		UnsafeSupplier<Long, Exception> groupIdUnsafeSupplier) {

		try {
			groupId = groupIdUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long groupId;

	@DecimalMin("0")
	@Schema
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long id;

	@Schema
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@JsonIgnore
	public void setLatitude(
		UnsafeSupplier<Double, Exception> latitudeUnsafeSupplier) {

		try {
			latitude = latitudeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Double latitude;

	@Schema
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@JsonIgnore
	public void setLongitude(
		UnsafeSupplier<Double, Exception> longitudeUnsafeSupplier) {

		try {
			longitude = longitudeUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Double longitude;

	@Schema
	@Valid
	public Number getMvccVersion() {
		return mvccVersion;
	}

	public void setMvccVersion(Number mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@JsonIgnore
	public void setMvccVersion(
		UnsafeSupplier<Number, Exception> mvccVersionUnsafeSupplier) {

		try {
			mvccVersion = mvccVersionUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Number mvccVersion;

	@Schema
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		try {
			name = nameUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	@NotEmpty
	protected String name;

	@Schema
	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	@JsonIgnore
	public void setPrimary(
		UnsafeSupplier<Boolean, Exception> primaryUnsafeSupplier) {

		try {
			primary = primaryUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean primary;

	@Schema
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	@JsonIgnore
	public void setStreet1(
		UnsafeSupplier<String, Exception> street1UnsafeSupplier) {

		try {
			street1 = street1UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String street1;

	@Schema
	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	@JsonIgnore
	public void setStreet2(
		UnsafeSupplier<String, Exception> street2UnsafeSupplier) {

		try {
			street2 = street2UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String street2;

	@Schema
	public String getStreet3() {
		return street3;
	}

	public void setStreet3(String street3) {
		this.street3 = street3;
	}

	@JsonIgnore
	public void setStreet3(
		UnsafeSupplier<String, Exception> street3UnsafeSupplier) {

		try {
			street3 = street3UnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String street3;

	@Schema
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@JsonIgnore
	public void setZip(UnsafeSupplier<String, Exception> zipUnsafeSupplier) {
		try {
			zip = zipUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String zip;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Warehouse)) {
			return false;
		}

		Warehouse warehouse = (Warehouse)object;

		return Objects.equals(toString(), warehouse.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (active != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"active\": ");

			sb.append(active);
		}

		if (city != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"city\": ");

			sb.append("\"");

			sb.append(_escape(city));

			sb.append("\"");
		}

		if (commerceCountryId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"commerceCountryId\": ");

			sb.append(commerceCountryId);
		}

		if (commerceRegionId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"commerceRegionId\": ");

			sb.append(commerceRegionId);
		}

		if (description != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(description));

			sb.append("\"");
		}

		if (groupId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"groupId\": ");

			sb.append(groupId);
		}

		if (id != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(id);
		}

		if (latitude != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"latitude\": ");

			sb.append(latitude);
		}

		if (longitude != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"longitude\": ");

			sb.append(longitude);
		}

		if (mvccVersion != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"mvccVersion\": ");

			sb.append(mvccVersion);
		}

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(name));

			sb.append("\"");
		}

		if (primary != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"primary\": ");

			sb.append(primary);
		}

		if (street1 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"street1\": ");

			sb.append("\"");

			sb.append(_escape(street1));

			sb.append("\"");
		}

		if (street2 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"street2\": ");

			sb.append("\"");

			sb.append(_escape(street2));

			sb.append("\"");
		}

		if (street3 != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"street3\": ");

			sb.append("\"");

			sb.append(_escape(street3));

			sb.append("\"");
		}

		if (zip != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"zip\": ");

			sb.append("\"");

			sb.append(_escape(zip));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Warehouse",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}