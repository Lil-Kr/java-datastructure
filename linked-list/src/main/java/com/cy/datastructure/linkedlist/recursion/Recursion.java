package com.cy.datastructure.linkedlist.recursion;

/**
 * @Author: Lil-K
 * @Date: 2023/2/22
 * @Description: 链表 递归
 */
public class Recursion {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int sum = sum(arr, 0);
        System.out.println(sum);
    }

    public static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }
}