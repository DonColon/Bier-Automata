package de.fh.albsig.digitalfactory.connector.model;

import java.util.Objects;

public final class Confirmation
{

	private String orderNumber;
	private boolean successful;


	public Confirmation() {}

	public Confirmation(final String orderNumber, final boolean successful)
	{
		this.orderNumber = orderNumber;
		this.successful = successful;
	}


	@Override
	public String toString()
	{
		return "Confirmation [orderNumber=" + this.orderNumber + ", successful=" + this.successful + "]";
	}

	@Override
	public boolean equals(final Object object)
	{
		if(object == null) return false;
		if(this == object) return true;

		if(this.getClass() != object.getClass())
			return false;

		final Confirmation confirmation = (Confirmation) object;
		return Objects.equals(this.orderNumber, confirmation.getOrderNumber())
			&& Objects.equals(this.successful, confirmation.isSuccessful());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.orderNumber, this.successful);
	}


	public String getOrderNumber()
	{
		return this.orderNumber;
	}

	public void setOrderNumber(final String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public boolean isSuccessful()
	{
		return this.successful;
	}

	public void setSuccessful(final boolean successful)
	{
		this.successful = successful;
	}

}
