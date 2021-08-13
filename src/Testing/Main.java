import generactive.Repository.ItemRepository;
import generactive.mock.ItemMock;
import generactive.model.Item;
import generactive.model.StockItem;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class Main {

    @BeforeEach
    public void clear(){
        ItemRepository repo=ItemRepository.getInstance();
        repo.clear();
    }
    @Test
    public void testCreate() {
        System.out.println("Running testCreate...");
        ItemRepository repo=ItemRepository.getInstance();
        Item item=new StockItem(1,300, "Big Car");
        Item item2=repo.addItem(item);

        assertTrue(repo.getSize()>0);
        assertEquals(item, item2);
    }
    @Test
    public void updateItem() throws Exception{
        ItemRepository repo=ItemRepository.getInstance();
        Item item=new StockItem(1,300,"Funny Duck");
        repo.addItem(item);
        item.setBasePrice(400);
        assertNotEquals(300, repo.findItemById(1).getBasePrice());
    }
    @Test
    public void testDelete () {
        ItemRepository repo=ItemRepository.getInstance();
        Item item=new StockItem(1,300,"Funny Duck");
        repo.addItem(item);
        repo.deleteItemById(1);
        assertNull(repo.existsById(1), "the item does not exist");
    }
    @Test
    public void testRead () {
        ItemRepository repo=ItemRepository.getInstance();
        Item item=new StockItem(1,300,"Funny Duck");
        repo.addItem(item);
        assertEquals("Funny Duck", item.getName());
    }


}
