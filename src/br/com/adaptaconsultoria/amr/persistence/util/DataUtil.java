package br.com.adaptaconsultoria.amr.persistence.util;

import java.util.UUID;

public class DataUtil {

	public static String getId() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

}