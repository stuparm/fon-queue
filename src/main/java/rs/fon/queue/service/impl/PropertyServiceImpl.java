package rs.fon.queue.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import rs.fon.queue.model.PropertyModel;
import rs.fon.queue.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService{

	@Override
	public List<PropertyModel> getDatabaseProperties() {
		Resource resource = new ClassPathResource("/database.properties");
		Properties props;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		List<PropertyModel> listPM = new ArrayList<PropertyModel>();
		Enumeration<?> en = props.propertyNames();
		while(en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String value = props.getProperty(key);
			PropertyModel pm = new PropertyModel();
			pm.setKey(key);
			pm.setValue(value);
			listPM.add(pm);
		}
		return listPM;
	}
	
}
