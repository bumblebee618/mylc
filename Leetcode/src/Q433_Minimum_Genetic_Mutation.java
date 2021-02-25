import java.util.*;
/**
 * 
A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needNum to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needNum, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3
 *
 */
public class Q433_Minimum_Genetic_Mutation {
	public int minMutation(String start, String end, String[] bank) {
        if (start == null || end == null || bank == null || bank.length == 0) {
            return -1;
        } else if (start.equals(end)) {
            return 0;
        }
        
        Set<String> geneBank = new HashSet<>();
        Set<String> visited = new HashSet<>();
        
        for (String gene : bank) {
            geneBank.add(gene);
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String gene = queue.poll();
                
                for (String newGene : getValidMutation(gene, geneBank)) {
                    if (visited.contains(newGene)) {
                        continue;
                    } else if (newGene.equals(end)) {
                        return step;
                    } else {
                        visited.add(newGene);
                        queue.offer(newGene);
                    }
                }
                
            }
        }
        
        return -1;
    }
    
    private List<String> getValidMutation(String start, Set<String> geneBank) {
        char[] validLetters = {'A', 'C', 'G', 'T'};
        char[] letters = start.toCharArray();
        List<String> list = new LinkedList<>();
        
        for (int i = 0; i < start.length(); i++) {
            char temp = letters[i];
            
            for (char c : validLetters) {
                if (temp == c) {
                    continue;
                }
                
                letters[i] = c;
                String newGene = new String(letters);
                
                if (geneBank.contains(newGene)) {
                    list.add(newGene);
                }
            }
            
            letters[i] = temp;
        }
        
        return list;
    }
}
