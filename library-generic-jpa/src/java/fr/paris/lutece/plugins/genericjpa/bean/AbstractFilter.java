/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.genericjpa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The abstract class for filter
 * 
 * @param <K>
 *            the type of the bean primary key
 */
public abstract class AbstractFilter<K> implements Serializable {
	private static final long serialVersionUID = -2029442617417680733L;
	private Boolean _orderAsc = true;
	private List<String> _orders = new ArrayList<String>();
	private K _id;

	public String convertFieldName(String fieldName) {
		return fieldName;
	}

	/**
	 * Get the order for SELECT query
	 * 
	 * @return the orderAsc
	 */
	public Boolean getOrderAsc() {
		return _orderAsc;
	}

	/**
	 * @param orderAsc
	 *            the orderAsc to set
	 */
	public void setOrderAsc(final Boolean orderAsc) {
		this._orderAsc = orderAsc;
	}

	/**
	 * Get the column to order
	 * 
	 * @return the orders
	 */
	public List<String> getOrders() {
		return _orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(final List<String> orders) {
		this._orders = orders;
	}

	/**
	 * @return the id
	 */
	public K getId() {
		return _id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final K id) {
		this._id = id;
	}
}
