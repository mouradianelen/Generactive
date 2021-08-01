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
    private static List<Item> readItems(String filename,GroupRepository repo){
        List<Item> items=new ArrayList<>();
        Path pathToFile=Paths.get(filename);
        try(BufferedReader br=Files.newBufferedReader(pathToFile,StandardCharsets.US_ASCII)){
            String line=br.readLine();
            while(line!=null){
                String [] attributes=line.split(",");
                Item item=createItem(attributes, repo);
                items.add(item);
                line=br.readLine();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return items;
    }
    private static StockItem createItem(String[] metadata,GroupRepository repo){
        int id = Integer.parseInt(metadata[0]);
        int basePrice = Integer.parseInt(metadata[1]);
        String name = metadata[2];
        String imageUrl = metadata[3];
        StockItem item=new StockItem(id, basePrice,name);
        item.setImageUrl(imageUrl);
        if(repo.findGroupById(Integer.parseInt(metadata[4]))!=null){
            item.setGroup(repo.findGroupById(Integer.parseInt(metadata[4])));
        }
        return item;
    }
}
