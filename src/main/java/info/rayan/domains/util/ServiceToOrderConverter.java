package info.rayan.domains.util;

import info.rayan.domains.OrderItem;
import info.rayan.domains.Service;

public class ServiceToOrderConverter {
	public static OrderItem serviceToOrderItemConverter(Service service) {
		return new OrderItem(service.getName(), service.getPrice(),
				service.getDescription());
	}

	public static OrderItem serviceToOrderItemConverter(Service service,
			String description) {
		return new OrderItem(service.getName(), service.getPrice(), description);
	}
}
