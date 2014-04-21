package pi;

import java.util.List;

import persistence.PersistenceEntity;
import pi.contact.Contact;
import pi.job.Job;
import pi.name.Name;

public class Person implements PersistenceEntity {

	private Long id;

	private Name preferredName;
	private List<Name> names;
	private List<Contact> contactInfo;
	private List<Job> jobs;

	public Person() {
		super();
	}

	public Person(Long id, Name preferredName, List<Name> names,
			List<Contact> contactInfo, List<Job> jobs) {
		super();
		this.id = id;
		this.preferredName = preferredName;
		this.names = names;
		this.contactInfo = contactInfo;
		this.jobs = jobs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Name getPreferredName() {
		return preferredName;
	}

	public void setPreferredName(Name preferredName) {
		this.preferredName = preferredName;
	}

	public List<Name> getNames() {
		return names;
	}

	public void setNames(List<Name> names) {
		this.names = names;
	}

	public List<Contact> getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(List<Contact> contactInfo) {
		this.contactInfo = contactInfo;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", preferredName=" + preferredName
				+ ", names=" + names + ", contactInfo=" + contactInfo
				+ ", jobs=" + jobs + "]";
	}

}
