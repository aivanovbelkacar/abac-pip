package BelkaCar.pip;

import java.util.Properties;
import java.util.Set;
import java.util.HashSet;

import org.wso2.carbon.identity.entitlement.pip.AbstractPIPAttributeFinder;

public class RestAttributeFinderModule extends AbstractPIPAttributeFinder {

	private BelkaCarHttpClient client;
	
	@Override
	public String getModuleName() {
		return "BelkaCar Attribute Finder";
	}

	@Override
	public Set<String> getSupportedAttributes() {
		try {
			return this.client.getAttributes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new HashSet<String>();
		}
	}

	@Override
	public void init(Properties properties) throws Exception {
		String endpoint = (String) properties.get("Endpoint");

        if(endpoint == null || endpoint.trim().length() == 0){
            throw new Exception("Endpoint can not be null. Please configure it in the entitlement.properties file.");
        }

        this.client = new BelkaCarHttpClient(endpoint);
	}

	@Override
	public Set<String> getAttributeValues(String subjectId, String resourceId, String actionId,
            String environmentId, String attributeId, String issuer) throws Exception {		// TODO Auto-generated method stub
		try {
			return this.client.getAttributeValues(subjectId, resourceId, actionId, environmentId, attributeId, issuer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new HashSet<String>();
		}
	}
}
