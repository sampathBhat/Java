package dataGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.json.JSONObject;

public class V5 {

    public interface int_DataFields {

        public static final int dOctets = 6;
        public static final int dPkts = 5;
        public static final int dst_as = 16;
        public static final int dst_mask = 18;
        public static final int dstaddr = 1;
        public static final int dstport = 10;
        public static final int first = 7;
        public static final int input = 3;
        public static final int last = 8;
        public static final int nexthop = 2;
        public static final int output = 4;
        public static final int pad1 = 11;
        public static final int pad2 = 19;
        public static final int prot = 13;
        public static final int src_as = 15;
        public static final int src_mask = 17;
        public static final int srcaddr = 0;
        public static final int srcport = 9;
        public static final int tcp_flags = 12;
        public static final int tos = 14;
    }

    /* public enum HeaderFields {
    H_VERSION=0,H_COUNT, H_SYSUPTIME, H_UNIXSECS, H_UNIXNSECS, H_FLOWSEQUENCE,H_ENGINETYPE,H_ENGINEID,H_SAMPLINGINTERVAL
            }*/
    public interface int_HeaderFields {

        public static final int count = 1;
        public static final int engine_id = 7;
        public static final int engine_type = 6;
        public static final int flow_sequence = 5;
        public static final int sampling_interval = 8;
        public static final int sys_uptime = 2;
        public static final int unix_nsecs = 4;
        public static final int unix_secs = 3;
        public static final int version = 0;
    }

    static long epocTime;

    static int groupByCount = 0;

    static String headerFields[] = { "version", "count", "sys_uptime", "unix_secs", "unix_nsecs", "flow_sequence",
            "engine_type", "engine_id", "sampling_interval" };

    static int NF_VERSION_V5 = 5;

    static long numberofGroupBy = 2;

    static String pduFileds[] = { "srcaddr", "dstaddr", "nexthop", "input", "output", "dPkts", "dOctets", "first",
            "last", "srcport", "dstport", "pad1", "tcp_flags", "prot", "tos", "src_as", "dst_as", "src_mask",
            "dst_mask", "pad2"};

    static long sequenceNumber = 1;

    static int UDP = 17;

    static long upTime;
    private static boolean csvOrJsonFlag=true;   //true= csv format   false = json format
    private static boolean socketOrFileFlag=false; // true= write to socket  false= write to file
    private static AtomicLong seqLong=new AtomicLong(0);
    private static long numOfLinesPerFile=1000;
    private static long numOfFilesPerSecond=50;
    private static short portNumber=0;
    private static String destIPaddress=null;
    public static long getEpochTime() {
        // return System.currentTimeMillis();
        return System.currentTimeMillis();
    }

    public static long getHeaderFieldValue(final int index) throws Exception {
        long returnValue = 0;
        switch (index) {
            case int_HeaderFields.version: {
                returnValue = V5.NF_VERSION_V5;
                break;
            }
            case int_HeaderFields.count: {
                returnValue = 1;
                break;
            }
            case int_HeaderFields.sys_uptime: {
                // returnValue = V5.getSystemUptime();
                returnValue = V5.upTime;
                break;
            }
            case int_HeaderFields.unix_secs: {
                // returnValue = V5.getEpochTime() / 1000;
                returnValue = V5.epocTime / 1000;
                break;
            }
            case int_HeaderFields.unix_nsecs: {
                // returnValue = V5.getEpochTime() * 1000;
                returnValue = V5.epocTime * 1000;
                break;
            }
            case int_HeaderFields.engine_type: {
                returnValue = 0;
                break;
            }
            case int_HeaderFields.engine_id: {
                returnValue = 0;
                break;
            }
            case int_HeaderFields.flow_sequence: {
                returnValue = V5.sequenceNumber;
                break;
            }
	    case int_HeaderFields.sampling_interval: {
		returnValue = 1;
		break;
		}
            default: {
                System.out.println("index is not correct "+index);
                returnValue = V5.sequenceNumber;
                break;
            }
        }
        return returnValue;
    }

