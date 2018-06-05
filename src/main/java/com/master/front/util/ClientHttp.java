/*
 * Copyright (coffee) 2015. qAtive Tecnologia. Todos os direitos reservados.
 * Este código fonte não pode ser copiado e/ou utilizado em projetos fora
 * da instituição de acordo com cláusula de segurança da informação em seu
 * contrato de trabalho.
 *
 */

package com.master.front.util;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ejb.Stateful;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Map;

/**
 * Classe que encapsula uma conexão HTTP para realizar métodos GET, POST e PATCH
 *
 * @author Filipe D. Abreu
 * @since 24/03/2016
 */
@Stateful
public class ClientHttp implements Serializable {
	private static final long serialVersionUID = 1L;

	private Invocation.Builder builder;

	public ClientHttp preparaClientHttp(String uri, String path, Map<String, Object> headers, MediaType... mediaTypes) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(uri + path);
		builder = target.request();
		if (mediaTypes != null && mediaTypes.length > 0) {
			builder.accept(mediaTypes);
		}
		if (headers != null) {
			builder.headers(parseHeadersForRequest(headers));
		}
		return this;
	}

	/**
	 * Faz um post com uma entidade passada por parâmetro
	 *
	 * @param entity
	 */
	public Response post(Entity entity) {
		if (builder != null) {
			return builder.post(entity);
		}
		return null;
	}

	/**
	 * Faz um post com json como data
	 *
	 * @param json
	 */
	public Response post(String json) {
		if (builder != null) {
			return builder.post(Entity.json(json));
		}
		return null;
	}


	public Response get() {
		if (builder != null) {
			return builder.get();
		}
		return null;
	}

	/**
	 * Transforma um Map do pacote java.util em um {@link MultivaluedHashMap}
	 *
	 * @param pheaders
	 * @return
	 */
	private MultivaluedMap<String, Object> parseHeadersForRequest(Map<String, Object> pheaders) {
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
		for (Map.Entry<String, Object> header : pheaders.entrySet()) {
			headers.putSingle(header.getKey(), header.getValue());
		}
		return headers;
	}
}
