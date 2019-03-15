package com.urca.das.storage;

import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import java.util.ArrayList;
import java.util.List;

import com.urca.das.entities.Pizza;

public class PizzaDatastore {
    private Datastore datastore;
    private KeyFactory keyFactory;

    private PizzaDatastore(){
        datastore = DatastoreOptions.getDefaultInstance().getService();
        keyFactory =datastore.newKeyFactory().setKind("Pizza");
    }

    private static PizzaDatastore INSTANCE = new PizzaDatastore();

    public static PizzaDatastore getInstance(){
        return INSTANCE;
    }

    public Long addPizza(Pizza pizza){
        IncompleteKey key = keyFactory.newKey();          // Key will be assigned once written
        FullEntity<IncompleteKey> incPizzaEntity = Entity.newBuilder(key)  // Create the Entity
            .set(Pizza.NAME, pizza.getName())
            .set(Pizza.IMAGE, pizza.getImage())
            .set(Pizza.DESCRIPTION, pizza.getDescription())
            .build();
        Entity pizzaEntity = datastore.add(incPizzaEntity); // Save the Entity
    return pizzaEntity.getKey().getId();
    }
    
    public Pizza getPizza(Long pizzaID){
        Entity pizzaEntity = datastore.get(keyFactory.newKey(pizzaID));
        return entityToPizza(pizzaEntity);
    }

    public void updatePizza(Pizza pizza){
        Key key = keyFactory.newKey(pizza.getId());
        Entity entity = Entity.newBuilder(key)
            .set(Pizza.NAME, pizza.getName())
            .set(Pizza.IMAGE, pizza.getImage())
            .set(Pizza.DESCRIPTION, pizza.getDescription())
            .build();
        datastore.update(entity);
    }

    public void RemovePizza(Long pizzaID){
        Key key = keyFactory.newKey(pizzaID);
        datastore.delete(key);
    }

    public List<Pizza> ListPizzas(){

        Query<Entity> query = Query.newEntityQueryBuilder()       // Build the Query
            .setKind("Pizza")
            .setOrderBy(OrderBy.asc(Pizza.NAME))
            .build();
        QueryResults<Entity> resultList = datastore.run(query);   // Run the query
        List<Pizza> resultPizzas = entitiesToPizzas(resultList);     // Retrieve and convert Entities
        return resultPizzas;
    }

//BUILDER
    public Pizza entityToPizza(Entity entity) {
        return new Pizza.Builder()                                     // Convert to Book form
            .name(entity.getString(Pizza.NAME))
            .image(entity.getString(Pizza.IMAGE))
            .description(entity.getString(Pizza.DESCRIPTION))
            .id(entity.getKey().getId())
            .build();
    }

    public List<Pizza> entitiesToPizzas(QueryResults<Entity> resultList) {
        List<Pizza> resultPizzas = new ArrayList<>();
        while (resultList.hasNext()) {  // We still have data
          resultPizzas.add(entityToPizza(resultList.next()));
        }
        return resultPizzas;
    }
}