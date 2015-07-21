package rs.fon.queue.service;

import rs.fon.queue.json.CheckJson;
import rs.fon.queue.json.InsertJson;
import rs.fon.queue.json.MonitorJson;


public interface RestService {

	/**
	 * Creates JSON part for insertion call
	 * 
	 * @param studentIndex
	 * @param standNumber
	 * @return
	 */
	public InsertJson getInsertJsonFor(String studentIndex, int standNumber);

	/**
	 * Create JSON part for monitor. Here are information about all stands.
	 * These JSON goes alone for monitor URL and goes as part of bigger JSON for
	 * INSERT and CHECK URL
	 * 
	 * @return
	 */
	public MonitorJson getMonitorJson();

	/**
	 * Creates JSON part for checking URL. Contains information about all queues.
	 * 
	 * @param studentIndex
	 * @return
	 */
	public CheckJson getCheckingFor(String studentIndex);
	
}
