package com.exam.controller;

public class ActionForward {
	private String path;//null
	private boolean isRedirect;//false ->dispatch 이동. true -> redirect 이동.
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}
