package de.webis.clueweb_filter;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import lombok.SneakyThrows;

public class Clueweb12B13Filter {
	public static final Set<String> IDS = Collections.unmodifiableSet(loadIds());

	@SneakyThrows
	private static Set<String> loadIds() {
		InputStream resource = Clueweb12B13Filter.class.getResourceAsStream("../../../clueweb-12-b13-ids.txt");
		Set<String> ret = new HashSet<>();
		
		for(String id: IOUtils.readLines(resource, StandardCharsets.UTF_8)) {
			ret.add(id);
		}
		
		return ret;
	}

	public static boolean idIsClueweb12B13(String id) {
		return IDS.contains(id);
	}
}
