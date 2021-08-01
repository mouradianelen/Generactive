package model;

import model.Configuration;
import model.Item;

public class StockItem extends Item {

    public StockItem(int id, int basePrice, String name) {
        super(id, basePrice, name);
    }

    @Override
    public int calculatePrice(Configuration configuration) {
        return getBasePrice() * configuration.getResolution().getCoefficient();
    }
}
