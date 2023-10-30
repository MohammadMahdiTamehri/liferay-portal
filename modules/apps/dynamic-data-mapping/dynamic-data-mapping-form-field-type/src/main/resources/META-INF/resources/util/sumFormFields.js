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

// const sumFormFieldsValues = (portletNamespace) =>{

export function sumFormFieldsValues(portletNamespace) {
	try {
		if (!portletNamespace.includes('Admin')) {
			let showSumOfPayment = false;
			const elements = document.getElementById(portletNamespace + 'fm')
				.elements;
			const elements2 = document.getElementById(portletNamespace + 'fm')
				.elements;
			let sum = 0;
			for (let i = 0; i < elements.length; i++) {
				const dataPriceField = elements[i].getAttribute(
					'data-price-field'
				);
				const elementType = elements[i].getAttribute('type');
				if (elementType === 'radio') {
					for (let j = 0; j < elements2.length; j++) {
						const jElementType = elements2[j].getAttribute('type');
						const jElementValue = elements2[j].getAttribute(
							'value'
						);
						if (
							jElementType === 'hidden' &&
							jElementValue === elements[i].value &&
							elements[i].getAttribute('data-price-value') !== ''
						) {
							sum += parseInt(
								!isNaN(
									elements[i].getAttribute('data-price-value')
								)
									? elements[i].getAttribute(
											'data-price-value'
									  )
									: '0',
								10
							);
							showSumOfPayment = true;
						}
					}
				}
				if (
					elementType === 'text' &&
					dataPriceField === 'price-field' &&
					elements[i].getAttribute('value') !== ''
				) {
					sum += parseInt(
						!isNaN(elements[i].getAttribute('value'))
							? elements[i].getAttribute('value')
							: '0',
						10
					);
					showSumOfPayment = true;
				}
				if (
					elementType === 'hidden' &&
					dataPriceField === 'price-field' &&
					elements[i].getAttribute('data-price-value') !== ''
				) {
					sum += parseInt(
						!isNaN(elements[i].getAttribute('data-price-value'))
							? elements[i].getAttribute('data-price-value')
							: '0',
						10
					);
					showSumOfPayment = true;
				}
			}
			if (showSumOfPayment) {
				document.getElementById(
					portletNamespace + 'sumOfFields'
				).innerHTML =
					Liferay.Language.get('sum-of-payment') + ': ' + sum;
			}
		}
	}
	catch (e) {}
}
