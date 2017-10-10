package BelkaCar.pip;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Objects;
import java.util.Set;
import java.net.URL;
import java.net.URI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;



public class BelkaCarHttpClient {

	private URL endpoint;
	private static Log log = LogFactory.getLog(RestAttributeFinderModule.class);
	
	public BelkaCarHttpClient(String endpoint) throws Exception {
		this.setEndpoint(new URL(endpoint));
	}

	private void setEndpoint(URL endpoint) {
		this.endpoint = endpoint;
	}
	
	public Set<String> getAttributes() throws Exception {
		log.info("Start");
		ObjectMapper mapper = new ObjectMapper();
		log.info("Got mapper");
		TypeReference<Set<String>> mapType = new TypeReference<Set<String>>() {};
		ObjectReader reader = mapper.readerFor(mapType);
        log.info("Get attributes list...");
        Set<String> attributes = reader.readValue(this.endpoint);
        //AttributesList wrapper = reader.readValue(this.endpoint);
		//Set<String> attributes = wrapper.getValues();
		//Set<String> attributes = new HashSet<String>();
	
        log.info("Got attributes list... ".concat(attributes.toString()));
		
		return attributes;
	}
	
	public Set<String> getAttributeValues(String subjectId, String resourceId, String actionId,
            String environmentId, String attributeId, String issuer) throws Exception {
		log.info("Start");
		ObjectMapper mapper = new ObjectMapper();
		log.info("Got mapper");
		TypeReference<Set<String>> mapType = new TypeReference<Set<String>>() {};
		ObjectReader reader = mapper.readerFor(mapType);
        log.info("Get attributes list...");
        
        String query = "".concat("subjectId")
        		.concat("=")
        		.concat(Objects.toString(subjectId, ""))
        		.concat("&")
        		.concat("resourceId")
        		.concat("=")
        		.concat(Objects.toString(resourceId, ""))
        		.concat("&")
        		.concat("actionId")
        		.concat("=")
        		.concat(Objects.toString(actionId, ""))
        		.concat("&")
        		.concat("environmentId")
        		.concat("=")
        		.concat(Objects.toString(environmentId, ""))
        		.concat("&")
        		.concat("issuer")
        		.concat("=")
        		.concat(Objects.toString(issuer, ""));
        log.info("Query is... ".concat(query));
        URI endpointWithQuery = new URI(
        		this.endpoint.getProtocol(),
        		this.endpoint.getAuthority(),
        		this.endpoint.getPath().concat("/").concat(attributeId),
        		query,
        		null
    		);
        log.info("Url is... ".concat(endpointWithQuery.toString()));
        Set<String> attributes = reader.readValue(endpointWithQuery.toURL());
        //AttributesList wrapper = reader.readValue(this.endpoint);
		//Set<String> attributes = wrapper.getValues();
		//Set<String> attributes = new HashSet<String>();
	
        log.info("Got attribute values list... ".concat(attributes.toString()));
		
		return attributes;
	}
}
