package generactive.IO;

import generactive.Repository.GroupRepository;
import generactive.model.Item;
import generactive.model.StockItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVreader {
    public static List<Item> readItems(String filename, GroupRepository repo){
        List<Item> items=new ArrayList<>();
        Path pathToFile= Paths.get(filename);
        try(BufferedReader br= Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)){
            String line=br.readLine();
            while(line!=null){
                String [] attributes=line.split(",");
                Item item=createItem(attributes, repo);
                items.add(item);
                line=br.readLine();
            }
        }
        catch (IOException ioe) {
            throw new AppRuntimeException();
        }
        return items;
    }
    private static StockItem createItem(String[] metadata, GroupRepository repo){
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
