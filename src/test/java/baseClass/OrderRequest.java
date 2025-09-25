package baseClass;

import java.util.List;

public class OrderRequest {

	
	public List<orderDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<orderDetails> orders) {
		this.orders = orders;
	}

	private List<orderDetails> orders;

	
}
