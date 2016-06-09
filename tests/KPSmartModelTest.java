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
		model.logIn("1", "1234");
		MailProcessInput mail = new MailProcessInput("Wellington","Auckland", "4","4","International Air");
		assertTrue(model.processMail(mail).isEmpty());//empty value signals success
	}
	
	@Test
	public void processMail2() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		MailProcessInput mail = new MailProcessInput("123","Auckland", "4","4","International Air");
		assertFalse(model.processMail(mail).isEmpty());//empty value signals success
	}
	
	@Test
	public void processMail3() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		MailProcessInput mail = new MailProcessInput("Wellington","Auckland", "Four","4","International Air");
		assertFalse(model.processMail(mail).isEmpty());//empty value signals success
	}
	
	@Test
	public void processMail4() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		MailProcessInput mail = new MailProcessInput("Wellington","Auckland", "4","Four","International Air");
		assertFalse(model.processMail(mail).isEmpty());//empty value signals success
	}
	
	/**
	 * Add new route tests
	 */
	
	@Test
	public void addRoute1() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","4","4","4","4");
		assertTrue(model.addNewRoute(route).isEmpty());
	}
	
	@Test
	public void addRoute2() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","Four","4","4","4");
		assertFalse(model.addNewRoute(route).isEmpty());
	}
	
	@Test
	public void addRoute3() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","4","Four","4","4");
		assertFalse(model.addNewRoute(route).isEmpty());
	}
	@Test
	public void addRoute4() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","4","4","Four","4");
		assertFalse(model.addNewRoute(route).isEmpty());
	}
	@Test
	public void addRoute5() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","AIR","4","4","4","Four");
		assertFalse(model.addNewRoute(route).isEmpty());
	}
	
	@Test
	public void addRoute6() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","10","123","4","4","4","4");
		assertFalse(model.addNewRoute(route).isEmpty());
	}
	
	@Test
	public void addRoute7() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput route = new NewRouteInput("Wellington","Auckland","DHL","Ten","AIR","4","4","4","4");
		assertFalse(model.addNewRoute(route).isEmpty());
	}
	
	/**
	 * Discontinue route tests
	 */
	
	@Test
	public void disRouteSuccessScenario() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		DiscontinueInput disRoute = new DiscontinueInput("Wellington","Auckland","company","AIR");
		assertTrue(model.discontinueRoute(disRoute).isEmpty());
	}
	
	@Test
	public void disRouteBadTransportType() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		DiscontinueInput disRoute = new DiscontinueInput("Wellington","Auckland","company","LOL");
		assertFalse(model.discontinueRoute(disRoute).isEmpty());
	}
	
	@Test
	public void disRouteNonexistantCompanyName() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		DiscontinueInput disRoute = new DiscontinueInput("Wellington","Auckland","comp","AIR");
		assertFalse(model.discontinueRoute(disRoute).isEmpty());
	}
	
	/**
	 * Change customer price tests
	 */
	
	@Test
	public void changePriceSuccessScenario() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auckland","International Air","5","5");
		assertTrue(model.changeCustomerPrice(price).isEmpty());
	}
	
	@Test
	public void changePriceNonexistantDestination() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auck","International Air","5","5");
		System.out.println("about to call changeCustomerPrice from model");		
		assertFalse(model.changeCustomerPrice(price).isEmpty());
	}
	
	@Test
	public void changePriceBadNumberFormat() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auckland","International Air","five","5");
		assertFalse(model.changeCustomerPrice(price).isEmpty());
	}
	
	@Test
	public void changePriceBadNumberFormat2() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		CustomerPriceInput price = new CustomerPriceInput("Wellington","Auckland","International Air","5","five");
		assertFalse(model.changeCustomerPrice(price).isEmpty());
	}
	
	//we had a test to try to update customer price on a discontinued route, but new
	//factorisation updates customer prices on all routes origin to destination same type
	//could be multiples, can't isolate a single route.
	
	/**
	 * Change transport cost
	 */
	@Test
	public void changeTransPriceSuccessScenario() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		String routeError = model.addNewRoute(newRoute);
		System.out.println(routeError);
		
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","5","5","10");
		String errorMessage = model.changeTransportCost(cost);
		System.out.println(errorMessage);
		assertTrue(errorMessage.isEmpty());
	}
	
	
	@Test
	public void changeTransPriceBadNumberFormat() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","Five","5","10");
		assertFalse(model.changeTransportCost(cost).isEmpty());
	}
	
	@Test
	public void changeTransPriceBadNumberFormat2() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","5","five","10");
		assertFalse(model.changeTransportCost(cost).isEmpty());
	}
	
	@Test
	public void changeTransPriceBadNumberFormat3() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","5","5","ten");
		assertFalse(model.changeTransportCost(cost).isEmpty());
	}
	
	@Test
	public void changeTransPriceNegativeNumber() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","-1","5","10");
		assertFalse(model.changeTransportCost(cost).isEmpty());
	}
	
	@Test
	public void changeTransPriceNegativeNumber2() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","AIR","5","-1","10");
		assertFalse(model.changeTransportCost(cost).isEmpty());
	}
	@Test
	public void changeTransPriceBadTransportType() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auckland","company","LOL","5","5","10");
		assertFalse(model.changeTransportCost(cost).isEmpty());
	}
	
	@Test
	public void changeTransPriceNonexistantDestination() {
		KPSmartModel model = new KPSmartModel();
		model.logIn("1", "1234");
		NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","AIR","4","4","4","4");
		model.addNewRoute(newRoute);
		TransportCostInput cost = new TransportCostInput("Wellington","Auck","company","AIR","5","5","10");
		assertFalse(model.changeTransportCost(cost).isEmpty());
	}
	
	//should it be able to change the cost if there is no diff to the old one?
//	@Test
//	public void changeTransPrice9() {
//		KPSmartModel model = new KPSmartModel();
//  	model.logIn("1", "1234");
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
