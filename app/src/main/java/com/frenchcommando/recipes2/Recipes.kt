package com.frenchcommando.recipes2

class RecipeDescription(
    val description: String = "",
    val ingredients: List<String> = listOf(),
    val oven: String = ""
)

class Recipe(
    val title: String,
    val drawable: Int,
    val content: RecipeDescription
)

object Recipes {
    val bakingList = listOf (
        Recipe(
            "Brownie",
            R.drawable.brownies,
            RecipeDescription(
                description = "VeryGood"
            )),
        Recipe(
            "Black Forest",
            R.drawable.blackforest,
            RecipeDescription(
                oven = "350F - 20min"
            )),
        Recipe(
            "Macarons",
            R.drawable.macarons,
            RecipeDescription(
                description = "best stuff",
                ingredients = listOf(
                    "100g flour",
                    "50g almond"
                ),
                oven = "350F - 10min"
            )),
        Recipe(
            "Cookies",
            R.drawable.cookies,
            RecipeDescription(
                description = "these items",
                oven="350F -10 min"
            )),
        Recipe(
            "Corn Bread",
            R.drawable.cornbread,
            RecipeDescription(
                description =
                "For Corn Bread" +
                        "\n" +
                        "cornmeal, flour, baking powder, baking soda salt" +
                        "\n" +
                        "melted butter, brown sugar, honey" +
                        "\n" +
                        "egg" +
                        "\n" +
                        "buttermilk",
                ingredients = listOf(
                    "120g fine cornmeal",
                    "125g all-purpose flour",
                    "1 teaspoon baking powder",
                    "1/2 teaspoon baking soda",
                    "115g unsalted butter melted cooled",
                    "67g brown sugar",
                    "30mL honey",
                    "1 large egg room temperature",
                    "240mL buttermilk room temperature"
                ),
                oven="400F 204C - 20 min"
            )),
        Recipe(
            "Yellow Cake",
            R.drawable.cornbread,
            RecipeDescription(
                description =
                    "6in tins",
                ingredients = listOf(
                    "2.5 cups all-purpose flour",
                    "0.75 tsp salt",
                    "2.25 baking powder",
                    "",
                    "3/4 cup 170g unsalted butter - room temperature",
                    "1.66 granulated sugar",
                    "3 eggs - one at a time",
                    "1 Tbsp 15mL Vanilla",
                    "",
                    "add: 1 cup buttermilk + dry ingredients",
                    "",
                    "Ganache",
                    "1/4 cup 70g unsalted butter",
                    "1/4 cup 25g cocoa",
                    "Chocolate",
                    "1/3 cup semisweet or bittersweet chocolate",
                    "1/4 cup 60mL milk",
                    "Buttercream",
                    "1 cup 2 sticks butter",
                    "+ chocolate",
                    "1/2 tsp salt",
                    "3 cups 1lb 450g powdered sugar",
                    "1/4 60mL cup milk",
                    "+ ganache",
                ),
                oven="350F - 30 min"
            )),
    )
    private fun getBaking(name: String?) : Recipe? {
        return bakingList.singleOrNull{ it.title == name }
    }

    val cookingList = listOf(
        Recipe(
            "Pasta",
            R.drawable.pasta,
            RecipeDescription(

            )
        ),
        Recipe(
            "Chili",
            R.drawable.pasta,
            RecipeDescription(
                description =
                "Cook everything",
                ingredients = listOf(
                    "1 tablespoon olive oil",
                    "1 medium yellow onion - diced",
                    "1 pound 90% lean ground beef",
                    "2.5 tablespoons chili powder",
                    "2 tablespoons ground cumin",
                    "2 tablespoons granulated sugar",
                    "2 tablespoons tomato paste",
                    "1 tablespoon garlic powder",
                    "1.5 teaspoon salt",
                    "0.5 teaspoon ground black pepper",
                    "0.25 teaspoon ground cayenne pepper",
                    "1.5 cups beef broth",
                    "1 can (15oz) petite diced tomatoes",
                    "1 can (16oz) red kidney beans, drained rinced",
                    "1 can (8oz) tomato sauce",
                ),
                oven="Stove"
            )),
    )
    private fun getCooking(name: String?) : Recipe? {
        return cookingList.singleOrNull{ it.title == name }
    }


    fun getRecipe(name: String?) : Recipe? {
        return getBaking(name) ?: getCooking(name)
    }
}