package com.example.demo;

class Study {

	private StudyStatus status = StudyStatus.DRAFT; //기본값 설정
	
	private int limit;
	
	private String name;
	
	public Study(int limit, String name) {
		this.limit = limit;
		this.name = name;
	}
	
	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit은 0보다 커야 한다.");
		}
		this.limit = limit;
	}
	
	public StudyStatus getStatus() {
		return this.status;
	}

	public int getLimit() {
		return limit;
	}

	public String getName() {
		return name;
	}

}
