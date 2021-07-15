# Product Management Application
This is a simple product management application. This project contains everything you need. It includes
1. `frontend/gocity-product-management`: React SPA
2. `backend/gocity`: Spring Boot application which contains two sets of REST API, **Product API** and **Category API**.
3. `backend/gocity-database`: MySQL Database configuration

---

## Table of Contents
1. [Technologies Used](#technologies)
2. [Folder Structure](#folder)
3. [User Interface](#ui)
4. [System Architecture and Data Model ](#system)
5. [Frontend Portal File Structure](#frontend)
6. [Backend Service File Structure](#backend)
7. [Category API](#category)
8. [Product API](#product)
9. [How to Run](#how)
10. [Future Development](#future)
---

## Technologies Used <a name="technologies"></a>

### Backend
1. Java 11
2. Spring Boot 2.5.2
3. Gradle 7.1.1

### Frontend
1. Node.js v12.14.1
2. React v17.0.2

### Database
1. MySQL 5.7

### Infrastructures
1. Docker
2. Docker Compose

---

## Folder structure <a name="folder"></a>

The following shows the structure of this folder. There are 3 subfolders:
1. backend

    (a) **gocity**: Backend service for this application. It contains all the source code, Dockerfile, docker-compose files, etc.  
    (b) **gocity-database**: It contains a docker-compose file of the database.

2. doc: All the assets related to this document.

3. frontend

    (a) **gocity-product-management**: React project for this application.


```
.
├── README.md
├── backend
│   ├── gocity
│   └── gocity-database
├── doc
│   ├── create-product.png
│   ├── gocity-architecture-diagram.png
│   ├── gocity-er-diagram.png
│   ├── landing.png
│   ├── product-details.png
│   └── product-list.png
└── frontend
    └── gocity-product-management
```

---
## User Interface <a name="ui"></a>

The following shows the user interface for this application.

### Landing Page

* path: `/`

![Alt text](doc/landing.png "Landing Page")


### Product List

* path: `/secure/products`

![Alt text](doc/product-list.png "Product List")


### Product Details

* path: `/secure/products/:id`

![Alt text](doc/product-details.png "Product Details ")


### Create Product

* path: `/secure/products/create`

![Alt text](doc/create-product.png "Create Product")

---

## System Architecture and Data Model <a name="system"></a>
The following picture shows the high level system architecture. Product management service (this service) manage the product data and product categories. Product management service exposes the `Product API` and `Category API` to the frontend portals. Data is stored in a MySQL database.

![Alt text](doc/gocity-architecture-diagram.png "Architecture Diagram")

In our scenario, we have to types of data, `category` and `product`. Since each `product` must have a `category`, there is a **one-to-many relationship between `product` and `category`. Because of this relationship, I decided to use a relational database, MySQL, as our data persistence layer. The following picture is the entity-relationship diagram for our database.

![Alt text](doc/gocity-er-diagram.png "ER Diagram")

Each table has a `id`, which is the primary key. The `product` table has a foreign key (**FK1** in the picture) on column `category_id`, which is referencing the `id` in table `category`.

---

## Frontend Portal File Structure <a name="frontend"></a>

The fronend portal is built using React. The following shows the file structure of the application. All the React source code is stored under the `src/main` folder. In most applications, authentication is required in order to gain access to the service(s). Therefore, I organize the source code as follows:

1. `src/main/common`: Stores the common components used across the application.
2. `src/main/public`: Stores the components used in public pages, which does not require authentication, such as landing page, signup, login, etc. 
3. `src/main/secure`: Stores the components used in private page, which requires authentication, such as product list, etc.
4. `src/main/util`: Stores the utility functions used across the application, such as `formatDate`, `httpGet`, etc.
```
.
├── Dockerfile
├── Dockerfile.prod
├── README.md
├── docker-compose-prod.yml
├── docker-compose.yml
├── package.json
├── public
│   ├── doge.png
│   ├── favicon.ico
│   ├── index.html
│   ├── logo192.png
│   ├── logo512.png
│   ├── manifest.json
│   └── robots.txt
├── src
│   ├── App.css
│   ├── App.js
│   ├── App.test.js
│   ├── index.css
│   ├── index.js
│   ├── logo.svg
│   ├── main
│   │   ├── common
│   │   ├── public
│   │   ├── secure
│   │   └── utils
│   ├── reportWebVitals.js
│   ├── setupTests.js
│   ├── static
│   │   └── doge.png
│   └── test
│       ├── common
│       ├── public
│       ├── secure
│       └── utils
└── yarn.lock
```

---

## Backend Service File Structure  <a name="backend"></a>

The following tree shows the file structure under the `src` folder. There are 5 packages `com.dennis.gocity`

1. **Data Transfer Object (DTO)**: Representation of business objects (Product and Category)
2. **Controller**: Implementation of the API endpoints
3. **Service**: Implemenatation of the business logics
4. **Repository**: Implemenatation of database queries. We only use Spring's default CrudRepository implementation in the project.
5. **Entity**: JPA entities that are persisted to the database.

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── dennis
│   │           └── gocity
│   │               ├── GocityApplication.java
│   │               ├── controller
│   │               ├── dto
│   │               ├── entity
│   │               ├── repository
│   │               └── service
│   └── resources
│       ├── application.properties
│       ├── static
│       └── templates
└── test
    └── java
        └── com
            └── dennis
                └── gocity
                    ├── GocityApplicationTests.java
                    ├── controller
                    ├── dao
                    ├── entity
                    ├── repository
                    └── service
```

---

## Category API <a name="category"></a>

### **Get all categories**
Returns a list of category objects in JSON format.

* **URL**

  `/categories`

* **Method:**

  `GET`
  
*  **URL Params**

    None

* **Data Params**

    None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[{ "id" : 1, categoryName : "Example Category" }]`
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

### **Get category by ID**
Returns a category object in JSON format.

* **URL**

  `/categories/:id`

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

    None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ "id" : 1, categoryName : "Example Category" }`
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

### **Create category**
Insert a category entry in the database and returns a category object in JSON format. `id` is generated automatically during the insert operation.

* **URL**

  `/categories`

* **Method:**

  `POST`
  
*  **URL Params**

    None

* **Data Params**

    ```json
    {
        "categoryName": "Example Category"
    }
    ```

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ "id" : 1, categoryName : "Example Category" }`
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

### **Update category**
Update a category entry in the database and returns a category object in JSON format.

* **URL**

  `/categories/:id`

* **Method:**

  `PUT`
  
*  **URL Params**

    **Required:**
 
   `id=[integer]`


* **Data Params**

    ```json
    {
        "id": 1,
        "categoryName": "Example Category Updated"
    }
    ```

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ "id" : 1, categoryName : "Example Category Updated" }`
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

### **Delete category**
Delete a category entry in the database and returns the `id` of the deleted entry as an integer.

* **URL**

  `/categories/:id`

* **Method:**

  `DELETE`
  
*  **URL Params**

    **Required:**
 
   `id=[integer]`


* **Data Params**

    None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `1`
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

<br>

## Product API <a name="product"></a>
---
### **Get all products**
Returns a list of product objects in JSON format.

* **URL**

  `/products`

* **Method:**

  `GET`
  
*  **URL Params**

    None

* **Data Params**

    None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    ```json
    [
        {
            "id": 1,
            "name": "Example Product",
            "description": "Example product description",
            "category": {
                "id": 1,
                "categoryName": "Example Category"
            },
            "creationDate": "2021-07-13T16:41:15.388+00:00",
            "updateDate": "2021-07-13T16:41:15.388+00:00",
            "lastPurchasedDate": null
        }
    ]
    ```
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

### **Get product by ID**
Returns a product object in JSON format.

* **URL**

  `/products/:id`

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

    None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    ```json
    {
        "id": 1,
        "name": "Example Product",
        "description": "Example product description",
        "category": {
            "id": 1,
            "categoryName": "Example Category"
        },
        "creationDate": "2021-07-13T16:41:15.388+00:00",
        "updateDate": "2021-07-13T16:41:15.388+00:00",
        "lastPurchasedDate": null
    }
    ```
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`


---

### **Purchase product**
Returns a product object in JSON format.

* **URL**

  `/products/:id/purchase`

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

    None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    ```json
    {
        "id": 1,
        "name": "Example Product",
        "description": "Example product description",
        "category": {
            "id": 1,
            "categoryName": "Example Category"
        },
        "creationDate": "2021-07-13T16:41:15.388+00:00",
        "updateDate": "2021-07-13T16:41:15.388+00:00",
        "lastPurchasedDate": "2021-07-13T16:41:15.388+00:00"
    }
    ```
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

### **Create product**
Insert a product entry in the database and returns a product object in JSON format. `id` is generated automatically during the insert operation.

* **URL**

  `/products`

* **Method:**

  `POST`
  
*  **URL Params**

    None

* **Data Params**

    ```json
    {
        "name": "Example Product",
        "description": "Example Product Description",
        "category": {
            "id": 1,
            "categoryName": "Example Category"
        }
    }
    ```

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    ```json
    {
        "id": 1,
        "name": "Example Product",
        "description": "Example Product Description",
        "category": {
            "id": 1,
            "categoryName": "Example Category"
        },
        "creationDate": "2021-07-13T16:41:15.388+00:00",
        "updateDate": "2021-07-13T16:41:15.388+00:00",
        "lastPurchasedDate": null
    }
    ```
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

### **Update product**
Update a product entry in the database and returns a product object in JSON format.

* **URL**

  `/products/:id`

* **Method:**

  `PUT`
  
*  **URL Params**

    **Required:**
 
   `id=[integer]`


* **Data Params**

    ```json
    {
        "id": 1,
        "name": "Example Product Updated",
        "description": "Example Product Description Updated",
        "category": {
            "id": 1,
            "categoryName": "Example Category"
        }
    }
    ```

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    ```json
    {
        "id": 1,
        "name": "Example Product Updated",
        "description": "Example Product Description Updated",
        "category": {
            "id": 1,
            "categoryName": "Example Category"
        },
        "creationDate": "2021-07-13T16:41:15.388+00:00",
        "updateDate": "2021-07-13T16:41:15.388+00:00",
        "lastPurchasedDate": null
    }
    ```
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

### **Delete product**
Delete a product entry in the database and returns the `id` of the deleted entry as an integer.

* **URL**

  `/products/:id`

* **Method:**

  `DELETE`
  
*  **URL Params**

    **Required:**
 
   `id=[integer]`


* **Data Params**

    None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `1`
 
* **Error Response:**

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `null`

---

## How to Run <a name="how"></a>
Make sure Docker and Docker Compose are installed on your machine. 

### Start the database
1. `cd backend/gocity-database`
2. `docker-compose build && docker-compose up -d`
3. (Stop) `docker-compose.yml up -d`

### Start the backend service
1. `cd backend/gocity`
2. `docker-compose build && docker-compose up -d`
3. (Stop) `docker-compose.yml up -d`

### Start the frontend portal
1. `cd frontend/gocity-product-management`
2. `docker-compose -f docker-compose-prod.yml build && docker-compose -f docker-compose-prod.yml up -d`
3. (Stop) `docker-compose-prod.yml up -d`

---

## Future Development
1. (Backend) Implement authentication layer using `Spring Security`
2. (UI Improvement) Transform the existing landing page into a login page
3. (UI Improvement) Support category management
4. (UI Improvement) Add navigation bar and side bar
5. ...