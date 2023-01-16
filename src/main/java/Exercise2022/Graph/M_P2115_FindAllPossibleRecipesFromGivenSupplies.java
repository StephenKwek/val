package Exercise2022.Graph;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 2115. Find All Possible Recipes from Given Supplies
 *
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
 * The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
 * Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 */
public class M_P2115_FindAllPossibleRecipesFromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        ArrayList<String> available = new ArrayList<>(Arrays.asList(supplies));
        Map<String, List<String>> requirements = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            requirements.put(recipes[i], new ArrayList<>(ingredients.get(i)));
        }
        for (int i = 0; i < available.size(); i++) {
            for (String recipe : recipes) {
                if (requirements.containsKey(recipe) && requirements.get(recipe).contains(available.get(i))) {
                    requirements.get(recipe).remove(available.get(i));
                    if (requirements.get(recipe).isEmpty()) {
                        available.add(recipe);
                        requirements.remove(recipe);
                    }
                }
            }
        }
        return Arrays.stream(recipes)
                .filter(available::contains)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        M_P2115_FindAllPossibleRecipesFromGivenSupplies p = new M_P2115_FindAllPossibleRecipesFromGivenSupplies();
        String[] recipes = new String[] {"bread"};
        List<List<String>> ingradients = List.of(
                List.of("yeast","flour")
        );
        String[] supplies = new String[]{"yeast","flour","corn"};
        TestUtil.equals(List.of("bread"), p.findAllRecipes(recipes, ingradients, supplies));

        recipes = new String[] {"bread","sandwich"};
        ingradients = List.of(
            List.of("yeast","flour"),
            List.of("bread","meat")
        );
        supplies = new String[] {"yeast","flour","meat"};
        TestUtil.equals(List.of("bread", "sandwich"), p.findAllRecipes(recipes, ingradients, supplies));

        recipes = new String[] {"bread","sandwich","burger"};
        ingradients = List.of(
                List.of("yeast","flour"),
                List.of("bread","meat"),
                List.of("sandwich","meat","bread")
        );
        supplies = new String[] {"yeast","flour","meat"};
        TestUtil.equals(List.of("bread", "sandwich", "burger"), p.findAllRecipes(recipes, ingradients, supplies));
    }
}
