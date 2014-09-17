package code.madhan.sfmovietour.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="facetcounts")
public class FacetCount{
	private String id;
	private FacetValue value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public FacetValue getValue() {
		return value;
	}
	public void setValue(FacetValue value) {
		this.value = value;
	}
}
