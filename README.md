
# Recipe Web Service

Web service that allows registred users

* Save their recipes
* Update their recipes
* Delete their recipes
* Search for others recipes
## API

#### Registering a new user

```
  POST /api/register
```
Request body
```js
{
    "email" : "admin@example.com",
    "password" : "p4ssword"
}
```

#### Adding new recipe

```
  GET /api/recipe/new
```
Request body
```js
{
    "name" : "Fresh Mint Tea",
    "category" : "beverage",
    "description": "Light, aromatic and refreshing beverage, ...",
    "ingredients": ["boiled water", "honey", "fresh mint leaves"],
    "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", 
        "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

#### Get repice

```
  GET /api/recipe/{id}
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :--------------------------------   |
| `id`      | `@Path`  | **Required**. Id of recipe to fetch |

Returns recipe with specified id.

#### Search recipes by category or name

```
  GET /api/recipe/search
```
| Parameter | Type      | Description                                           |
| :-------- | :-------  | :--------------------------------                     |
| `category`| `string`  | **Not required**. Category of recipe to fetch         |
| `name`    | `string`  | **Not required**. to fetch                            |

Returns recipe with specified category or name.

#### Delete recipe

```
  DELETE /api/recipe/{id}
```
| Parameter | Type     | Description                         |
| :-------- | :------- | :--------------------------------   |
| `id`      | `@Path`  | **Required**. Id of recipe to delete |

Deletes recipe with specified id.

#### Update recipes by category or name

```
  PUT /api/recipe/{id}
```
| Parameter | Type     | Description                          |
| :-------- | :------- | :--------------------------------    |
| `id`      | `@Path`  | **Required**. Id of recipe to update |
Request body
```js
{
    "name": "Warming Ginger Tea",
    "category": "beverage",
    "description": "Ginger tea is a warming drink for cool weather, ...",
    "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
    "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", 
        "Steep for 5-10 minutes", "Drink and enjoy"]
}
```

Updates recipe with specified id.