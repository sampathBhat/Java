package dataGenerator;

public class DataBeanV5{

    private long dOctets;
    private long dPkts;
    private int dst_as;
    private int dst_mask;
    private long dstaddr;
    private int dstport;
    private long first;
    private int input;
    private long last;
    private long nexthop;
    private int output;
    private int pad1;
    private int pad2;
    private int prot;
    private int src_as;
    private int src_mask;
    private long srcaddr;
    private int srcport;
    private int tcp_flags;
    private int tos;
    private String version;
    private int count;
    private long uptime; 
    private long unixTime;
    private long unixNanoTime;
    private long sequence_number;
    private char engine_type;
    private char engine_id;
    private int sampling_interval;
    private String name;
    private String type;
  	
    public static final String CSV_HEADER="version,count,sys_uptime,unix_secs,unix_nsecs,flow_sequence,engine_type,engine_id,sampling_interval,srcaddr,dstaddr,nexthop,input,output,dPkts,dOctets,first,last,srcport,dstport,pad1,tcp_flags,prot,tos,src_as,dst_as,src_mask,dst_mask,pad2,Name,Type";
    private static final String comma=",";
    public static final int MIN = 1, MAX = Integer.MAX_VALUE/1000;
    private String csv=null;
    public DataBeanV5() {
        this.setAllValues();
    }

    private void setAllValues() {
        this.srcaddr = Util.getRandomValue(MIN, MAX);
        this.dstaddr = Util.getRandomValue(MIN, MAX);
        this.nexthop = Util.getRandomValue(MIN, MAX);
        this.input = Util.getRandomValue(MIN, MAX);
        this.output = Util.getRandomValue(MIN, MAX);
        this.dPkts = Util.getRandomValue(MIN, MAX);
        this.dOctets = Util.getRandomValue(MIN, MAX);
        this.first = Util.getRandomValue(MIN, MAX);
        this.last = Util.getRandomValue(MIN, MAX);
        this.srcport = Util.getRandomValue(MIN, MAX);
        this.dstport = Util.getRandomValue(MIN, MAX);
        this.pad1 = Util.getRandomValue(MIN, MAX);
        this.tcp_flags = Util.getRandomValue(MIN, MAX);
        this.prot = Util.getRandomValue(MIN, MAX);
        this.tos = Util.getRandomValue(MIN, MAX);
        this.src_as = Util.getRandomValue(MIN, MAX);
        this.dst_as = Util.getRandomValue(MIN, MAX);
        this.src_mask = Util.getRandomValue(MIN, MAX);
        this.dst_mask = Util.getRandomValue(MIN, MAX);
        this.pad2 = Util.getRandomValue(MIN, MAX);
        this.name="\"NetFlow-V5\"";
        this.type="\"RawData\"";
        
        this.version="5";
        this.count=Util.getRandomValue(MIN, MAX);
        this.uptime=Util.getRandomValue(MIN, MAX); 
        this.unixTime=System.currentTimeMillis()/1000;
        this.unixNanoTime=System.nanoTime();
        this.sequence_number=Util.getRandomValue(MIN, MAX);;
        this.engine_type='9';
        this.engine_id='8';
        this.sampling_interval=Util.getRandomValue(MIN, MAX);;
        
        this.csv= version + comma + count + comma + uptime + comma + unixTime + comma + unixNanoTime + comma + sequence_number + comma + engine_type + comma + engine_id + comma + sampling_interval + comma + srcaddr + comma + dstaddr + comma + nexthop + comma + input + comma + output + comma + dPkts + comma + dOctets + comma + first + comma + last + comma + srcport + comma + dstport + comma + pad1 + comma + tcp_flags + comma + prot + comma + tos + comma + src_as + comma + dst_as + comma + src_mask + comma + dst_mask + comma + pad2+comma+name+comma+type;
        
    }
	
	public String getCSV(){
		return csv;
		
	}
  
}