    public static long getPduFieldValue(final int index) throws Exception {
        long returnValue = 0;
        switch (index) {
            case int_DataFields.dOctets: {
                // returnValue = 64;
                final Random rand = new Random();
                returnValue = rand.nextInt(64) + 1;
                ;
                break;
            }
            case int_DataFields.dPkts: {
                returnValue = 1;
                break;
            }
            case int_DataFields.dst_as: {
                returnValue = 3;
                break;
            }
            case int_DataFields.dst_mask: {
                returnValue = 31;
                break;
            }
            case int_DataFields.dstaddr: {
                returnValue = 0x7f000001;
                break;
            }
            case int_DataFields.dstport: {
                returnValue = 3434;
                break;
            }
            case int_DataFields.first: {
                // returnValue = V5.getSystemUptime();
                returnValue = V5.upTime;
                break;
            }
            case int_DataFields.input: {
                returnValue = 1;
                break;
            }
            case int_DataFields.last: {
                // returnValue = V5.getSystemUptime();
                returnValue = V5.upTime;
                break;
            }
            case int_DataFields.nexthop: {
                returnValue = 0x0;
                break;
            }
            case int_DataFields.output: {
                returnValue = 2;
                break;
            }
            case int_DataFields.pad1: {
                returnValue = 0;
                break;
            }
            case int_DataFields.pad2: {
                returnValue = 0;
                break;
            }
            case int_DataFields.prot: {
                returnValue = V5.UDP;
                break;
            }
            case int_DataFields.src_as: {
                returnValue = 2;
                break;
            }
            case int_DataFields.src_mask: {
                returnValue = 31;
                break;
            }
            case int_DataFields.srcaddr: {
                // returnValue = (0x7f00001 + (groupByCount%numberofGroupBy ));
                returnValue = (0x17fad965 + V5.groupByCount);
                break;
            }
            case int_DataFields.srcport: {
                // returnValue = (4242 + (groupByCount%numberofGroupBy ));
                returnValue = (4242 + V5.groupByCount);
                break;
            }
            case int_DataFields.tcp_flags: {
                returnValue = 0;
                break;
            }
            case int_DataFields.tos: {
                returnValue = 1;
                break;
            }
            default: {
                System.out.println(" data-index is not correct "+index);
                returnValue = V5.sequenceNumber;
                break;
            }
        }
        return returnValue;
    }

