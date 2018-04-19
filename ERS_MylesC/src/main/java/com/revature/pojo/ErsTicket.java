package com.revature.pojo;

public class ErsTicket {
	private int reim_id;
	private double reim_amount;
	private String reim_submitted;
	private String reim_resolved;
	private String reim_descript;
	private Object reim_receipt;
	private int reim_author_id;
	private int reim_resolver_id;
	private int reim_status_id;
	private String reim_status;
	private int reim_type_id;
	private String reim_type;
	private String reim_author_name;
	
	public ErsTicket() {}
	
	public ErsTicket(int reim_id, double reim_amount, String reim_submitted, String reim_resolved,
					String reim_descript, Object reim_receipt, int reim_author_id, int reim_resolver_id,
					int reim_status_id, String reim_status,
					int reim_type_id, String reim_type, String reim_author_name) {
		super();
		this.reim_id = reim_id;
		this.reim_amount = reim_amount;
		this.reim_submitted = reim_submitted;
		this.reim_resolved = reim_resolved;
		this.reim_descript = reim_descript;
		this.reim_receipt = reim_receipt;
		this.reim_author_id = reim_author_id;
		this.reim_resolver_id = reim_resolver_id;
		this.reim_status_id = reim_status_id;
		this.reim_type_id = reim_type_id;
		this.reim_status = reim_status;
		this.reim_type = reim_type;
		this.reim_author_name = reim_author_name;
	}

	public int getReim_id() {
		return reim_id;
	}

	public void setReim_id(int reim_id) {
		this.reim_id = reim_id;
	}

	public double getReim_amount() {
		return reim_amount;
	}

	public void setReim_amount(double reim_amount) {
		this.reim_amount = reim_amount;
	}

	public String getReim_submitted() {
		return reim_submitted;
	}

	public void setReim_submitted(String reim_submitted) {
		this.reim_submitted = reim_submitted;
	}

	public String getReim_resolved() {
		return reim_resolved;
	}

	public void setReim_resolved(String reim_resolved) {
		this.reim_resolved = reim_resolved;
	}

	public String getReim_descript() {
		return reim_descript;
	}

	public void setReim_descript(String reim_descript) {
		this.reim_descript = reim_descript;
	}

	public Object getReim_receipt() {
		return reim_receipt;
	}

	public void setReim_receipt(Object reim_receipt) {
		this.reim_receipt = reim_receipt;
	}

	public int getReim_author_id() {
		return reim_author_id;
	}

	public void setReim_author_id(int reim_author_id) {
		this.reim_author_id = reim_author_id;
	}

	public int getReim_resolver_id() {
		return reim_resolver_id;
	}

	public void setReim_resolver_id(int reim_resolver_id) {
		this.reim_resolver_id = reim_resolver_id;
	}
	
	public int getReim_status_id() {
		return reim_status_id;
	}

	public void setReim_status_id(int reim_status_id) {
		this.reim_status_id = reim_status_id;
	}

	public String getReim_status() {
		return reim_status;
	}

	public void setReim_status(String reim_status) {
		this.reim_status = reim_status;
	}

	public int getReim_type_id() {
		return reim_type_id;
	}

	public void setReim_type_id(int reim_type_id) {
		this.reim_type_id = reim_type_id;
	}

	public String getReim_type() {
		return reim_type;
	}

	public void setReim_type(String reim_type) {
		this.reim_type = reim_type;
	}
	public String getReim_author_name() {
		return reim_author_name;
	}
	public void setReim_author_name(String reim_author_name) {
		this.reim_author_name = reim_author_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reim_amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reim_author_id;
		result = prime * result + ((reim_author_name == null) ? 0 : reim_author_name.hashCode());
		result = prime * result + ((reim_descript == null) ? 0 : reim_descript.hashCode());
		result = prime * result + reim_id;
		result = prime * result + ((reim_receipt == null) ? 0 : reim_receipt.hashCode());
		result = prime * result + ((reim_resolved == null) ? 0 : reim_resolved.hashCode());
		result = prime * result + reim_resolver_id;
		result = prime * result + ((reim_status == null) ? 0 : reim_status.hashCode());
		result = prime * result + reim_status_id;
		result = prime * result + ((reim_submitted == null) ? 0 : reim_submitted.hashCode());
		result = prime * result + ((reim_type == null) ? 0 : reim_type.hashCode());
		result = prime * result + reim_type_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErsTicket other = (ErsTicket) obj;
		if (Double.doubleToLongBits(reim_amount) != Double.doubleToLongBits(other.reim_amount))
			return false;
		if (reim_author_id != other.reim_author_id)
			return false;
		if (reim_author_name == null) {
			if (other.reim_author_name != null)
				return false;
		} else if (!reim_author_name.equals(other.reim_author_name))
			return false;
		if (reim_descript == null) {
			if (other.reim_descript != null)
				return false;
		} else if (!reim_descript.equals(other.reim_descript))
			return false;
		if (reim_id != other.reim_id)
			return false;
		if (reim_receipt == null) {
			if (other.reim_receipt != null)
				return false;
		} else if (!reim_receipt.equals(other.reim_receipt))
			return false;
		if (reim_resolved == null) {
			if (other.reim_resolved != null)
				return false;
		} else if (!reim_resolved.equals(other.reim_resolved))
			return false;
		if (reim_resolver_id != other.reim_resolver_id)
			return false;
		if (reim_status == null) {
			if (other.reim_status != null)
				return false;
		} else if (!reim_status.equals(other.reim_status))
			return false;
		if (reim_status_id != other.reim_status_id)
			return false;
		if (reim_submitted == null) {
			if (other.reim_submitted != null)
				return false;
		} else if (!reim_submitted.equals(other.reim_submitted))
			return false;
		if (reim_type == null) {
			if (other.reim_type != null)
				return false;
		} else if (!reim_type.equals(other.reim_type))
			return false;
		if (reim_type_id != other.reim_type_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErsTicket [reim_id=" + reim_id + ", reim_amount=" + reim_amount + ", reim_submitted=" + reim_submitted
				+ ", reim_resolved=" + reim_resolved + ", reim_descript=" + reim_descript + ", reim_receipt="
				+ reim_receipt + ", reim_author_id=" + reim_author_id + ", reim_resolver_id=" + reim_resolver_id
				+ ", reim_status_id=" + reim_status_id + ", reim_status=" + reim_status + ", reim_type_id="
				+ reim_type_id + ", reim_type=" + reim_type + ", reim_author_name=" + reim_author_name + "]";
	}

	
	
	
}
