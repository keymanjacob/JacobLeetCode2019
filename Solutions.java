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