    public static long getSystemUptime() throws Exception {
        long uptime = -1;
        final String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            final Process uptimeProc = Runtime.getRuntime().exec("net stats srv");
            final BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Statistics since")) {
                    final SimpleDateFormat format = new SimpleDateFormat("'Statistics since' MM/dd/yyyy hh:mm:ss a");
                    final Date boottime = format.parse(line);
                    uptime = System.currentTimeMillis() - boottime.getTime();
                    break;
                }
            }
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            final Process uptimeProc = Runtime.getRuntime().exec("uptime");
            final BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
            final String line = in.readLine();
            if (line != null) {
                final Pattern parse = Pattern.compile("((\\d+) days,)? (\\d+):(\\d+)");
                final Matcher matcher = parse.matcher(line);
                if (matcher.find()) {
                    final String _days = matcher.group(2);
                    final String _hours = matcher.group(3);
                    final String _minutes = matcher.group(4);
                    final int days = _days != null ? Integer.parseInt(_days) : 0;
                    final int hours = _hours != null ? Integer.parseInt(_hours) : 0;
                    final int minutes = _minutes != null ? Integer.parseInt(_minutes) : 0;
                    uptime = (minutes * 60000) + (hours * 60000 * 60) + (days * 6000 * 60 * 24);
                }
            }
        }
        return uptime;
    }

    public static void main(final String[] args) throws Exception {
        System.out.println("Running NetFlowv5 generator!");
        String dataDirectoryPath = null;

        final Option helpOption = Option.builder("h").longOpt("help").required(false).desc("shows this message")
                .build();
        final Option dataDirectory = Option.builder("d").longOpt("dataDirectory").numberOfArgs(1).required(true)
                .type(String.class).desc("Data directory path").build();
        final Option groupby = Option.builder("g").longOpt("groupby").numberOfArgs(1).required(false).type(Number.class)
                .desc("Number of group by").build();
	 final Option directHadoop = Option.builder("w").longOpt("directHadoop").numberOfArgs(1).required(false).type(String.class)
                .desc("write direct to hdfs").build();
	 
	 final Option csvOrJson = Option.builder("c").longOpt("csvOrJson").numberOfArgs(1).required(true).type(String.class)
             .desc("frame csv or json").build();
	 final Option socketOrFile = Option.builder("s").longOpt("socketOrFile").numberOfArgs(1).required(false).type(String.class)
             .desc("transmit csv over socket or file").build();
	 final Option numOfLines = Option.builder("n").longOpt("numOfLines").numberOfArgs(1).required(false).type(Number.class)
             .desc("number of lines that has to be written into file").build();
	 final Option numOfFiles = Option.builder("fps").longOpt("numOfFiles").numberOfArgs(1).required(false).type(Number.class)
             .desc("number of files that has to be written per second").build();
	 final Option portNum = Option.builder("port").longOpt("portNum").numberOfArgs(1).required(false).type(String.class)
             .desc("port number to which data has to be sent").build();
	 final Option ipAddress = Option.builder("ip").longOpt("ipAddress").numberOfArgs(1).required(false).type(String.class)
             .desc("ipaddress to which data has to be sent").build();


        final Options options = new Options();
        options.addOption(helpOption);
        options.addOption(dataDirectory);
        options.addOption(groupby);
        options.addOption(directHadoop);
        options.addOption(csvOrJson);
        options.addOption(socketOrFile);
        options.addOption(numOfLines);
        options.addOption(numOfFiles);
        options.addOption(portNum);
        options.addOption(ipAddress);
        

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmdLine = parser.parse(options, args);
        if (cmdLine.hasOption("help")) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Netflow V5 generator", options);
            System.exit(1);
        }
        if (cmdLine.hasOption("dataDirectory")) {
            dataDirectoryPath = (String) cmdLine.getParsedOptionValue("dataDirectory");
        } else {
            System.err.println("dataDirectory should be passed");
            System.exit(1);
        }
        
        
        if (cmdLine.hasOption("csvOrJson")) {
        	String tmpVar=cmdLine.getParsedOptionValue("csvOrJson").toString();
            if(tmpVar.equals("csv")){
            csvOrJsonFlag=true;
            System.out.println("The file format specified is csv and csvOrJsonFlag is "+ csvOrJsonFlag);
            }
            if(tmpVar.equals("json")){
            	csvOrJsonFlag=false;
            	System.out.println("The file format specified is json and csvOrJsonFlag is "+ csvOrJsonFlag);
            }
            
            
        } else {
            System.err.println("csv or json file format must be specidfied");
            System.exit(1);
        }
        
        
        
        if (cmdLine.hasOption("socketOrFile")) {
            if(cmdLine.getParsedOptionValue("socketOrFile").toString().contains("udp"))
            socketOrFileFlag=true;
            if(cmdLine.getParsedOptionValue("socketOrFile").toString().contains("file"))
            socketOrFileFlag=false;
            
        } else {
        	socketOrFileFlag=false;
        }
        
        if (cmdLine.hasOption("numOfLines")) {
           numOfLinesPerFile= (long)cmdLine.getParsedOptionValue("numOfLines");
        }
        
        if (cmdLine.hasOption("numOfFiles")) {
            numOfFilesPerSecond= (long)cmdLine.getParsedOptionValue("numOfFiles");
         }
        if (cmdLine.hasOption("portNum")) {
        	portNumber= Short.valueOf(cmdLine.getParsedOptionValue("portNum").toString());
         }
        else{
        	System.err.println("No port number is passed hence using default 3939");
        	portNumber=3939;
        }
        
        if (cmdLine.hasOption("ipAddress")) {
        	destIPaddress= cmdLine.getParsedOptionValue("ipAddress").toString();
         }
        else{
        	System.err.println("No IP address is passed hence using default 127.0.0.1");
        	destIPaddress="127.0.0.1";
        }
        
