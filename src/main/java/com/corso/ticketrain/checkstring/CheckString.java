package com.corso.ticketrain.checkstring;

public abstract class CheckString {
	
	private CheckString next;
	
	public abstract boolean check(String s1, String s2);
	
	public CheckString setCheckString(CheckString next) {
		this.next = next;
		return next;
	}
	
	protected boolean handleNext(String s1, String s2) {
		if (next == null) {
			return true;
		}
		return next.check(s1, s2);
	}
	
	public CheckString getNext() {
	    return next;
	}

}
