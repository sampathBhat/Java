package leetcode.simple;

public class Divide2Number {

	public static void main(String[] args) {
		int dividend =100; int divisor=3;
		dividend=Math.abs(dividend);
		divisor=Math.abs(divisor);
		int globalDivisor=divisor;
		int numShift;
		int result=0;
		while(dividend>=divisor ) {
			numShift=1;	
		while(dividend>=(divisor=divisor<<1)) {
			numShift=numShift<<1;
		}
		result+=numShift;
		if(dividend==divisor)break;
		divisor=divisor>>1;
			dividend=dividend-divisor;
		divisor=globalDivisor;
		}
	}

}
