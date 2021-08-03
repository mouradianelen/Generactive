import Repository.GroupRepository;
import Repository.ItemRepository;
import model.Group;
import model.Item;
import model.StockItem;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import IO.CSVreader;

import static IO.CSVreader.readItems;


public class CSVTest {
    public static void main(String[] args) {
        Group test1=new Group(1,"Test 1 group");
        Group test2=new Group(2,"Test 2 group");
        Group test3=new Group(3,"Test 3 group");
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
