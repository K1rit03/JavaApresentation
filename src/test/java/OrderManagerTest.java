import org.example.NotificationService;
import org.example.Order;
import org.example.OrderManager;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderManagerTest {

    private OrderManager orderManager;
    private NotificationService notificationService;

    @Before
    public void setUp() {
        notificationService = mock(NotificationService.class);
        orderManager = new OrderManager(notificationService);
    }

    @Test
    public void testAddOrder() {
        Order order = new Order("1", "John Doe", 100.0);
        orderManager.addOrder(order);
        assertEquals(1, orderManager.getAllOrders().size());
        verify(notificationService).notify("Order added: 1");
    }

    @Test
    public void testSearchById() {
        Order order1 = new Order("1", "John Doe", 100.0);
        Order order2 = new Order("2", "Jane Doe", 200.0);
        orderManager.addOrder(order1);
        orderManager.addOrder(order2);

        Order result = orderManager.searchById("1");
        assertNotNull(result);
        assertEquals("John Doe", result.getCustomerName());
    }

    @Test
    public void testSearchByCustomerName() {
        Order order1 = new Order("1", "Jane Doe", 100.0);
        Order order2 = new Order("2", "Jane Doe", 200.0);
        orderManager.addOrder(order1);
        orderManager.addOrder(order2);

        List<Order> result = orderManager.searchByCustomerName("Jane Doe");
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("2", result.get(1).getId());
    }

    @Test
    public void testRemoveOrder() {
        Order order = new Order("1", "John Doe", 100.0);
        orderManager.addOrder(order);
        assertTrue(orderManager.removeOrder("1"));
        assertEquals(0, orderManager.getAllOrders().size());
    }
}