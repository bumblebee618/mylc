import java.util.*;

/***
 * 
 * @author jackie
 * 
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.

 

Example 1:

Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
Example 2:

Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
Example 3:

Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 

Constraints:

n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.
 *
 */
public class Q2115_Find_All_Possible_Recipes_from_Given_Supplies {
	public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> result = new LinkedList<>();

        if (recipes == null || recipes.length == 0 || ingredients == null || ingredients.size() == 0 || supplies == null || supplies.length == 0 || recipes.length != ingredients.size()) {
            return result;
        }

        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, Integer> dependencyMap = new HashMap<>();

        Set<String> recipeSet = new HashSet<>();
        for (String recipe : recipes) {
            recipeSet.add(recipe);
        }

        for (int i = 0; i < recipes.length; i++) {
            List<String> ingredient = ingredients.get(i);

            for (String supply : ingredient) {
                graph.computeIfAbsent(supply, x -> new HashSet<>());

                if (!graph.get(supply).contains(recipes[i])) {
                    graph.get(supply).add(recipes[i]);
                    dependencyMap.put(recipes[i], dependencyMap.getOrDefault(recipes[i], 0) + 1);
                }
            }
        }

        Queue<String> queue = new LinkedList<>();

        for (String supply : supplies) {
            queue.offer(supply);
        }

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            if (recipeSet.contains(cur)) {
                result.add(cur);
            }

            if (!graph.containsKey(cur)) {
                continue;
            }

            for (String next : graph.get(cur)) {
                int dependencyCount = dependencyMap.get(next);

                if (dependencyCount > 1) {
                    dependencyMap.put(next, dependencyCount - 1);
                } else {
                    dependencyMap.remove(next);
                    queue.offer(next);
                }
            }
        }

        return result;
    }
    
    
    public static void main(String[] args) {
    	Q2115_Find_All_Possible_Recipes_from_Given_Supplies test = new Q2115_Find_All_Possible_Recipes_from_Given_Supplies();
    	String[] recipes = {"ju","fzjnm","x","e","zpmcz","h","q"};
    	String[] supplies = {"f","hveml","cpivl","d"};
    
    	List<String> list1 = new LinkedList<>();
    	list1.add("d");
    	
    	List<String> list2 = new LinkedList<>();
    	list2.add("hveml");
    	list2.add("f");
    	list2.add("cpivl");
    	
    	List<String> list3 = new LinkedList<>();
    	list3.add("cpivl");
    	list3.add("zpmcz");
    	list3.add("h");
    	list3.add("e");
    	list3.add("fzjnm");
    	list3.add("ju");
    	
    	List<String> list4 = new LinkedList<>();
    	list4.add("cpivl");
    	list4.add("hveml");
    	list4.add("zpmcz");
    	list4.add("ju");
    	list4.add("ju");
    	
    	List<String> list5 = new LinkedList<>();
    	list5.add("h");
    	list5.add("fzjnm");
    	list5.add("e");
    	list5.add("q");
    	list5.add("x");
    	
    	List<String> list6 = new LinkedList<>();
    	list6.add("d");
    	list6.add("hveml");
    	list6.add("cpivl");
    	list6.add("q");
    	list6.add("zpmcz");
    	list6.add("ju");
    	list6.add("e");
    	list6.add("x");
    	
    	List<String> list7 = new LinkedList<>();
    	list7.add("f");
    	list7.add("hveml");
    	list7.add("cpivl");
    	
    	List<List<String>> ingredients = new LinkedList<>();
    	ingredients.add(list1);
    	ingredients.add(list2);
    	ingredients.add(list3);
    	ingredients.add(list4);
    	ingredients.add(list5);
    	ingredients.add(list6);
    	ingredients.add(list7);
    	
    	test.findAllRecipes(recipes, ingredients, supplies);
    }
    
}
