package de.fh.albsig.digitalfactory.connector;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;

import de.fh.albsig.digitalfactory.connector.model.Task;


public final class TaskSchedule
{

	private final JCoDestination destination;
	private final JCoFunction function;


	public TaskSchedule()
		throws JCoException
	{
		this.destination = JCoDestinationManager.getDestination("src/main/resources/ConProperties");
		this.destination.ping();

		final JCoRepository repository = this.destination.getRepository();

		this.function = repository.getFunction("Z_NEXTOP_BYORDER1");
	}


	public Task nextTask(final String orderNumber)
		throws JCoException
	{
		final JCoParameterList input = this.function.getImportParameterList();
		input.setValue("I_AUFNR", orderNumber);

		this.function.execute(this.destination);

		final JCoParameterList values = this.function.getExportParameterList();

		return this.parseTask(values);
	}


	private Task parseTask(final JCoParameterList values)
	{
		final Task task = new Task(
			values.getString("E_VORNR"),
			values.getString("E_DESCRIPTION"),
			values.getDouble("E_TEMP"),
			values.getLong("E_DAUER"),
			Boolean.valueOf(values.getString("E_SWITCH1")),
			Boolean.valueOf(values.getString("E_SWITCH2"))
		);
		return task;
	}

}
