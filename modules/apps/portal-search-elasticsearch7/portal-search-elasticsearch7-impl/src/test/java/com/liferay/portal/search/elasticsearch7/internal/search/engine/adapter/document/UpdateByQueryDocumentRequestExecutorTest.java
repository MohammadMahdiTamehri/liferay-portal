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

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.document;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch7.internal.query.ElasticsearchQueryTranslatorFixture;
import com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentRequest;
import com.liferay.portal.search.internal.script.ScriptsImpl;
import com.liferay.portal.search.script.Scripts;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PropsImpl;

import org.elasticsearch.index.reindex.UpdateByQueryRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Dylan Rebelak
 */
public class UpdateByQueryDocumentRequestExecutorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		PropsUtil.setProps(new PropsImpl());

		_elasticsearchFixture = new ElasticsearchFixture(
			UpdateByQueryDocumentRequestExecutorTest.class.getSimpleName());

		_elasticsearchFixture.setUp();
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Test
	public void testDocumentRequestTranslationWithNoRefresh() {
		doTestDocumentRequestTranslation(false);
	}

	@Test
	public void testDocumentRequestTranslationWithRefresh() {
		doTestDocumentRequestTranslation(true);
	}

	protected void doTestDocumentRequestTranslation(boolean refresh) {
		BooleanQuery booleanQuery = new BooleanQueryImpl();

		booleanQuery.addExactTerm(_FIELD_NAME, true);

		UpdateByQueryDocumentRequest updateByQueryDocumentRequest =
			new UpdateByQueryDocumentRequest(
				booleanQuery, null, new String[] {_INDEX_NAME});

		updateByQueryDocumentRequest.setRefresh(refresh);

		UpdateByQueryDocumentRequestExecutorImpl
			updateByQueryDocumentRequestExecutorImpl =
				new UpdateByQueryDocumentRequestExecutorImpl() {
					{
						setElasticsearchClientResolver(_elasticsearchFixture);

						com.liferay.portal.search.elasticsearch7.internal.
							legacy.query.ElasticsearchQueryTranslatorFixture
								lecacyElasticsearchQueryTranslatorFixture =
									new com.liferay.portal.search.
										elasticsearch7.internal.legacy.query.ElasticsearchQueryTranslatorFixture();

						setLegacyQueryTranslator(
							lecacyElasticsearchQueryTranslatorFixture.
								getElasticsearchQueryTranslator());

						ElasticsearchQueryTranslatorFixture
							elasticsearchQueryTranslatorFixture =
								new ElasticsearchQueryTranslatorFixture();

						setQueryTranslator(
							elasticsearchQueryTranslatorFixture.
								getElasticsearchQueryTranslator());

						setScripts(_scripts);
					}
				};

		UpdateByQueryRequest updateByQueryRequest =
			updateByQueryDocumentRequestExecutorImpl.createUpdateByQueryRequest(
				updateByQueryDocumentRequest);

		Assert.assertArrayEquals(
			new String[] {_INDEX_NAME}, updateByQueryRequest.indices());

		Assert.assertEquals(
			updateByQueryDocumentRequest.isRefresh(),
			updateByQueryRequest.isRefresh());

		String queryString = String.valueOf(
			updateByQueryRequest.getSearchRequest());

		Assert.assertTrue(queryString.contains(_FIELD_NAME));
		Assert.assertTrue(queryString.contains("\"value\":\"true\""));
	}

	private static final String _FIELD_NAME = "testField";

	private static final String _INDEX_NAME = "test_request_index";

	private static final Scripts _scripts = new ScriptsImpl();

	private ElasticsearchFixture _elasticsearchFixture;

}