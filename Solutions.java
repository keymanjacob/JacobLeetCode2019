/*
All Rights Reserved
Author: Guanhua Fan
*/


#1 Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

----------------------------
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if (map.containsKey(complement)){
                return new int[] {
                  map.get(complement), i
                };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

Runtime: 3 ms, faster than 99.74% of Java online submissions for Two Sum.
Memory Usage: 26.9 MB, less than 41.38% of Java online submissions for Two Sum.

==========================================================================
#2 Add Two Numbers
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8git
Explanation: 342 + 465 = 807.

----------------------------
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2, curr;
        ListNode dummyHead = new ListNode(0);
        curr = dummyHead;
        int carry = 0;
        while (p != null || q != null){
            int x = (p!=null)? p.val: 0;
            int y = (q!=null)? q.val: 0;
            int sum = x + y + carry;
            curr.next = new ListNode(sum%10);
            carry = sum / 10;
            if (p!=null) p = p.next;
            if (q!=null) q = q.next;
            curr = curr.next;
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
        }
        return dummyHead.next;
    }
}

Runtime: 22 ms, faster than 81.69% of Java online submissions for Add Two Numbers.
Memory Usage: 29.2 MB, less than 73.64% of Java online submissions for Add Two Numbers.
================================================================================
#3 Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

----------------------------
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<> ();
        int i = 0, j = 0, ans = 0, n = s.length();
        while (i<n && j<n){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j-i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}

Runtime: 28 ms, faster than 69.00% of Java online submissions for Longest Substring Without Repeating Characters.
Memory Usage: 29.5 MB, less than 10.73% of Java online submissions for Longest Substring Without Repeating Characters.
=========================================================================================
#6 ZigZag Conversion
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I

---------------------------------
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i< Math.min(s.length(), numRows); i++){
            rows.add(new StringBuilder());
        }
        int currRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()){
            rows.get(currRow).append(c);
            if (currRow == 0 || currRow == numRows-1) goingDown = !goingDown;
            currRow += (goingDown)? 1: -1;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row: rows){
            ret.append(row);
        }
        return ret.toString();
    }
}

Runtime: 27 ms, faster than 73.34% of Java online submissions for ZigZag Conversion.
Memory Usage: 29.5 MB, less than 32.83% of Java online submissions for ZigZag Conversion.
==================================================================================================
#14 Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

-------------------------------------------------
class Solution {
    public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++)
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) return "";
        }
    return prefix;
	}
}
Runtime: 4 ms, faster than 99.85% of Java online submissions for Longest Common Prefix.
Memory Usage: 26.4 MB, less than 44.29% of Java online submissions for Longest Common Prefix.

=====================================================================================================
#104. Maximum Depth of Binary Tree
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.

----------------------------------------------------
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    } else {
      int left_height = maxDepth(root.left);
      int right_height = maxDepth(root.right);
      return java.lang.Math.max(left_height, right_height) + 1;
    }
  }
}
Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
Memory Usage: 28.2 MB, less than 18.58% of Java online submissions for Maximum Depth of Binary Tree.
===============================================================================================================
#111. Minimum Depth of Binary Tree
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
-----------------------------------------
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        } else {
            int minDepthLeft = minDepth(root.left) ;
            int minDepthRight = minDepth(root.right);
            return (minDepthLeft==0||minDepthRight==0)?
                minDepthLeft+minDepthRight+1:
                Math.min(minDepthLeft, minDepthRight)+1;
        }
    }
}
Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Depth of Binary Tree.
Memory Usage: 26.8 MB, less than 53.30% of Java online submissions for Minimum Depth of Binary Tree.

=====================================================================================================================
#124. Binary Tree Maximum Path Sum
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
------------------------------------------------
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Res {
    public int val;
}
class Solution {
    public int findMaxSum(TreeNode root, Res res) {
        if (root == null) return 0;
        int l = findMaxSum(root.left, res);
        int r = findMaxSum(root.right, res);

        int max_single = Math.max((Math.max(l, r)+root.val), root.val);
        int max_top = Math.max(max_single, (l+r+root.val));
        res.val = Math.max(res.val, max_top);
        return max_single;
    }

    public int maxPathSum(TreeNode root) {
        Res res = new Res();
        res.val = Integer.MIN_VALUE;
        findMaxSum(root, res);
        return res.val;
    }
}

Runtime: 1 ms, faster than 99.65% of Java online submissions for Binary Tree Maximum Path Sum.
Memory Usage: 30.6 MB, less than 32.38% of Java online submissions for Binary Tree Maximum Path Sum.
==============================================================================================================================
#142. Linked List Cycle II
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.



Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.


Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.




Follow up:
Can you solve it without using extra space?

