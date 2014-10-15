package com.gordoncaleb.persistence;

public class SequenceId {

	private String id;
	private Long sequenceNum;

	public SequenceId() {
		super();
	}

	public SequenceId(String id, Long sequenceNum) {
		super();
		this.id = id;
		this.sequenceNum = sequenceNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(Long sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

}
