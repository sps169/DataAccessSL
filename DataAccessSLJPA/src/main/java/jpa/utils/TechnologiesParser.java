package jpa.utils;

import jpa.dtos.Technologies;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  Class in charge of parse String to a Set<Technologies> and vice versa
 * @author sps169, FedericoTB
 */

public class TechnologiesParser {
	public static String technologiesToString(Set<Technologies> technologies) {
		String technologiesString = "";
		for (Technologies tech : technologies) {
			technologiesString+=tech.name();
		}
		return technologiesString;
	}

	public static Set<Technologies> technologiesToSet (String technologies) {
		return Arrays.stream(technologies.split(";")).map(Technologies::valueOf).collect(Collectors.toSet());
	}
}
