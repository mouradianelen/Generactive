import Repository.ItemRepository;
import mock.ItemMock;
import model.BasketItem;
import model.Item;
import model.StockItem;
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
        List<Item> items = ItemMock.getItems(1);
        Item item=items.get(0);
        item.print();

        int id= item.getId();

        Assertions.assertTrue(id > 0);
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
