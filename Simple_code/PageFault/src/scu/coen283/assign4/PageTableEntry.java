package scu.coen283.assign4;

public class PageTableEntry {
	private int pageNumber=-1;
	private int counter=0;
	private boolean referenceBitFlag=false;
	public PageTableEntry(int pageNumber, int counter, boolean referenceBitFlag) {
		this.pageNumber = pageNumber;
		this.counter = counter;
		this.referenceBitFlag = referenceBitFlag;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public int getCounter() {
		return counter;
	}
	public boolean isReferenceBitFlag() {
		return referenceBitFlag;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public void setReferenceBitFlag(boolean referenceBitFlag) {
		this.referenceBitFlag = referenceBitFlag;
	}
}