--------------------------------------------
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;
        fast = head;
        while (true) {
            if (fast == slow) return slow;
            slow = slow.next;
            fast = fast.next;
        }

    }
}

Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle II.
Memory Usage: 22.6 MB, less than 10.22% of Java online submissions for Linked List Cycle II.
============================================================================================================================
#287. Find the Duplicate Number
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
------------------------------------------------
public class Solution {
   public int findDuplicate(int[] nums) {
     int n=nums.length-1;
     int lo=1, hi=n;
     while (lo<hi) {
       int mid=lo+(hi-lo)/2;
       if (count(nums,lo,mid)>mid-lo+1) hi=mid;
       else lo=mid+1;
     }
     return lo;
   }
   private int count(int[] nums, int lo, int hi) {
     int res=0;
     for (int x: nums) {
       if (x>=lo && x<=hi) res++;
     }
     return res;
   }
 }

 Runtime: 3 ms, faster than 49.58% of Java online submissions for Find the Duplicate Number.
Memory Usage: 27.9 MB, less than 6.34% of Java online submissions for Find the Duplicate Number.
==============================================================================================================================
#342. Power of Four
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:

Input: 16
Output: true
Example 2:

Input: 5
Output: false
Follow up: Could you solve it without loops/recursion?
--------------------------------------


public class Solution {
    public boolean isPowerOfFour(int num) {
        int mask = 0xAAAAAAAA;
        return (( mask & num )== 0 ) && isPowerofTwo(num);
    }
    public boolean isPowerofTwo(int n){
        return (n>0) && ( n == 1 || ( (n & (n-1)) ==0 ));
    }
}

Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Four.
Memory Usage: 24.1 MB, less than 32.35% of Java online submissions for Power of Four.
=================================================================================================================================
#344. Reverse String
Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.



Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]

----------------------------------------
class Solution {
    public void reverseString(char[] s) {
        char temp;
        int midPoint = Math.round(s.length/2);
        for (int i = 0; i < midPoint; i++){
                temp = s[i];
                s[i] = s[s.length-i-1];
                s[s.length-i-1] = temp;
        }
    }
}

Runtime: 13 ms, faster than 15.89% of Java online submissions for Reverse String.
Memory Usage: 37.6 MB, less than 7.68% of Java online submissions for Reverse String.
===============================================================================================================================
#384. Shuffle an Array
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
---------------------------------------
class Solution {

    private int[] array;
    private int[] original;
    private Random rand = new Random();

    private List<Integer> getArrayCopy() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++){
            list.add(array[i]);
        }
        return list;
    }

    public Solution(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();

        for (int i = 0; i < array.length; i ++){
            int removeIndex = rand.nextInt(aux.size());
            array[i] = aux.get(removeIndex);
            aux.remove(removeIndex);
        }
        return array;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
Runtime: 175 ms, faster than 58.64% of Java online submissions for Shuffle an Array.
Memory Usage: 56.1 MB, less than 2.21% of Java online submissions for Shuffle an Array.
==============================================================================================================================
#412. Fizz Buzz
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]

--------------------------------------------
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> ls = new ArrayList<> ();
        for ( int  i = 1; i <= n; i ++ ){
            String res = "";

            if (i%3 == 0) {
                res += "Fizz";
            }
            if (i%5 ==0) {
                res += "Buzz";
            }
            if (res.equals("")) res += Integer.toString(i);
            ls.add(res);
        }
        return ls;
    }
}

Runtime: 3 ms, faster than 39.24% of Java online submissions for Fizz Buzz.
Memory Usage: 27.7 MB, less than 23.73% of Java online submissions for Fizz Buzz.
=================================================================================================================
#256. Paint House
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
----------------------------------
class Solution {
    public int minCost(int[][] costs) {
        if(costs==null||costs.length==0)
            return 0;

        for(int i=1; i<costs.length; i++){
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }

        int n = costs.length-1;
        return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }
}
Runtime: 0 ms, faster than 100.00% of Java online submissions for Paint House.
Memory Usage: 26.6 MB, less than 24.14% of Java online submissions for Paint House.
===================================================================================================================
#283. Move Zeroes
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
----------------------------------------------------
class Solution {
    public void moveZeroes(int[] nums) {
        for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.length; cur++ ){
            if (nums[cur] != 0){
                // swap nums[cur] and nums[lastNonZeroFoundAt]
                int temp = nums[cur];
                nums[cur] = nums[lastNonZeroFoundAt];
                nums[lastNonZeroFoundAt] = temp;
                lastNonZeroFoundAt++;
            }
        }
    }
}

