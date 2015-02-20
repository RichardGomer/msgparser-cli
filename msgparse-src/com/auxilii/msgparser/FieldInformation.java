/*
 * msgparser - http://auxilii.com/msgparser
 * Copyright (C) 2007  Roman Kurmanowytsch
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package com.auxilii.msgparser;

import org.apache.poi.poifs.filesystem.DocumentEntry;

/**
 * Convenience class for storing type information
 * about a {@link DocumentEntry}.
 * 
 * @author roman.kurmanowytsch
 */
public class FieldInformation {

	/**
	 * The default value for both the {@link #clazz} and
	 * the {@link #type} properties.
	 */
	public static final String UNKNOWN = "unknown";
	
	/**
	 * The default value for the {@link #mapiType}
	 */
	public static final int UNKNOWN_MAPITYPE = -1;
	
	/**
	 * The class of the {@link DocumentEntry}.
	 */
	protected String clazz = UNKNOWN;
	
	/**
	 * The mapi type of the {@link DocumentEntry}.
	 */
	protected int mapiType  = UNKNOWN_MAPITYPE;

	/**
	 * Empty constructor that uses the default
	 * values.
	 */
	public FieldInformation() {
	}

	
	/**
	 * Constructor that allows to set the class
	 * and type properties.
	 * 
	 * @param clazz The class of the {@link DocumentEntry}.
	 * @param mapiType The mapiType of the {@link DocumentEntry} (see {@link MAPIProp}).
	 */
	public FieldInformation(String clazz, int mapiType) {
		this.setClazz(clazz);
		this.setMapiType(mapiType);
	}

	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}


	/**
	 * @return the mapiType
	 */
	public int getMapiType() {
		return mapiType;
	}

	/**
	 * @param mapiType the mapiType to set
	 */
	public void setMapiType(int mapiType) {
		this.mapiType = mapiType;
	}
	
	
	
}
