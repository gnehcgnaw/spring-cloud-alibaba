/*
 * Copyright (C) 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.alibaba.fescar.feign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fescar.core.context.RootContext;

import org.springframework.beans.factory.BeanFactory;

import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FescarFeign执行客户端
 * 		(服务端处理方式：${@link org.springframework.cloud.alibaba.fescar.web.FescarHandlerInterceptor#preHandle(HttpServletRequest, HttpServletResponse, Object)})
 * @author xiaojing
 */
public class FescarFeignClient implements Client {

	private final Client delegate;
	private final BeanFactory beanFactory;

	FescarFeignClient(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
		this.delegate = new Client.Default(null, null);
	}

	FescarFeignClient(BeanFactory beanFactory, Client delegate) {
		this.delegate = delegate;
		this.beanFactory = beanFactory;
	}

	/**
	 * 执行请求
	 * @param request
	 * @param options
	 * @return
	 * @throws IOException
	 */
	@Override
	public Response execute(Request request, Request.Options options) throws IOException {
		//进行修改Request
		Request modifiedRequest = getModifyRequest(request);

		try {
			return this.delegate.execute(modifiedRequest, options);
		}
		finally {

		}
	}

	private Request getModifyRequest(Request request) {
		//TM向TC申请开启一个全局事务，全局事务创建成功并生产一个全局唯一的XID,XID会一直透传到下面的微服务中
		//添加xid
		String xid = RootContext.getXID();

		if (StringUtils.isEmpty(xid)) {
			return request;
		}

		Map<String, Collection<String>> headers = new HashMap<>();
		headers.putAll(request.headers());

		List<String> fescarXid = new ArrayList<>();
		fescarXid.add(xid);
		//将xid添加到请求头中
		headers.put(RootContext.KEY_XID, fescarXid);

		return Request.create(request.method(), request.url(), headers, request.body(),
				request.charset());
	}

}