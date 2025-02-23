Hi There,
This is spring boot project with maven build in monolithic architectural style and can be easily converted to microservices architectured styled application. Also used default values in database which is one time
activity of creating and isnerting values into db. I have implemented flyway database scripts to have default value and visual very well.

To leverage the functionality please follow below steps to run the application and perform operations such as 
**[
1. Login, 
2. Sign Up,
3. See list of grocery items, 
4. Place order,
5. Add grocery items, 
6. Remove items, 
7. Modify items, 
8. Manage inventory
]**

Steps:
1. Download the repository to local machine.
2. Open maven project in Intellij Idea
3. Open maven section and download resources, load the maven, execute the maven goals [mvn clean compile install]
4. Then run the spring boot application by adding maven configuration [grocery-service spring-boot:run]
5. Now open the swagger documentation by hiting below link
6. Hit - 'http://localhost:9090/swagger-ui/index.html#/'
7. You will se following screens and perform the tasks accordingly.
8. You first need to authenticate
9. Second copy auth token from response and paste it in pop up comes when you click on the lock symbol on the right side of swagger documentation
10. Once you got athorized then you can perform below operations as mentioned above.
11. After authentication see the grocery item list by trying out and executing 'http://localhost:9090/api/user/grocery-items'
12. Then copy the items from response as is and place those in request body of place order and very important ajdust the inventory level of the items you want to buy from the original values. Here Inventory level
13. Inventory level is nothing but quantity of item you require
14. Once you decided items list then you can hit the execute button of 'http://localhost:9090/api/user/place-order'

some screenshots:

1. Hit - 'http://localhost:9090/swagger-ui/index.html#/'
      ![image](https://github.com/user-attachments/assets/c739707f-1225-466b-8337-2c1a905c5780)

2. Expand Authenticate yourself and sign in as a username: "user" and password: "user@123" as these are used default values
      ![image](https://github.com/user-attachments/assets/d1407775-08d1-4912-96b4-a05f2a9ae788)

3. Copy Token from response:
      ![image](https://github.com/user-attachments/assets/e36fac33-2080-4b07-8af4-b377e380ccb4)

4. Add the token in left side of the swagger document into mentioned pop up field of lock symbol
    ![image](https://github.com/user-attachments/assets/94fd7187-aa5c-4bdb-b46e-67e987bfbd3d)

5. And now hit get all grocery items url and response as below
    ![image](https://github.com/user-attachments/assets/12882857-36c9-4d33-8836-7d84526c659f)

6. copy the response list of items and paste same into place order's endpoint request body
    You will get below list
                                              [
                    {
                      "groceryItemId": 1,
                      "name": "Apple",
                      "price": 1.99,
                      "category": "Fruits",
                      "inventoryLevel": 58,
                      "description": "Fresh red apples"
                    },
                    {
                      "groceryItemId": 2,
                      "name": "Banana",
                      "price": 0.99,
                      "category": "Fruits",
                      "inventoryLevel": 138,
                      "description": "Ripe yellow bananas"
                    },
                    {
                      "groceryItemId": 3,
                      "name": "Milk",
                      "price": 2.49,
                      "category": "Dairy",
                      "inventoryLevel": 35,
                      "description": "Whole milk, 1 liter"
                    },
                    {
                      "groceryItemId": 4,
                      "name": "Bread",
                      "price": 1.49,
                      "category": "Bakery",
                      "inventoryLevel": 60,
                      "description": "Whole grain bread"
                    }
                  ]
7. You copy above list and put into requst body of 'http:localhost:9090/api/users/palce-order' endpoint by updating the inventoryLevel as per your requirement of quantity. and hit the execute button you will get
   below response:
                {
                "orderId": 8,
                "orderedItems": [
                  {
                    "name": "Apple",
                    "qty": 10,
                    "price": 1.99,
                    "totalPrice": 19.9
                  },
                  {
                    "name": "Banana",
                    "qty": 10,
                    "price": 0.99,
                    "totalPrice": 9.9
                  },
                  {
                    "name": "Milk",
                    "qty": 5,
                    "price": 2.49,
                    "totalPrice": 12.45
                  },
                  {
                    "name": "Bread",
                    "qty": 5,
                    "price": 1.49,
                    "totalPrice": 7.45
                  }
                ],
                "totalAmount": 49.7,
                "paymentStatus": "COMPLETED"
              }

9. you can see order placed.
10. Similarly other major functionalities can be acheive.


Thank you 
