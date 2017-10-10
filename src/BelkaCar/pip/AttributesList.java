package BelkaCar.pip;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class AttributesList {
	private Set<String> values;

	public AttributesList(List<String> values) {
		super();
		this.values = new HashSet<String>();
		values.forEach((v)->{this.values.add(v);});
	}

	public Set<String> getValues() {
		return this.values;
	}
}