int hadoopwrite=0;
 if (cmdLine.hasOption("directHadoop")) {
//            dataDirectoryPath = (String) cmdLine.getParsedOptionValue("directHadoop");
            hadoopwrite=1;
        } 
        // Long nGroupBy;
        if (cmdLine.hasOption("groupby")) {
            // System.err.println(cmdLine.getParsedOptionValue("groupby"));
            V5.numberofGroupBy = (Long) cmdLine.getParsedOptionValue("groupby");
            // System.err.println(V5.numberofGroupBy);
            // nGroupBy
        } else {
            System.err.println("number of groupby is not passed hence taking default value of 1");
          //  System.exit(1);
        }
        // AGGREGATION_PERIOD in secs

        final long AGGREGATION_PERIOD = 1L;
        final long EPOCH_TIME = V5.getEpochTime() / 1000;
        long QUERYSTART_TIME = EPOCH_TIME;
        long QUERYEND_TIME = QUERYSTART_TIME + AGGREGATION_PERIOD;

        final File theDir = new File(dataDirectoryPath);
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
            } catch (final SecurityException se) {
                System.err.println(dataDirectoryPath + " Directory creation failure");
            }
        }
        
        // to write files in json format
        if(!csvOrJsonFlag){
        String filePath = dataDirectoryPath + File.separator + "data_" + QUERYSTART_TIME + ".json" + ".temp";
        String finalfilePath = dataDirectoryPath + File.separator + "data_" + QUERYSTART_TIME + "_1" + ".json";
        FileWriter file = new FileWriter(filePath);
        System.out.println("FILE Created " + filePath);

        while (true) {
            final long CURRENT_TIME = V5.getEpochTime() / 1000;
            if (CURRENT_TIME > QUERYEND_TIME) {
                // #initialise start and end time for next query
                QUERYSTART_TIME = QUERYEND_TIME;
                QUERYEND_TIME = QUERYSTART_TIME + AGGREGATION_PERIOD;
                // Create a new file and trigger HDFS put for old file
                file.close();

                Files.move(Paths.get(filePath), Paths.get(finalfilePath));
                // Trigger HDFS put for old file
                // script filePath
                if(hadoopwrite==1){
                 final HdfsPut h = new HdfsPut(finalfilePath);
                 h.start();
}
                filePath = dataDirectoryPath + File.separator + "data_" + QUERYEND_TIME + ".json" + ".temp";
		finalfilePath = dataDirectoryPath + File.separator + "data_" + QUERYSTART_TIME + "_1" + ".json";
                file = new FileWriter(filePath);
                System.out.println("FILE Created " + filePath);
            }

            // Current epoch time nad systemuptime
            V5.epocTime = V5.getEpochTime();
            V5.upTime = V5.getSystemUptime();

            final JSONObject obj = new JSONObject();
            obj.put("Type", "RawData");
            obj.put("Name", "NetFlow-V5");

            // HEADER
            for (int loop = 0; loop < V5.headerFields.length; loop++) {
                obj.put(V5.headerFields[loop], V5.getHeaderFieldValue(loop));
            }

            // PDU
            for (int loop = 0; loop < V5.pduFileds.length; loop++) {
                obj.put(V5.pduFileds[loop], V5.getPduFieldValue(loop));
            }

            if(!socketOrFileFlag){
            file.write(obj.toString());
            file.write("\n");
            file.flush();
            V5.sequenceNumber++;
            V5.groupByCount++;
            V5.groupByCount = (int) (V5.groupByCount % V5.numberofGroupBy);}
            else if(socketOrFileFlag){
            	sendOverUDP(obj.toString(), portNumber,destIPaddress,numOfLinesPerFile,numOfFilesPerSecond);
            }
        }
        }
        
        else{
        	DataBeanV5 dataBeanV5=new DataBeanV5();
        	long tmpTime=System.currentTimeMillis()/1000;
        	String content=getCSVcontent(dataBeanV5);
        	if(!socketOrFileFlag){
        	while(true){
        		long tmpSeqNum=getSeqLong();
        		String filename=String.valueOf(System.currentTimeMillis()/1000)+"_"+tmpSeqNum;	
        		
        		writeCSV(dataBeanV5,dataDirectoryPath, filename,content);
        		while(tmpSeqNum>=numOfFilesPerSecond && ((System.currentTimeMillis()/1000)-tmpTime<1)){
        			Thread.sleep(10);
        		}
        		if((System.currentTimeMillis()/1000)-tmpTime>=1){
        			tmpTime=System.currentTimeMillis()/1000;
        			seqLong.set(1);
        		}
        		
        	}
        	}else if(socketOrFileFlag){
        		sendOverUDP(dataBeanV5,portNumber,destIPaddress,numOfLinesPerFile,numOfFilesPerSecond);
        	}
        }
    }
    
    
    private static void writeCSV(DataBeanV5 dataBeanV5,String dataDirectoryPath, String filename, String content) throws Exception{
    	
    	 String filePath = dataDirectoryPath + File.separator + "data_" + filename + ".csv" + ".temp";
         String finalfilePath = dataDirectoryPath + File.separator + "data_" + filename + "_1" + ".csv";
         FileWriter file = new FileWriter(filePath);
         System.out.println("FILE Created " + filePath);
        file.write(content);
        file.flush();
         file.close();
         Files.move(Paths.get(filePath), Paths.get(finalfilePath));
    }
    
    private static long getSeqLong(){
    	return seqLong.getAndIncrement();
    }
    
    private static void sendOverUDP(DataBeanV5 dataBeanV5,int portNum,String ipAddress,long numOfCSVperUDP,long numOfPackets){
    	DatagramSocket clientSocket=null;
    	try{
    		clientSocket = new DatagramSocket();
        final InetAddress IPAddress = InetAddress.getByName(ipAddress);
        DatagramPacket csvHeaderUDP;
        
        String pacContent=DataBeanV5.CSV_HEADER;
        for(int i=1;i<numOfCSVperUDP;i++){
        	pacContent+="\n";
        	pacContent+=dataBeanV5.getCSV();
        }
        byte[] csvHeader=pacContent.getBytes();
        csvHeaderUDP = new DatagramPacket(csvHeader, csvHeader.length, IPAddress, portNum);
        long tmpSleepTime=Util.getBusySleepTime(numOfPackets);
        while(true){
        clientSocket.send(csvHeaderUDP);
        Util.busySleep(tmpSleepTime);
        }
        }catch(Exception e){
        e.printStackTrace();	
        }finally{
        	if(clientSocket!=null)
        	clientSocket.close();
        }
    }
    
    
    private static void sendOverUDP(String content,int portNum,String ipAddress,long numOfCSVperUDP,long numOfPackets){
    	DatagramSocket clientSocket=null;
    	try{
    		clientSocket = new DatagramSocket();
        final InetAddress IPAddress = InetAddress.getByName(ipAddress);
        DatagramPacket jsonUDP;
        
        String pacContent=content;
        for(int i=1;i<numOfCSVperUDP;i++){
        pacContent+="\n";
        pacContent+=content;
        }
        byte[] jsonUDPContent=pacContent.getBytes();
        jsonUDP = new DatagramPacket(jsonUDPContent, jsonUDPContent.length, IPAddress, portNum);
        long tmpSleepTime=Util.getBusySleepTime(numOfPackets);
        while(true){
        clientSocket.send(jsonUDP);
        Util.busySleep(tmpSleepTime);
        }
        }catch(Exception e){
        e.printStackTrace();	
        }finally{
        	if(clientSocket!=null)
        	clientSocket.close();
        }
    }
    
    private static String getCSVcontent(DataBeanV5 dataBeanV5){
    	String content=DataBeanV5.CSV_HEADER;
    	content+="\n";
    	for(int i=1;i<numOfLinesPerFile;i++){
    		content+=dataBeanV5.getCSV();
    		content+="\n";
        }
    	return content;
    }
    
}
