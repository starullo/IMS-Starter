Coverage: 80.2%
# Inventory Management System

An application that allows users to interact with a database through a Command-Line Interface. Users can perform various operations on customers, items, and order entities in the database.

## Getting Started

The project is packaged with it's dependencies in a jar file (ims-0.0.1-jar-with-dependencies.jar) located in the IMS-Starter/documents directory. Open up a terminal and (from the root directory of the project) enter the command
```
java -jar documents/ims-0.0.1-jar-with-dependencies.jar
```
to start the application.

### Prerequisites

You need Java installed on your computer.

```
https://java.com/en/
```

### Installing

If you're able to use Java in your Command-Line Interface, simply enter the command (from the root directory of the project)

```
java -jar documents/ims-0.0.1-jar-with-dependencies.jar
```

to start the application. You can also run the application from Eclipse, or any IDE that can be used with Java by "running" the IMS-Starter/src/main/java directory in the project itself.

Once the application is running, you can select from a selection of entities to perform operations on

```
Which entity would you like to use?
CUSTOMER: Information about customers
ITEM: Individual Items
ORDER: Purchases of items
STOP: To close the application
```

You can view, add, update, or delete customers
```
What would you like to do with customer:
CREATE: To save a new entity into the database
READ: To read an entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
```

You can view, add, update, or delete items

```
What would you like to do with item:
CREATE: To save a new entity into the database
READ: To read an entity from the database
UPDATE: To change an entity already in the database
DELETE: To remove an entity from the database
RETURN: To return to domain selection
```

You can view, add, update, or delete orders

```
What would you like to do with order:
CREATE: To add a new order into the database
READ: To read orders in the database
UPDATE: To change an order in the database
DELETE: To remove an order from the database
ADD: To add an item to an order in the database
TOTAL: To get the total cost for an order in the database
ITEMS: To read all items in an order in the database
REMOVE: To remove an item from an order in the database
RETURN: To return to domain selection
```
As well as add a specific item to an order,

```
add
Please enter the id of the order you'd like to add to
1
Please enter the id of the item you'd like to add to the order
3
Please enter the quantity of the item you'd like to add to the order
1
Item added to order
```

remove a specific item from an order,

```
remove
Please enter the id of the order you'd like to modify
1
Please enter the id of the item you'd like to remove from the order
4
```

and get the cost of an order

```
total
Please enter the order id to view the total
1
id: 1, customerId: 1, orderedOn: 2021-04-25
$39.93
```


## Running the tests

All tests are located in the IMS-Starter/src/test/java directory. They can be "run" with your IDE with or without coverage.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Sam Tarullo** - [starullo](https://github.com/starullo)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* Thank you to Alan, Alex, and Ed for all your help!
