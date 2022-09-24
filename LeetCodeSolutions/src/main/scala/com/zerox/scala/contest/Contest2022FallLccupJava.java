package com.zerox.scala.contest;

import java.util.HashSet;

/**
 * @author ZeromaXHe
 * @apiNote LCCUP 22 力扣杯秋季编程大赛（前两题不能用 Scala）
 * @implNote
 * @since 2022/9/24 15:01
 */
public class Contest2022FallLccupJava {
    /**
     * 1. 气温变化趋势 | 难度：简单
     *
     * @param temperatureA
     * @param temperatureB
     * @return
     */
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        int max = 0;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            int trendA = Integer.compare(temperatureA[i + 1], temperatureA[i]);
            int trendB = Integer.compare(temperatureB[i + 1], temperatureB[i]);
            if (trendA == trendB) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }

    /**
     * 2. 交通枢纽 | 难度：中等
     *
     * @param path
     * @return
     */
    public int transportationHub(int[][] path) {
        int max = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int[] ints : path) {
            if (ints[0] > max) max = ints[0];
            if (ints[1] > max) max = ints[1];
            set.add(ints[0]);
            set.add(ints[1]);
        }
        int[][] inOuts = new int[max + 1][2];
        for (int[] ints : path) {
            inOuts[ints[0]][1]++;
            inOuts[ints[1]][0]++;
        }
        for (int i = 0; i <= max; i++) {
            int[] io = inOuts[i];
            if (io[0] == set.size() - 1 && io[1] == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Contest2022FallLccupJava().transportationHub(new int[][]{{0, 1}, {0, 3}, {1, 3}, {2, 0}, {2, 3}}));
    }
}
