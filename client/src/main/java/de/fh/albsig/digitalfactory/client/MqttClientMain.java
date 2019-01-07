package de.fh.albsig.digitalfactory.client;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.fh.albsig.digitalfactory.connector.TaskSchedule;
import de.fh.albsig.digitalfactory.connector.model.Task;

public class MqttClientMain {

	public static void main(final String[] args) throws MqttException {
		final IMqttClient mqttClient = new MqttClient(
			String.format("tcp://%s:%s", MqttConstants.BROKER, MqttConstants.BROKER_PORT),
			MqttClient.generateClientId()
		);

		final MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);

		mqttClient.connect(options);

		mqttClient.subscribe(MqttConstants.START_PROCESS, (topic, message) -> {
			final String orderNumber = new String(message.getPayload());
			System.out.println(orderNumber);

			final TaskSchedule schedule = new TaskSchedule();
			final Task task = schedule.nextTask(orderNumber);
			System.out.println(task);

			final String json = new ObjectMapper().writeValueAsString(task);
			System.out.println(json);

			mqttClient.publish(MqttConstants.TO_SPS, json.getBytes(), 1, true);
		});


		System.out.println("Press enter to exit!");
		final Scanner s = new Scanner(System.in);
		s.nextLine();

		System.exit(0);
	}

}
