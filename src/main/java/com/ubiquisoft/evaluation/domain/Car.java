package com.ubiquisoft.evaluation.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ubiquisoft.evaluation.domain.PartType.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

	private String year;
	private String make;
	private String model;

	private List<Part> parts;

	private static final Map<PartType, Integer> REQUIRED_PART_COUNT = new HashMap<>();

	static {
		REQUIRED_PART_COUNT.put(ENGINE, 1);
		REQUIRED_PART_COUNT.put(ELECTRICAL, 1);
		REQUIRED_PART_COUNT.put(FUEL_FILTER, 1);
		REQUIRED_PART_COUNT.put(OIL_FILTER, 1);
		REQUIRED_PART_COUNT.put(TIRE, 4);
	}


	public Map<PartType, Integer> getMissingPartsMap() {

		HashMap<PartType, Integer> missingParts = new HashMap<>(REQUIRED_PART_COUNT);

		this.parts.stream().map(Part::getType).forEach(
				type -> missingParts.computeIfPresent(
						type, (partType, count) -> count <= 1 ? null : count - 1));

		return missingParts;
	}

	@Override
	public String toString() {
		return "Car{" +
				       "year='" + year + '\'' +
				       ", make='" + make + '\'' +
				       ", model='" + model + '\'' +
				       ", parts=" + parts +
				       '}';
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters *///region
	/* --------------------------------------------------------------------------------------------------------------- */

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters End *///endregion
	/* --------------------------------------------------------------------------------------------------------------- */

}
