package scu.coen283.assign4;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PageFaultSimulator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Number of page fault frames");
		int numOfIter=sc.nextInt();
		System.out.println("Enter "+numOfIter+" of maximum number of page frames/ table entries");
		int[] frameSizeArr=new int[numOfIter];
		for(int i=0;i<frameSizeArr.length;i++) {  // Read the frame size
			frameSizeArr[i]=sc.nextInt();
		}
		sc.close();
		int[] pageFaultCount= new int[numOfIter];
		Util.createPageReferenceFile();  // generate the page reference input file 
		List<Integer> pageReference= Util.readPageReferenceFile(); // read the page reference input file
		for(int i=0;i<frameSizeArr.length;i++) {
			int faultCount=initPageTableEntry(frameSizeArr[i], pageReference);  // load the page to page table
			pageFaultCount[i]=faultCount;
		}
		// Output the result to console
		System.out.println("frame size  page fault");
		for(int i=0;i<numOfIter;i++) {
			System.out.println(frameSizeArr[i]+" "+pageFaultCount[i]);
		}
		}
	
	private static int initPageTableEntry(int tableSize, List<Integer> pageReference) {
		int faultCount=0;
		PageTableEntry[] pageTableEntries = new PageTableEntry[tableSize];
		for(int i=0;i<pageReference.size();i++) {
			int pageNumber=pageReference.get(i);
			int pageIndex= pageNumber%(tableSize-1);
			if(pageTableEntries[pageIndex]==null) {
				pageTableEntries[pageIndex] = new PageTableEntry(pageNumber, Util.setMSB(0), true);
				decAllPageEntries(pageTableEntries);
				faultCount++;
			}
			else if(PageFaultSimulator.checkIfPagePresnt(pageNumber,pageTableEntries)) {
				decAllPageEntries(pageTableEntries);
			}
			else {
				replacePage(pageNumber, pageTableEntries);
				decAllPageEntries(pageTableEntries);
				faultCount++;
			}
		}
	return faultCount;	
	}
	private static boolean checkIfPagePresnt(int pageNumber, PageTableEntry[] pageTableEntries) {
		for(int i=0;i<pageTableEntries.length;i++) {
			if(pageTableEntries[i] == null)continue;
			if(pageTableEntries[i].getPageNumber() == pageNumber) {
				pageTableEntries[i].setCounter(Util.setMSB(pageTableEntries[i].getCounter()));
				return true;
			}
		}
		return false;
	} 
	private static void decAllPageEntries(PageTableEntry[] pageTableEntries) {
		for(PageTableEntry p:pageTableEntries) {
			if(p==null)continue;
			p.setCounter(Util.decCounter(p.getCounter()));
		}
	}

	private static void replacePage(int pageNumber, PageTableEntry[] pageTableEntries) {
		for(int i=0;i<pageTableEntries.length;i++) {
			if(pageTableEntries[i] == null) {
				pageTableEntries[i] = new PageTableEntry(pageNumber, Util.setMSB(0), true);
				return;
			}
		}
		replaceWithLowestCount(pageNumber, pageTableEntries);
	}
	private static void replaceWithLowestCount(int pageNumber, PageTableEntry[] pageTableEntries) {
	  Arrays.sort(pageTableEntries, (PageTableEntry p1, PageTableEntry p2) -> p2.getCounter()-p1.getCounter());
	  pageTableEntries[0].setPageNumber(pageNumber);
	  pageTableEntries[0].setCounter(Util.setMSB(pageTableEntries[0].getCounter()));
	}
	}