Runtime: 1 ms, faster than 100.00% of Java online submissions for Move Zeroes.
Memory Usage: 28.1 MB, less than 37.33% of Java online submissions for Move Zeroes.
======================================================================================================================
#17. Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
----------------------------------------------------
class Solution {
    Map<String, String> phone = new HashMap<String, String> () {{
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};

    List<String> output = new ArrayList<String>();
    public void backtrack(String combination, String next_digits){
        if (next_digits.length() == 0){
            output.add(combination);
        } else {
            String digit = next_digits.substring(0,1);
            String letters = phone.get(digit);
            for (int i = 0 ; i < letters.length(); i++){
                String letter = phone.get(digit).substring(i, i+1);
                backtrack(combination + letter, next_digits.substring(1));
            }
        }

    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return output;
    }
}

Runtime: 2 ms, faster than 81.46% of Java online submissions for Letter Combinations of a Phone Number.
Memory Usage: 26.5 MB, less than 10.63% of Java online submissions for Letter Combinations of a Phone Number.
=====================================================================================================================
#236. Lowest Common Ancestor of a Binary Tree
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]




Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
-----------------------------------------------------
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private TreeNode ans;

    public Solution() {
        // Variable to store LCA node.
        this.ans = null;
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;


        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.ans;
    }
}

Runtime: 6 ms, faster than 99.80% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
Memory Usage: 23.1 MB, less than 21.61% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
=======================================================================================================================
#121. Best Time to Buy and Sell Stock
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
------------------------------------------------------

class Solution {
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++){
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}

Runtime: 1 ms, faster than 99.88% of Java online submissions for Best Time to Buy and Sell Stock.
Memory Usage: 27.6 MB, less than 20.22% of Java online submissions for Best Time to Buy and Sell Stock.
=====================================================================================================================================================
#7. Reverse Integer

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
----------------------------------
class Solution {
    public int reverse(int x) {
        boolean isNegative = x<0;
        String y = String.valueOf(Math.abs(x));

        char[] chars = y.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length-1; i >=0; i--){
            sb.append(chars[i]);
        }
        try {
            return (isNegative)? -1 * Integer.valueOf(sb.toString()): Integer.valueOf(sb.toString());
        } catch (NumberFormatException e){
            return 0;
        }
    }
}
Runtime: 19 ms, faster than 60.41% of Java online submissions for Reverse Integer.
Memory Usage: 27.2 MB, less than 5.22% of Java online submissions for Reverse Integer.
=================================================================================================================
#88. Merge Sorted Array
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
-----------------------------
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1;
        int p2 = n-1;
        int p = m+n-1;
        while (p1>=0 && p2>=0){
            nums1[p--] = (nums1[p1] > nums2[p2])? nums1[p1--]:nums2[p2--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2+1);
    }
}
Runtime: 3 ms, faster than 64.99% of Java online submissions for Merge Sorted Array.
Memory Usage: 26.6 MB, less than 7.63% of Java online submissions for Merge Sorted Array.
=================================================================================================================
#21. Merge Two Sorted Lists
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
--------------------------
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l2 == null) {
            return l1;
        } else if (l1 == null) {
            return l2;
        } else if (l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
Runtime: 7 ms, faster than 72.31% of Java online submissions for Merge Two Sorted Lists.
Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Merge Two Sorted Lists.

=================================================================================================================
#9. Palindrome Number
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?
---------------------------------------
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x > 0)) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber*10 + x%10;
            x /= 10;
        }
        return (x == revertedNumber) || (x == revertedNumber/10);
    }
}

