package edu.wpi.cs.proteus.model;

public class Benchmark {

	String id;
	String implemetationid;
	String probleminstanceid;
	String name;
	String time;
	String cpu;
	String cores;
	String ram;
	String cache;
	String requestedBy;
	public Benchmark(String id, String implemetationid, String probleminstanceid, String name, String time, String cpu,
			String cores, String ram, String cache, String requestedBy) {
		super();
		this.id = id;
		this.implemetationid = implemetationid;
		this.probleminstanceid = probleminstanceid;
		this.name = name;
		this.time = time;
		this.cpu = cpu;
		this.cores = cores;
		this.ram = ram;
		this.cache = cache;
		this.requestedBy = requestedBy;
	}
	public Benchmark(String implemetationid, String probleminstanceid, String name, String time, String cpu,
			String cores, String ram, String cache, String requestedBy) {
		super();
		this.implemetationid = implemetationid;
		this.probleminstanceid = probleminstanceid;
		this.name = name;
		this.time = time;
		this.cpu = cpu;
		this.cores = cores;
		this.ram = ram;
		this.cache = cache;
		this.requestedBy = requestedBy;
	}
	public Benchmark()
	{
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImplemetationid() {
		return implemetationid;
	}
	public void setImplemetationid(String implemetationid) {
		this.implemetationid = implemetationid;
	}
	public String getProbleminstanceid() {
		return probleminstanceid;
	}
	public void setProbleminstanceid(String probleminstanceid) {
		this.probleminstanceid = probleminstanceid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getCores() {
		return cores;
	}
	public void setCores(String cores) {
		this.cores = cores;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}


}
