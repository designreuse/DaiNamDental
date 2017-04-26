package com.datawings.app.filter;

public class CustomerFiler extends BaseFilter {
	public String serial;
	public String name;
	public String telephone;
	public String dentist;
	public String dateStartFrom;
	public String dateStartTo;
	public String status;
	public String branch;
	private String type;

	public String serialCreate;
	public String nameCreate;
	public String telephoneCreate;
	public String dentistCreate;
	public String dateStartCreate;
	public String statusCreate;
	private String typeCreate;
	private String sexCreate;
	private String addressCreate;
	private String emailCreate;
	private String causeCreate;
	private String contentCreate;
	private String dateBirthCreate;
	private String noteCreate;

	public CustomerFiler() {
		init();
	}

	public void init() {
		super.init();
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getNoteCreate() {
		return noteCreate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNoteCreate(String noteCreate) {
		this.noteCreate = noteCreate;
	}

	public String getDateBirthCreate() {
		return dateBirthCreate;
	}

	public void setDateBirthCreate(String dateBirthCreate) {
		this.dateBirthCreate = dateBirthCreate;
	}

	public String getTypeCreate() {
		return typeCreate;
	}

	public void setTypeCreate(String typeCreate) {
		this.typeCreate = typeCreate;
	}

	public String getSexCreate() {
		return sexCreate;
	}

	public void setSexCreate(String sexCreate) {
		this.sexCreate = sexCreate;
	}

	public String getAddressCreate() {
		return addressCreate;
	}

	public void setAddressCreate(String addressCreate) {
		this.addressCreate = addressCreate;
	}

	public String getEmailCreate() {
		return emailCreate;
	}

	public void setEmailCreate(String emailCreate) {
		this.emailCreate = emailCreate;
	}

	public String getCauseCreate() {
		return causeCreate;
	}

	public void setCauseCreate(String causeCreate) {
		this.causeCreate = causeCreate;
	}

	public String getContentCreate() {
		return contentCreate;
	}

	public void setContentCreate(String contentCreate) {
		this.contentCreate = contentCreate;
	}

	public String getSerialCreate() {
		return serialCreate;
	}

	public void setSerialCreate(String serialCreate) {
		this.serialCreate = serialCreate;
	}

	public String getNameCreate() {
		return nameCreate;
	}

	public void setNameCreate(String nameCreate) {
		this.nameCreate = nameCreate;
	}

	public String getTelephoneCreate() {
		return telephoneCreate;
	}

	public void setTelephoneCreate(String telephoneCreate) {
		this.telephoneCreate = telephoneCreate;
	}

	public String getDentistCreate() {
		return dentistCreate;
	}

	public void setDentistCreate(String dentistCreate) {
		this.dentistCreate = dentistCreate;
	}

	public String getDateStartCreate() {
		return dateStartCreate;
	}

	public void setDateStartCreate(String dateStartCreate) {
		this.dateStartCreate = dateStartCreate;
	}

	public String getStatusCreate() {
		return statusCreate;
	}

	public void setStatusCreate(String statusCreate) {
		this.statusCreate = statusCreate;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDentist() {
		return dentist;
	}

	public void setDentist(String dentist) {
		this.dentist = dentist;
	}

	public String getDateStartFrom() {
		return dateStartFrom;
	}

	public void setDateStartFrom(String dateStartFrom) {
		this.dateStartFrom = dateStartFrom;
	}

	public String getDateStartTo() {
		return dateStartTo;
	}

	public void setDateStartTo(String dateStartTo) {
		this.dateStartTo = dateStartTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
