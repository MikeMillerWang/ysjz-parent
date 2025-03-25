import cn.hutool.core.lang.Console;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ysjz.entity.Road;
import com.ysjz.pattern.adapter.Adaptee;
import com.ysjz.pattern.adapter.Adapter;
import com.ysjz.pattern.adapter.Target;
import com.ysjz.service.NumberFactory;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestDesign {

    /**
     * 简化路径
     */
    @Test
    public void test19() {
        String path = "/home///foo/";
        String slash = "//";

        while (path.contains(slash)) {
            path = path.replaceAll(slash, "/");
        }

        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        /*for (int i = path.length() - 1; i >= 0; i--) {
            if
        }*/

        System.out.println(path);
    }

    /**
     * 爬楼梯
     */
    @Test
    public void test18() {
        int a = 3;
        int x = 1, y = 2, sum = 0;

        for (int i = 3; i <= a; i++) {
            sum = x + y;
            x = y;
            y = sum;
        }

        System.out.println(y);
    }

    /**
     * x 的平方根
     */
    @Test
    public void test17() {
        int x = 4;
        double sqrt = x;
        int last = 0;

        while (true) {
            sqrt = (sqrt + x / sqrt) / 2;
            if ((int) sqrt == last) {
                break;
            }
            last = (int) sqrt;
        }
        System.out.println((int)sqrt);

        /*int sqrt = (int) Math.sqrt(x);
        System.out.println(sqrt);*/
    }

    /**
     * 二进制求和
     */
    @Test
    public void test16() {
        String a = "1010", b = "1011";
        // 110001

        char c1 = '1';
        System.out.println(c1 - '1');

        // 官方题解
        /*String s = Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
        System.out.println(s);*/

        /*int aLen = a.length();
        int bLen = b.length();

        if (aLen < bLen) {
            String temp = a;
            a = b;
            b = temp;

            aLen = a.length();
            bLen = b.length();
        }

        int i = 1;
        String res = "";
        char c1 = '0';
        while (aLen - i >= 0 && bLen - i >= 0) {
            char bCh = b.charAt(bLen - i);
            if (a.charAt(aLen - i) == '1' && bCh == '1' && c1 == '0') {
                res = '0' + res;
                c1 = '1';
            } else if (a.charAt(aLen - i) == '0' && bCh == '0' && c1 == '0') {
                res = '0' + res;
                c1 = '0';
            } else if (a.charAt(aLen - i) == '1' && bCh == '1' && c1 == '1') {
                res = '1' + res;
                c1 = '1';
            } else if (a.charAt(aLen - i) == '0' && bCh == '1' && c1 == '1') {
                res = '0' + res;
                c1 = '1';
            } else if (a.charAt(aLen - i) == '1' && bCh == '0' && c1 == '1') {
                res = '0' + res;
                c1 = '1';
            } else {
                res = '1' + res;
                c1 = '0';
            }
            i++;
        }

        // String a = "110111", b = "101"; 111100
        //              10111
        // 111010
        while (aLen - i >= 0) {
            char c = a.charAt(aLen - i);
            if (c1 == '1' && c == '1') {
                res = '0' + res;
                c1 = '1';
            } else if (c1 == '1' && c == '0') {
                res = '1' + res;
                c1 = '0';
            } else {
                res = c + res;
                c1 = '0';
            }
            i++;
        }

        if (c1 == '1') {
            res = '1' + res;
        }

        System.out.println(res);*/
    }

    /**
     * 加一
     */
    @Test
    public void test15() {
        int[]digits = {9};
        // 9,0,0,0
        int[] res = plusOne(digits);
        System.out.println(JSONUtil.toJsonStr(res));

        /*有误：int[] res;
        if (digits[0] == 9 ) {
             res = new int[digits.length + 1];
        } else {
             res = new int[digits.length];
        }
        int len = digits.length - 1;
        for (int i = len; i >= 0; i--) {
            int index;
            if (digits[0] == 9) {
                index = i + 1;
            } else {
                index = i;
            }
            int digit = digits[i];
            if (i == len && digit != 9) {
                res[index] = digit + 1;
            } else if (digit == 9) {
                res[index] = 0;
            } else if (digits[i + 1] == 9) {
                res[index] = digit + 1;
            } else {
                res[index] = digit;
            }
        }

        if (res[0] == 0) {
            res[0] = 1;
        }*/

        /*有误：int[] res = new int[digits.length + 1];
        if (digits[0] == 9) {
            res[0] = 1;
            res[1] = 0;

            for (int i = 1; i < digits.length; i++) {
                if (digits[i] == 9) {
                    res[i + 1] = 0;
                } else if (i == digits.length - 1) {
                    res[i + 1] = digits[i] + 1;
                } else {
                    res[i + 1] = digits[i];
                }
            }
        } else {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
            res = digits;
        }*/
    }

    private int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        // 跳出for循环，说明数字全部是9
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    /**
     * 最后一个单词的长度
     */
    @Test
    public void test14() {
        String s = "Hello World";
        /*String[] s1 = s.split(" ");
        int res = 0;
        for (int i = s1.length - 1; i >= 0 ; i--) {
            String s2 = s1[i];
            if (!"".equals(s2)) {
                res = s2.length();
                break;
            }
        }*/
        String s1 = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c != ' ') {
                s1 = c + s1;
            }

            if (c == ' ' && !"".equals(s1)) {
                break;
            }
        }
        System.out.println(s1);
    }

    /**
     * 搜索插入位置
     */
    @Test
    public void test13() {
        int[] nums = {1,3,5,6};
        int target = 7;
        int res = nums.length;
        for (int i = 0; i < res; i++) {
            if (nums[i] >= target) {
                res = i;
                break;
            }
        }
        System.out.println(res);
    }

    /**
     * 找出字符串中第一个匹配项的下标
     * KMP-字符串匹配算法
     * LPS-部分匹配表
     */
    @Test
    public void test12() {
        String haystack = "mississippi", needle = "issip";
        // int i = haystack.indexOf(needle);
        System.out.println(buildLPS(needle, haystack, haystack, 0));
    }

    private int buildLPS(String needle, String oldHaystack, String newHaystack, int res) {
        int firstLen = needle.length();
        int secondLen = newHaystack.length();

        if (firstLen > secondLen) {
            return -1;
        }

        for (int i = 0; i < firstLen; i++) {
            if (needle.charAt(i) != newHaystack.charAt(i)) {
                int res1 = i + 1;
                if (res1 > secondLen) {
                    res = -1;
                    break;
                }
                res = buildLPS(needle, oldHaystack, oldHaystack.substring(res + 1), res + 1);
                break;
            }
        }

        return res;
    }

    /**
     * 移除元素
     */
    @Test
    public void test11() {
        int[] nums = {3,2,2,3};
        int val = 3;

        int fast = 0;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
    }

    /**
     * 删除有序数组中的重复项
     */
    @Test
    public void test10() {
        int[] nums = {1,2};
        int j = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i+1 < nums.length && nums[i] != nums[i+1]) {
                nums[i] = nums[j];
                j++;
            }
            if (i+1 == nums.length && j < i+1) {
                nums[i] = 999;
            }
        }
        /*LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int[] result = set.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < result.length; i++) {
            nums[i] = result[i];
        }*/
        /*int[] result = new int[nums.length];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!set.contains(num)) {
                result[set.size()] = num;
            }
            set.add(num);
        }
        for (int i = 0; i < result.length; i++) {
            nums[i] = result[i];
        }*/
    }

    /**
     * 合并两个有序链表
     */
    @Test
    public void test9() {
        ListNode list2 = new ListNode(0);
        ListNode list1 = null;

        ListNode l1 = recurveNode(list1, list2);
        // ListNode l2 = recurveNode(l1, l1.next);
        System.out.println(JSONUtil.toJsonStr(l1));
    }

    private ListNode recurveNode(ListNode next1, ListNode next2) {
        if (null == next1 && null == next2) {
            return null;
        }

        if (null == next1 && null != next2) {
            return next2;
        }

        if (null != next1 && null == next2) {
            return next1;
        }

        int val1 = next1.val;
        ListNode list1 = next1.next;

        int val2 = next2.val;
        ListNode list2 = next2.next;
        if (val1 <= val2) {
            return new ListNode(val1, recurveNode(next2, list1));
        } else {
            return new ListNode(val2, recurveNode(next1, list2));
        }
    }

    @Data
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 最长公共前缀 二解
     */
    @Test
    public void test8() {
        String[] strs  = {"dog","racecar","car"};
        String result = "";
        strs = Arrays.stream(strs).sorted().toArray(String[]::new);
        for (int i = 0; i < strs[0].length(); i++) {
            if (strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
                result += strs[0].charAt(i);
            } else {
                break;
            }
        }
        System.out.println(result);
    }

    /**
     * 最长公共前缀 一解
     */
    @Test
    public void test7() {
        String[] strs  = {"a", "a"};
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char c1 = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() < strs[0].length() && i == strs[j].length()) {
                    System.out.println(result);
                    return;
                }
                char c2 = strs[j].charAt(i);
                // 有不相等的就break
                if (c2 != c1) {
                    System.out.println(result);
                    return;
                }
                // 表示是最后一个字符串的时候再追加
                if (j == strs.length - 1) {
                    result.append(c1);
                }
            }
        }
        System.out.println(result);
    }

    /**
     * 罗马数字转整数
     */
    @Test
    public void test5() {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        String s = "MCMXCIV";
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if ('V' == c && i - 1 >= 0 && 'I' == s.charAt(i - 1) ) {
                result += 4;
                i--;
                continue;
            }
            if ('X' == c && i - 1 >= 0 && 'I' == s.charAt(i - 1)) {
                result += 9;
                i--;
                continue;
            }
            if ('L' == c && i - 1 >= 0 && 'X' == s.charAt(i - 1)) {
                result += 40;
                i--;
                continue;
            }
            if ('C' == c && i - 1 >= 0 && 'X' == s.charAt(i - 1)) {
                result += 90;
                i--;
                continue;
            }
            if ('D' == c && i - 1 >= 0 && 'C' == s.charAt(i - 1)) {
                result += 400;
                i--;
                continue;
            }
            if ('M' == c && i - 1 >= 0 && 'C' == s.charAt(i - 1)) {
                result += 900;
                i--;
                continue;
            }
            if (map.containsKey(c)) {
                result += map.get(c);
            }
        }
        System.out.println(result);
    }

    /**
     * 罗马数字转整数 - 官方题解
     */
    @Test
    public void test6() {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        String s = "MCMXCIV";
        int result = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                result -= value;
            } else {
                result += value;
            }
        }
        System.out.println(result);
    }

    @Test
    public void test1() {
        Number parse = NumberFactory.parse("1.11");
        Console.log(parse);
    }

    @Test
    public void test2() {
        String build = UrlBuilder.ofHttp("http://www.baidu.com").build();
        Console.log(build);
    }

    @Test
    public void test3() {
        int i = 1;
        while(i < 5) {
            if (i == 3) {
                // break; // 终止循环
                // i++;
                // continue; // 跳出本次循环，继续下次循环
                return; // 终止循环，与break效果一致
            }
            System.out.println(i);
            i++;
        }

        /*// for循环，fori循环 一样
        List<String> list = Arrays.asList("李白", "杜甫", "白居易", "高适", "杜牧");
        for (int i = 0; i < list.size(); i++) {
            String x = list.get(i);
            if ("白居易".equals(x)) {
                // break; // 终止循环
                // continue; // 跳出本次循环，继续下次循环
                // return; // 终止循环，与break效果一致
            }
            System.out.println(x);
        }

        List<String> list = Arrays.asList("李白", "杜甫", "白居易", "高适", "杜牧");
        for (String x : list) {
            if ("白居易".equals(x)) {
                // break; // 终止循环
                // continue; // 跳出本次循环，继续下次循环
                return; // 终止循环，与break效果一致
            }
            System.out.println(x);
        }

        // foreach中不能使用break，continue，编译就会报错
        // 在foreach中使用 return相当于continue，与continue效果一样
        List<String> list = Arrays.asList("李白", "杜甫", "白居易", "高适", "杜牧");
        list.forEach(x -> {
            if ("白居易".equals(x)) {
                // break;
                // continue;
                return;
            }
            System.out.println(x);
        });*/
    }

    @Test
    public void test4() {
        Road road1 = new Road("荷花大道_东段", 100.2, "荷花社区");
        Road road2 = new Road("荷花大道西段", 88.2, "荷花社区");
        Road road3 = new Road("剑北大道_东段", 99.2, "剑北社区");
        Road road4 = new Road("剑北大道西段", 111.4, "剑北社区");
        Road road5 = new Road("剑北大道_南段", 68.9, "剑北社区");

        List<Road> roadList = new ArrayList<>();
        roadList.add(road1);
        roadList.add(road2);
        roadList.add(road3);
        roadList.add(road4);
        roadList.add(road5);

        // ①根据 社区属性 分组
        // 两种方式皆可
        Map<String, List<Road>> roadMap1 = roadList.stream().collect(Collectors.groupingBy(Road::getCommunity));
        Map<String, List<Road>> roadMap2 = roadList.stream().collect(Collectors.groupingBy(Road::getCommunity, Collectors.toList()));

        // ②将 道路长度大于100 的分为一组
        Map<String, List<Road>> roadMap3 = roadList.stream().collect(Collectors.groupingBy(x -> {
            if (x.getLen() > 100) {
                return "大于100";
            } else {
                return "小于等于100";
            }
        }));

        // ③想得到一个通过道路长度分组并有序的结果（此处道路长度都不一样，没必要分组，只是想体现有序的返回）
        TreeMap<Double, List<Road>> roadMap4 = roadList.stream().collect(Collectors.groupingBy(Road::getLen, TreeMap::new, Collectors.toList()));

        // ④将名字中的 大道 替换为'' 并进行分组（主要是这个，平时用的时候，有时会搞忘）
        Map<String, List<Road>> roadMap5 = roadList.stream().collect(Collectors.groupingBy(x -> x.getName().replace("_", ""), Collectors.toList()));

        // 按自己习惯打印输出，roadMap1...roadMap5，进行结果验证比对
    }

    public static void main(String[] args) {
        // 适配器模式
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.request(); // 客户端只与目标接口交互
    }
}
