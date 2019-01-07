package de.fh.albsig.digitalfactory.client;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttClientMain {

	public static void main(final String[] args) throws MqttException {
		final IMqttClient mqttClient = new MqttClient(
				String.format("tcp://%s:%s", MqttConstants.BROKER, MqttConstants.BROKER_PORT), MqttClient.generateClientId());

		final MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);

		mqttClient.connect(options);

		mqttClient.subscribe(MqttConstants.START_PROCESS, (topic, message) -> {
			System.out.println(new String(message.getPayload()));
		});
	}

}
