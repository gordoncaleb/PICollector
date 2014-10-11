package pi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pi.contact.EmailAddress;
import pi.contact.Phone;
import pi.job.Job;
import pi.name.Name;
import util.IOUtils;
import util.StringUtils;

public class Person {

	private Long id;

	private Date updated;

	private Name firstName;
	private Name middleName;
	private Name lastName;
	private List<Name> otherNames = new ArrayList<Name>();
	private List<EmailAddress> emailAddresses = new ArrayList<EmailAddress>();
	private List<Phone> phones = new ArrayList<Phone>();
	private List<Job> jobs = new ArrayList<Job>();

	public Person() {
		super();
	}

	public Name getFirstName() {
		return firstName;
	}

	public void setFirstName(Name firstName) {
		this.firstName = firstName;
	}

	public Name getLastName() {
		return lastName;
	}

	public void setLastName(Name lastName) {
		this.lastName = lastName;
	}

	public Name getMiddleName() {
		return middleName;
	}

	public void setMiddleName(Name middleName) {
		this.middleName = middleName;
	}

	public List<Name> getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(List<Name> otherNames) {
		this.otherNames = otherNames;
	}

	public void addOtherName(String name) {
		this.otherNames.add(new Name(name));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public void addJob(Job j) {
		this.jobs.add(j);
	}

	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public void addEmailAddress(EmailAddress email) {
		this.emailAddresses.add(email);
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone phone) {
		this.phones.add(phone);
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public boolean nameMatch(List<String> names) {
		if (names.isEmpty()) {
			return false;
		}

		List<String> nameListA = new ArrayList<String>();

		if (firstName != null) {
			nameListA.add(firstName.getName());
		}

		if (middleName != null) {
			nameListA.add(middleName.getName());
		}

		if (lastName != null) {
			nameListA.add(lastName.getName());
		}

		List<String> nameListB = new ArrayList<String>();
		nameListB.addAll(names);

		StringUtils.removeMatchesIgnoreCase(nameListA, nameListB);

		if (nameListB.isEmpty()) {
			return true;
		} else {

			List<String> initialsB = new ArrayList<String>();
			for (String b : nameListB) {
				if (b.length() == 1) {
					initialsB.add(b);
				}
			}

			if (!initialsB.isEmpty()) {

				List<String> initialsA = new ArrayList<String>();
				for (String a : nameListA) {
					initialsA.add(a.substring(0, 1));
				}

				StringUtils.removeMatchesIgnoreCase(initialsA, initialsB);

				if (initialsB.isEmpty()) {
					return true;
				} else {
					return false;
				}

			}
		}

		return false;

	}

	public String csvLine() {

		String email = emailAddresses.isEmpty() ? "" : emailAddresses.get(0).getFullAddress();
		String phone = phones.isEmpty() ? "" : phones.get(0).getNumber();
		String org = jobs.isEmpty() ? "" : jobs.get(0).getOrganization();
		String salery = jobs.isEmpty() ? "" : jobs.get(0).getSalery() + "";

		return this.getFirstName().getName() + "," + this.getMiddleName().getName() + "," + this.getLastName().getName() + "," + email + "," + phone
				+ "," + org + "," + salery;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", otherNames="
				+ otherNames + ", emailAddresses=" + emailAddresses + ", phones=" + phones + ", jobs=" + jobs + "]";
	}

	public static void writeCsvFile(String fileName, List<Person> people) {
		for (Person p : people) {
			IOUtils.writeToFile(p.csvLine() + System.lineSeparator(), fileName, true);
		}
	}

}
