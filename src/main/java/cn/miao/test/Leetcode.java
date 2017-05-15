package cn.miao.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;

public class Leetcode {

	public static void main(String[] args) {
		int[] ints = twoSum(new int[]{3, 5, 7, 1, 8}, 9);
		System.err.println(JSON.toJSONString(ints));
	}

	public static int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		Map<Integer, Integer> numMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (numMap.containsKey(target - nums[i])) {
				res[0] = i;
				res[1] = numMap.get(target - nums[i]);
				return res;
			}
			numMap.put(nums[i], i);

		}
		return res;
	}

	@Test
	public void testAddTwoNumbers(){
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(6);
		ListNode listNode = addTwoNumbers(l1, l2);
		System.err.println(listNode);
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode c1 = l1;
		ListNode c2 = l2;
		ListNode sentinel = new ListNode(0);
		ListNode d = sentinel;
		int sum = 0;
		while (c1 != null || c2 != null) {
			sum /= 10;
			if (c1 != null) {
				sum += c1.val;
				c1 = c1.next;
			}
			if (c2 != null) {
				sum += c2.val;
				c2 = c2.next;
			}
			d.next = new ListNode(sum % 10);
			d = d.next;
		}
		if (sum / 10 == 1)
			d.next = new ListNode(1);
		return sentinel.next;
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	@Test
	public void testLengthOfLongestSubstring(){
		int length = lengthOfLongestSubstring2("pwwkew");
		System.err.println(length);
	}

	public int lengthOfLongestSubstring2(String s) {
		if (s.length()==0) return 0;
		HashMap<Character, Integer> map = new HashMap<>();
		int max=0;
		for (int i=0, j=0; i<s.length(); ++i){
			char c = s.charAt(i);
			if (map.containsKey(c)){
				j = Math.max(j,map.get(c)+1);
			}
			max = Math.max(max,i-j+1);
		}
		return max;
	}

	public int lengthOfLongestSubstring(String str) {
		int maxLength = 0;
		Map<String, Integer> charMap = new HashMap<>();
		char[] chars = str.toCharArray();
		String charStr = "";
		for (int i=0;i<chars.length;i++) {
			if(i==0) {
				charStr += chars[i];
				charMap.put(charStr, charStr.length());
				maxLength = 1;
				continue;
			}
			int index = charStr.indexOf(chars[i]);
			charStr += chars[i];
			if (index == -1) {
				if (charStr.length() > maxLength) {
					maxLength = charStr.length();
				}
			}else{
				charStr = charStr.substring(index+1, charStr.length());
			}
			charMap.put(charStr, charStr.length());
		}
		return maxLength;
	}

	@Test
	public void findMedianSortedArrays(){
		int[] nums1 = {};
		int[] nums2 = {1};
		double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
		System.err.println(medianSortedArrays);
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		return 1.0;
	}

	@Test
	public void longestPalindrome(){
		String str = longestPalindrome("abcdcbefghgfeb");
		System.err.println(str);
	}

	public static String longestPalindrome(String str) {
		char[] chars = str.toCharArray();
		LinkedList<Character> charLinkedList = new LinkedList<>();
		for (int i = 0; i < chars.length; i++) {
			charLinkedList.push(chars[i]);
			if(charLinkedList.contains(""))
			plindrome(charLinkedList, i);
		}
		return "";
	}

	private static String plindrome(LinkedList charLinkedList, int i) {
		Object pop = charLinkedList.pop();

		return "";
	}


}