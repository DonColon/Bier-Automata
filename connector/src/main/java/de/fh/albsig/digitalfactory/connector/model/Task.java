package de.fh.albsig.digitalfactory.connector.model;

import java.util.Objects;


public final class Task
{

	private final String transactionKey;
	private final String description;

	private final double targetTemperature;
	private final long duration;

	private final boolean stirrerEnabled;
	private final boolean heaterEnabled;


	public Task(final String transactionKey, final String description,
				final double targetTemperature, final long duration,
				final boolean stirrerEnabled, final boolean heaterEnabled)
	{
		this.transactionKey = transactionKey;
		this.description = description;
		this.targetTemperature = targetTemperature;
		this.duration = duration;
		this.stirrerEnabled = stirrerEnabled;
		this.heaterEnabled = heaterEnabled;
	}


	@Override
	public String toString()
	{
		return "Task [transactionKey=" + this.transactionKey
				+ ", description=" + this.description
				+ ", targetTemperature="+ this.targetTemperature
				+ ", duration=" + this.duration
				+ ", stirrerEnabled=" + this.stirrerEnabled
				+ ", heaterEnabled=" + this.heaterEnabled + "]";
	}

	@Override
	public boolean equals(final Object object)
	{
		if(object == null) return false;
		if(this == object) return true;

		if(this.getClass() != object.getClass())
			return false;

		final Task task = (Task) object;
		return Objects.equals(this.transactionKey, task.getTransactionKey())
			&& Objects.equals(this.description, task.getDescription())
			&& Objects.equals(this.targetTemperature, task.getTargetTemperature())
			&& Objects.equals(this.duration, task.getDuration())
			&& Objects.equals(this.stirrerEnabled, task.isStirrerEnabled())
			&& Objects.equals(this.heaterEnabled, task.isHeaterEnabled());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.transactionKey, this.description,
							this.targetTemperature, this.duration,
							this.stirrerEnabled, this.heaterEnabled);
	}


	public String getTransactionKey()
	{
		return this.transactionKey;
	}

	public String getDescription()
	{
		return this.description;
	}

	public double getTargetTemperature()
	{
		return this.targetTemperature;
	}

	public long getDuration()
	{
		return this.duration;
	}

	public boolean isStirrerEnabled()
	{
		return this.stirrerEnabled;
	}

	public boolean isHeaterEnabled()
	{
		return this.heaterEnabled;
	}

}