Runtime: 82 ms, faster than 73.64% of Java online submissions for Palindrome Number.
Memory Usage: 40.5 MB, less than 100.00% of Java online submissions for Palindrome Number.
=================================================================================================================
#20. Valid Parentheses
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
--------------------------
class Solution {
    private HashMap<Character, Character> mappings;
    public Solution () {
        this.mappings = new HashMap<Character, Character> ();
        this.mappings.put (')', '(');
        this.mappings.put (']', '[');
        this.mappings.put ('}', '{');
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for( int i = 0 ; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (this.mappings.containsKey(c)) {
                char topElement = stack.empty()? '#': stack.pop();
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }
}
Runtime: 5 ms, faster than 76.76% of Java online submissions for Valid Parentheses.
Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Valid Parentheses.
=================================================================================================================
#26. Remove Duplicates from Sorted Array
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}

----------------------------
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;
        int dupAt = 0;
        for (int cur = 1; cur < nums.length; cur++) {
            if (nums[cur]!= nums[dupAt]) {
                dupAt++;
                nums[dupAt] = nums[cur];
            }
        }
        return dupAt+1;
    }
}
Runtime: 6 ms, faster than 97.50% of Java online submissions for Remove Duplicates from Sorted Array.
Memory Usage: 42 MB, less than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
=================================================================================================================
#53. Maximum Subarray
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
------------------------------------
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i  = 1; i < nums.length; i ++) {
            dp[i] = nums[i] + (dp[i-1]>0? dp[i-1]: 0);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
Runtime: 6 ms, faster than 99.94% of Java online submissions for Maximum Subarray.
Memory Usage: 39.6 MB, less than 100.00% of Java online submissions for Maximum Subarray.
=================================================================================================================
#28. Implement strStr()
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
--------------------------------------
public class Solution {
    public int strStr(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        if (l1 < l2) {
            return -1;
        } else if (l2 == 0) {
            return 0;
        }
        int threshold = l1 - l2;
        for (int i = 0; i <= threshold; i++) {
            if (haystack.substring(i,i+l2).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
Runtime: 3 ms, faster than 99.58% of Java online submissions for Implement strStr().
Memory Usage: 37 MB, less than 100.00% of Java online submissions for Implement strStr().
=================================================================================================================
#35. Search Insert Position
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0
-------------------------------
class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while (low <= high) {
            int mid = Math.round((high+low)/2);
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                high = mid -1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
Runtime: 2 ms, faster than 100.00% of Java online submissions for Search Insert Position.
Memory Usage: 39.4 MB, less than 100.00% of Java online submissions for Search Insert Position.
=================================================================================================================
#67. Add Binary
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
---------------------------------
class Solution {
    public String addBinary(String a, String b) {
        if (a==null || b==null) {
            return (a==null)? b: a;
        }
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int p = a.length()-1, q = b.length()-1; p>=0 || q>=0|| carry>0; p--, q--) {
            int sum  = 0;
            sum += (p>=0)? a.charAt(p)-'0': 0;
            sum += (q>=0)? b.charAt(q)-'0': 0;
            sum += carry;
            carry = sum/2;
            sum = sum%2;
            sb.append(sum);
        }
        return sb.reverse().toString();
    }
}
Runtime: 1 ms, faster than 100.00% of Java online submissions for Add Binary.
Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Add Binary.
=================================================================================================================
#206. Reverse Linked List
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
------------------------------------

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
def reverseList(head):
    if head is None:
        return head
    new_head = head
    while head.next is not None:
        current = head.next
        head.next = head.next.next
        current.next = new_head
        new_head = current
    return new_head
*/

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode newHead = head;
        ListNode curr = null;
        while (head.next != null) {
            curr = head.next;
            head.next = head.next.next;
            curr.next = newHead;
            newHead = curr;
        }
        return newHead;
    }
}

// recursive
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
Memory Usage: 37.8 MB, less than 100.00% of Java online submissions for Reverse Linked List.
=================================================================================================================
#929. Unique Email Addresses
Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?



Example 1:

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails


Note:

1 <= emails[i].length <= 100
1 <= emails.length <= 100
Each emails[i] contains exactly one '@' character.
----------------------------------------
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for(String email : emails) {
            StringBuilder sb = new StringBuilder();
            for(char c: email.toCharArray()) {
                if (c == '.') continue;
                if (c == '+') break;
                sb.append(c);
            }
            String cur = sb.toString() + email.substring(email.indexOf('@'));
            set.add(cur);
        }
        return set.size();
    }
}
Runtime: 14 ms, faster than 95.34% of Java online submissions for Unique Email Addresses.
Memory Usage: 40.3 MB, less than 100.00% of Java online submissions for Unique Email Addresses.
=================================================================================================================
#83. Remove Duplicates from Sorted List
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
-----------------------------
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
Memory Usage: 37.7 MB, less than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
=================================================================================================================
#844. Backspace String Compare
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
------------------------------
class Solution {
    public String build(String s) {
            Stack<Character> ans = new Stack();
            for (char c: s.toCharArray()) {
                if (c!='#') {
                    ans.push(c);
                } else if (!ans.empty()) {
                    ans.pop();
                }
            }
            return String.valueOf(ans);
    }
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }
}
Runtime: 7 ms, faster than 32.22% of Java online submissions for Backspace String Compare.
Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Backspace String Compare.
----------------------------
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {skipS++; i--;}
                else if (skipS > 0) {skipS--; i--;}
                else break;
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {skipT++; j--;}
                else if (skipT > 0) {skipT--; j--;}
                else break;
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            i--; j--;
        }
        return true;
    }
}
=================================================================================================================
#112. Path Sum
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
----------------------------------------------
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        if ((root.left == null) && root.right == null)
            return (sum == 0);
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}

Runtime: 0 ms, faster than 100.00% of Java online submissions for Path Sum.
Memory Usage: 38.7 MB, less than 100.00% of Java online submissions for Path Sum.
=================================================================================================================