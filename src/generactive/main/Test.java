package generactive.main;

import generactive.Repository.GroupRepository;
import generactive.mock.ConfigurationMock;
import generactive.mock.GroupMock;
import generactive.mock.ItemMock;
import generactive.model.Basket;
import generactive.model.BasketItem;
import generactive.model.Group;
import generactive.model.Item;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Group> groups = GroupMock.getGroupsHierarchy();
        List<Item> items = ItemMock.getItems(4);

        groups.get(groups.size() - 1).addItems(items);

        GroupRepository groupRepository = GroupRepository.getInstance();
        groupRepository.addGroupAll(groups);

        for (Group group : groupRepository.getGroupsHierarchy()) {
            group.print(0);
        }

        Basket basket = new Basket();
        for (Item item : items) {
            basket.add(new BasketItem(item, ConfigurationMock.getConfiguration()));
        }

        basket.print();
        System.out.println("");
        System.out.println("-------API test-------");
        List<BasketItem> list=basket.findHighestPricedItems();
        for(BasketItem basketItem : list) {
            basketItem.print();
        }
        System.out.println("----------TEST 2--------");

        List<BasketItem> list2=basket.findByName("A");
        for(BasketItem basketItem : list2) {
            basketItem.print();
        }
        System.out.println("----------TEST 3--------");

        List<BasketItem> list3=basket.findById(7);
        for(BasketItem basketItem : list3) {
            basketItem.print();
        }

    }





}
