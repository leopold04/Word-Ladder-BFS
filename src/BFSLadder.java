//package BFSLadder;
import java.util.*;

/**
solves word ladders using breadth-first search
@author Leopold Dorilas
@version 2022/02/01
*/
public class BFSLadder
{
   /** Dictionary object containing the word list*/
   private Dictionary myWordList;

   /** integer storing the number of nodes visited */
   private int nodes;

   /** start time of BFS algorithm */
   private long start;

   /** total runtime of BFS algorithm */
   private long duration;

   /** constructor creating new BFSLadder object */
   public BFSLadder() { }


   /**
    Runs BFS ladder program
    */
   public void run() {
      while (true)
      {
         myWordList = new Dictionary();
         Scanner reader = new Scanner(System.in);
         System.out.print("Enter starting word: ");
         String startWord = reader.nextLine();
         System.out.print("Enter ending word: ");
         String endWord = reader.nextLine();
         ArrayList<String> ladder = this.getLadder(startWord, endWord, true);
         if (ladder.size() == 0)
         {
            System.out.println("No solution found!");
         }
         else
         {
            System.out.println("Solution = " + ladder);
            System.out.println("Nodes visited = " + nodes);
            System.out.println("Runtime: " + duration / Math.pow(10,9) + " seconds");
         }
         System.out.printf("Do you want to play again?");
         if (!reader.next().toLowerCase().equals("y"))
         {
            System.exit(0);
         }
      }
   }

   /**
    * Method that uses BFS to find the path from one word to another
    * @param startWord
    * @param endWord
    * @param seeSearch
    * @return word ladder path from start word to end word, empty if there is no solution
    */
   public ArrayList<String> getLadder(String startWord, String endWord, boolean seeSearch)
   {
      start = System.nanoTime();
      ArrayList<ArrayList<String>> queue = new ArrayList<>();
      // adds first word to a chain
      ArrayList<String> chain = new ArrayList<>(Arrays.asList(startWord));
      queue.add(chain);
      myWordList.setValue(startWord,Dictionary.USED);
      // BFS algorithm
      while (!queue.isEmpty())
      {
         // reference of first index in order to get neighbors
         ArrayList<String> newChain = queue.get(0);
         queue.remove(0);
         nodes++;
         ArrayList<String> neighbors = myWordList.getNeighbors(newChain.get(newChain.size()-1));
         for (String neighbor: neighbors){
            if (myWordList.isUsed(neighbor) == Dictionary.UNUSED){
               // adding 'neighbor' to queue (chain + neighbor)
               chain = new ArrayList<>(newChain);
               chain.add(neighbor);
               queue.add(chain);
               if (seeSearch){
                  System.out.println(chain);
               }
               myWordList.setValue(neighbor,Dictionary.USED);
               if (chain.get(chain.size()-1).equals(endWord)){
                  duration = System.nanoTime() - start;
                  return chain;
               }
            }
         }
      }
      duration = System.nanoTime() - start;
      return new ArrayList<>(Arrays.asList("No solution"));
   }

   public static void main(String[] args)
   {
      BFSLadder test = new BFSLadder();
      test.run();
   }
}