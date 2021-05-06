package no.hvl.dat110.ac.restservice;

import com.google.gson.Gson;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccessLog {
	
	// atomic integer used to obtain identifiers for each access entry
	private AtomicInteger cid;
	protected ConcurrentHashMap<Integer, AccessEntry> log;
	
	public AccessLog () {
		this.log = new ConcurrentHashMap<Integer,AccessEntry>();
		cid = new AtomicInteger(0);
	}

	// add an access entry to the log for the provided message and return assigned id
	public int add(String message) {
		int id = cid.getAndIncrement();
		log.put(id, new AccessEntry(id, message));
		return id;
	}
		
	// retrieve a specific access entry from the log
	public AccessEntry get(int id) {
		AccessEntry entry = null;
		try {
			entry = log.get(id);
		}catch (Exception e){
			e.printStackTrace();
		}
		return entry;
	}
	
	// clear the access entry log
	public void clear() {
		log.clear();
	}
	
	// return JSON representation of the access log
	public String toJson() { return new Gson().toJson(log); }
}
