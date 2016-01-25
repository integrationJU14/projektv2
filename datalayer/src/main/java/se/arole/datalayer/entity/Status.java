package se.arole.datalayer.entity;

public enum Status {

	TO_DO("TO_DO"), ASSIGNED("ASSIGNED"), DONE("DONE"), ACCEPTED("ACCEPTED");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * Will return the Status enum that matches the parameter String status.
	 * 
	 * @param status
	 *            needs to match one of the existing Enums String values
	 *            otherwise an IllegalArgumentException will be thrown.
	 * @return
	 */
	public static Status fromString(String status) {
		for (Status s : Status.values()) {
			if (s.getStatus().equalsIgnoreCase(status)) {
				return s;
			}
		}
		throw new IllegalArgumentException("No status with name " + status + " exists");
	}
}
