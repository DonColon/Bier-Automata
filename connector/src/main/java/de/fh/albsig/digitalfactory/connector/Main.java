package de.fh.albsig.digitalfactory.connector;

import com.sap.conn.jco.JCoException;

import de.fh.albsig.digitalfactory.connector.model.Task;


public class Main
{

	public static void main(final String[] args) throws JCoException
	{
		final TaskSchedule schedule = new TaskSchedule();
		final Task task = schedule.nextTask("000001000157");
		System.out.println(task);
	}

}
