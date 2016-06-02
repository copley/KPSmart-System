package tests;

import static org.junit.Assert.*;
import model.KPSmartModel;
import model.events.inputs.*;

import org.junit.Test;

public class KPSmartModelTest {
	
	/**
	 * Process mail tests
	 */
	
	@Test
	public void processMail1() {
		KPSmartModel model = new KPSmartModel();
		MailProcessInput mail = new MailProcessInput("Wellington","Auckland", "4","4","International Air");
		assertTrue(model.processMail(mail));
	}
	
	@Test
	public void processMail2() {
		KPSmartModel model = new KPSmartModel();
		MailProcessInput mail = new MailProcessInput("123","Auckland", "4","4","International Air");
		assertFalse(model.processMail(mail));
	}
	
	@Test
	public void processMail3() {
		KPSmartModel model = new KPSmartModel();
		MailProcessInput mail = new MailProcessInput("Wellington","Auckland", "Four","4","International Air");
		assertFalse(model.processMail(mail));
	}
	
	@Test
	public void processMail4() {
		KPSmartModel model = new KPSmartModel();
		MailProcessInput mail = new MailProcessInput("Wellington","Auckland", "4","Four","International Air");
		assertFalse(model.processMail(mail));
	}
	
	/**
	 * Add new route tests
	 */
	
	@Test
	public void addRoute1() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","4","4","4","4");
		assertTrue(model.addNewRoute(route));
	}
	
	@Test
	public void addRoute2() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","Four","4","4","4");
		assertFalse(model.addNewRoute(route));
	}
	
	@Test
	public void addRoute3() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","4","Four","4","4");
		assertFalse(model.addNewRoute(route));
	}
	@Test
	public void addRoute4() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","4","4","Four","4");
		assertFalse(model.addNewRoute(route));
	}
	@Test
	public void addRoute5() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","4","4","4","Four");
		assertFalse(model.addNewRoute(route));
	}
	
	@Test
	public void addRoute6() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","123","4","4","4","4");
		assertFalse(model.addNewRoute(route));
	}
	
	@Test
	public void addRoute7() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","Ten","AIR","4","4","4","4");
		assertFalse(model.addNewRoute(route));
	}
	
	/**
	 * Discontinue route tests
	 */
	
	@Test
	public void disRoute1() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		DiscontinueInput disRoute = new DiscontinueInput("Wellington","Auckland","company","AIR");
		assertTrue(model.discontinueRoute(disRoute));
	}
	
	@Test
	public void disRoute2() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		DiscontinueInput disRoute = new DiscontinueInput("Wellington","Auckland","company","LOL");
		assertFalse(model.discontinueRoute(disRoute));
	}
	
	@Test
	public void disRoute3() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		DiscontinueInput disRoute = new DiscontinueInput("Wellington","Auckland","comp","AIR");
		assertFalse(model.discontinueRoute(disRoute));
	}
	
	/**
	 * Change customer price tests
	 */
	
	@Test
	public void changePrice1() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auckland","International Air","5","5");
		assertTrue(model.changeCustomerPrice(price));
	}
	
	@Test
	public void changePrice2() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auck","International Air","5","5");
		assertFalse(model.changeCustomerPrice(price));
	}
	
	@Test
	public void changePrice3() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auckland","International Air","five","5");
		assertFalse(model.changeCustomerPrice(price));
	}
	
	@Test
	public void changePrice4() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auckland","International Air","5","five");
		assertFalse(model.changeCustomerPrice(price));
	}
	
	@Test
	public void changePrice5() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		DiscontinueInput disRoute = new DiscontinueInput("Wellington","Auckland","company","AIR");
		model.addNewRoute(newRoute);
		model.discontinueRoute(disRoute);
		//able to change a route that doesn't exist???
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auckland","International Air","5","5");
		assertFalse(model.changeCustomerPrice(price));
	}
	
	/**
	 * Change transport cost
	 */
	@Test
	public void changeTransPrice1() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","5","5","10");
		assertTrue(model.changeTransportCost(cost));
	}
	
	
	@Test
	public void changeTransPrice2() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","Five","5","10");
		assertFalse(model.changeTransportCost(cost));
	}
	
	@Test
	public void changeTransPrice3() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","5","five","10");
		assertFalse(model.changeTransportCost(cost));
	}
	
	@Test
	public void changeTransPrice4() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","5","5","ten");
		assertFalse(model.changeTransportCost(cost));
	}
	
	@Test
	public void changeTransPrice5() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","-1","5","10");
		assertFalse(model.changeTransportCost(cost));
	}
	
	@Test
	public void changeTransPrice6() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","5","-1","10");
		assertFalse(model.changeTransportCost(cost));
	}
	@Test
	public void changeTransPrice7() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","LOL","5","5","10");
		assertFalse(model.changeTransportCost(cost));
	}
	
	@Test
	public void changeTransPrice8() {
		KPSmartModel model = new KPSmartModel();
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auck","company","AIR","5","5","10");
		assertFalse(model.changeTransportCost(cost));
	}
	
	//should it be able to change the cost if there is no diff to the old one?
//	@Test
//	public void changeTransPrice9() {
//		KPSmartModel model = new KPSmartModel();
//		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
//		model.addNewRoute(newRoute);
//		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","4","4","10");
//		assertFalse(model.changeTransportCost(cost));
//	}
	
	/**
	 * login tests
	 */
//	@Test
//	public void login1() {
//		KPSmartModel model = new KPSmartModel();
//		String name = "1";
//		String password ="1234";
//		assertTrue(model.logIn(name, password));
//	}
}
