import generactive.Repository.GroupRepository;
import generactive.Repository.ItemRepository;
import generactive.mock.ItemMock;
import generactive.model.Group;
import generactive.model.Item;
import generactive.model.StockItem;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static generactive.IO.CSVreader.readItems;
import static org.junit.jupiter.api.Assertions.*;

public class Main {

    @BeforeEach
    public void clear() {
        ItemRepository.getInstance().clear();
    }

    @Test
    public void csvItemTest() {
        Group test1 = new Group(1, "generactive.main.Test 1 group");
        Group test2 = new Group(2, "generactive.main.Test 2 group");
        Group test3 = new Group(3, "generactive.main.Test 3 group");
        GroupRepository groupRepository = GroupRepository.getInstance();
        List<Group> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);
        list.add(test3);
        groupRepository.addGroupAll(list);
        ItemRepository itemRepository = ItemRepository.getInstance();
        String fileName = "c:\\Users\\elenm\\Downloads\\item.csv";
        List<Item> items = readItems("c:\\Users\\elenm\\Downloads\\item.csv", groupRepository);
        for (Item item : items) {
            itemRepository.addItem(item);
        }
        StockItem temp = new StockItem(2, 200, "Test2");
        temp.setImageUrl("https://test.test/image/1");
        temp.setGroup(test1);
        assertEquals(itemRepository.findItemById(1), temp);
    }

    @Test
    public void createItemTest() {
        System.out.println("Running testCreate...");
        ItemRepository repo = ItemRepository.getInstance();
        Item item = new StockItem(1, 300, "Big Car");
        Item item2 = repo.addItem(item);
        assertTrue(repo.getSize() > 0);
        assertEquals(item, item2);
    }

    @Test
    public void updateItemTest() throws Exception {
        ItemRepository repo = ItemRepository.getInstance();
        Item item = new StockItem(1, 300, "Funny Duck");
        repo.addItem(item);
        item.setBasePrice(400);
        assertNotEquals(300, repo.findItemById(1).getBasePrice());
    }

    @Test
    public void deleteItemTest() {
        ItemRepository repo = ItemRepository.getInstance();
        Item item = new StockItem(1, 300, "Funny Duck");
        repo.addItem(item);
        repo.deleteItemById(1);
        assertNull(repo.existsById(1), "the item does not exist");
    }

    @Test
    public void readItemTest() {
        ItemRepository repo = ItemRepository.getInstance();
        Item item = new StockItem(1, 300, "Funny Duck");
        repo.addItem(item);
        assertEquals("Funny Duck", item.getName());
    }


}
