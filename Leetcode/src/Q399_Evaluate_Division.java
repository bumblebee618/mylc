import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/********
 * 
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, row / row = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["row", "row"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

 * 
 * */

public class Q399_Evaluate_Division {
	private Map<String, Map<String, Double>> graph = new HashMap<>();
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (equations == null || equations.size() == 0 || values == null || values.length == 0 || queries == null || queries.size() == 0) {
            return new double[0];
        } else if (equations.size() != values.length) {
            return new double[0];
        }
        
        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);
            graph.computeIfAbsent(equation.get(0), x -> new HashMap<>()).put(equation.get(1), values[i]);
            graph.computeIfAbsent(equation.get(1), x -> new HashMap<>()).put(equation.get(0), 1.0 / values[i]);
        }
        
        double[] result = new double[queries.size()];
        
        for (int i = 0; i < queries.size(); i++) {
            result[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, new HashSet<>());
        }
        
        return result;
    }
    
    private double dfs(String start, String end, double sum, Set<String> visited) {
        if (!graph.containsKey(start)) {
            return -1.0;
        } else if (start.equals(end)) {  // 将 startNode.equals(endNode) 放第二个，防止题目给的test里 [x, x]的情况
            return 1.0;
        } else if (graph.get(start).containsKey(end)) {
            return sum * graph.get(start).get(end);
        }
        
        visited.add(start);
        
        for (Map.Entry<String, Double> entry : graph.get(start).entrySet()) {
            if (!visited.contains(entry.getKey())) {
                double result = dfs(entry.getKey(), end, sum * entry.getValue(), visited);
                
                if (result != -1.0) {
                    return result;
                }
            }
        }
        
        return -1.0;
    }
}
