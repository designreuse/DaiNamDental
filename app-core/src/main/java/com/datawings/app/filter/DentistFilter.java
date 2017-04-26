package com.datawings.app.filter;

public class DentistFilter extends BaseFilter {

	private String name;
	private String branch;
	private String note;

	private String nameCreate;
	private String branchCreate;
	private String noteCreate;

	private Integer idModify;
	private String nameModify;
	private String branchModify;
	private String noteModify;

	public DentistFilter() {
		init();
	}

	public void init() {
		super.init();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNameCreate() {
		return nameCreate;
	}

	public void setNameCreate(String nameCreate) {
		this.nameCreate = nameCreate;
	}

	public String getBranchCreate() {
		return branchCreate;
	}

	public void setBranchCreate(String branchCreate) {
		this.branchCreate = branchCreate;
	}

	public String getNoteCreate() {
		return noteCreate;
	}

	public void setNoteCreate(String noteCreate) {
		this.noteCreate = noteCreate;
	}

	public Integer getIdModify() {
		return idModify;
	}

	public void setIdModify(Integer idModify) {
		this.idModify = idModify;
	}

	public String getNameModify() {
		return nameModify;
	}

	public void setNameModify(String nameModify) {
		this.nameModify = nameModify;
	}

	public String getBranchModify() {
		return branchModify;
	}

	public void setBranchModify(String branchModify) {
		this.branchModify = branchModify;
	}

	public String getNoteModify() {
		return noteModify;
	}

	public void setNoteModify(String noteModify) {
		this.noteModify = noteModify;
	}

}
