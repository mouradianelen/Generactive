package generactive.main;

import generactive.Repository.GroupRepository;
import generactive.Repository.ItemRepository;
import generactive.model.Group;
import generactive.model.Item;
import java.util.ArrayList;
import java.util.List;
import static generactive.IO.CSVreader.readItems;

public class CSVTest {
    public static void main(String[] args) {
        Group test1=new Group(1,"generactive.main.Test 1 group");
        Group test2=new Group(2,"generactive.main.Test 2 group");
        Group test3=new Group(3,"generactive.main.Test 3 group");
        GroupRepository groupRepository = GroupRepository.getInstance();
        List<Group> list=new ArrayList<>();
        list.add(test1);
        list.add(test2);
        list.add(test3);
        groupRepository.addGroupAll(list);
        ItemRepository itemRepository=ItemRepository.getInstance();
        String fileName = "c:\\Users\\elenm\\Downloads\\item.csv";
        List<Item> items = readItems("c:\\Users\\elenm\\Downloads\\item.csv",groupRepository);
        for(Item item:items){
            itemRepository.addItem(item);
        }

        for(Item item:items){
            item.print();
            Group parent=item.getGroup();
            parent.print(0);
        }
    }
}
