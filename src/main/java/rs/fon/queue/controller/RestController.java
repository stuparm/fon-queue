package rs.fon.queue.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import rs.fon.queue.json.MonitorJson;
import rs.fon.queue.service.RestService;

@Controller
public class RestController {

	@Autowired
	private RestService restService;
	
	
	
	@RequestMapping(value="/rest/monitor", method=RequestMethod.GET)
	public @ResponseBody MonitorJson monitor (HttpServletResponse response) {
		return getRestService().getMonitorJson();
	}
	
	@RequestMapping(value="/rest/insert", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> insert (@RequestParam(value="index", required=true) String studentIndex,
											 @RequestParam(value="stand", required=true) int standNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("INSERT",getRestService().getInsertJsonFor(studentIndex, standNumber));
		map.put("MONITOR",getRestService().getMonitorJson());
		return map;
	}
	
	@RequestMapping(value="/rest/check", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> check (@RequestParam(value="index", required=true) String studentIndex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CHECK",getRestService().getCheckingFor(studentIndex));
		map.put("MONITOR",getRestService().getMonitorJson());
		return map;
	}
	
	
	
	
	
	
	
	
	public RestService getRestService() {
		return restService;
	}
	public void setRestService(RestService restService) {
		this.restService = restService;
	}
}
