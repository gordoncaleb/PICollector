package pi.job;

import java.util.Date;

public class Job {

	private Long id;
	private String position;
	private String title;
	private String organization;
	private Date startDate;
	private Date endDate;
	private Double yearsEmployed;
	private Double salery;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getYearsEmployed() {
		return yearsEmployed;
	}

	public void setYearsEmployed(Double yearsEmployed) {
		this.yearsEmployed = yearsEmployed;
	}

	public Double getSalery() {
		return salery;
	}

	public void setSalery(Double salery) {
		this.salery = salery;
	}

}
