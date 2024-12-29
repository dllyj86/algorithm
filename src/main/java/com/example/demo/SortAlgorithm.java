package com.example.demo;

import java.util.*;

/**
 * 这个类包含了所有排序算法
 */
public class SortAlgorithm {

    public static void main(String[] args) {

        bubbleSort(Utils.generateArray());
        selectionSort(Utils.generateArray());
        insertionSort(Utils.generateArray());
        mergeSort(Utils.generateArray());
        quickSort(Utils.generateArray());
        heapSort(Utils.generateArray());
    }

    public static void bubbleSort(int[] array) {

        System.out.println("bubbleSort received : " + Arrays.toString(array));

        for (int i = 0; i < array.length; i++) {
            // i负责记录有几个被排好序了
            for (int j = 0; j < array.length - 1 - i; j++) {
                // j用来循环比较. 因为j要和下一个j+1比较, 所以用 array.length - 1
                // array.length - 1 再 -i, 是为了去掉数组中处于尾部的已经排好序的部分
                if (array[j] > array[j + 1]) {
                    Utils.swap(array, j, j + 1);
                    System.out.println("bubbleSort process : " + Arrays.toString(array));
                }
            }
        }

        System.out.println("bubbleSort sorted : " + Arrays.toString(array));
    }

    public static void selectionSort(int[] array) {

        System.out.println("selectionSort received : " + Arrays.toString(array));
        // i表示已经排序的部分的下一个元素, 也就是未排序部分的第一个元素
        for (int i = 0; i < array.length; i++) {
            int minIdx = i; // 暂时设置成最小值的索引
            for (int j = minIdx; j < array.length; j++) {
                // 遍历所有未排序的元素,与最小值比较
                if (array[j] < array[minIdx]) {
                    // 遍历到更小的元素, 设置下标为最小值下标
                    minIdx = j;
                }
            }
            // 交换第一个未排序的元素和最小值元素
            Utils.swap(array, i, minIdx);

            System.out.println("selectionSort process : " + Arrays.toString(array));
        }

        System.out.println("selectionSort sorted : " + Arrays.toString(array));
    }

    public static void insertionSort(int[] array) {

        System.out.println("insertionSort received : " + Arrays.toString(array));

        for (int i = 0; i < array.length - 1; i++) {
            // i 是已排序的部分的末尾元素的下标.
            // i + 1 是未排序部分的第一个元素下标
            // 因为有 i + 1, 所以 i 的限制必须是 i < array.length
            int selectIdx = i + 1;
            for (int j = selectIdx; j > 0; j--) {
                // 用未排序的第一个元素, 与前面的已排序的元素, 倒序对比, 交换位置
                // 效果就相当于未排序的第一个元素依次向前移动, 直到没有元素比它小为止
                if (array[j] < array[j - 1]) {
                    Utils.swap(array, j, j - 1);
                    System.out.println("insertionSort process : " + Arrays.toString(array));
                } else {
                    System.out.println("insertionSort process : " + Arrays.toString(array));
                    break;
                }
            }
        }

        System.out.println("insertionSort sorted : " + Arrays.toString(array));
    }

    /**
     * 归并排序
     * @param array
     */
    public static void mergeSort(int[] array) {

        System.out.println("mergeSort start " + Arrays.toString(array));
        int[] result = splitAndMerge(array);
        System.out.println("mergeSort done " + Arrays.toString(result));
    }

    /**
     * 将数组分割成两部分
     * @param array
     * @return
     */
    public static int[] splitAndMerge(int[] array) {

        if (array.length <= 1) {
            return array;
        }
        // 取中点, 除以2. 右移一位就是除以2
        int middle = array.length >> 1;
        // copyOfRange都是左闭右开
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);
        // 递归去继续拆然后合并
        return merge(splitAndMerge(left), splitAndMerge(right));
    }

    /**
     * 归并排序中, 比较 - 合并 两个数组.
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        // 新数组的长度等于两个子数组之和
        int[] mergedArray = new int[left.length + right.length];

        int indexLeft = 0;
        int indexRight = 0;
        int indexMerged = 0;
        // 同时循环两个数组, 直到一个先循环完毕
        while (indexLeft < left.length && indexRight < right.length) {

            if (left[indexLeft] <= right[indexRight]) {
                mergedArray[indexMerged] = left[indexLeft];
                indexMerged++;
                indexLeft++;
            } else {
                mergedArray[indexMerged] = right[indexRight];
                indexMerged++;
                indexRight++;
            }
        }

        // 因为两个子数组都是从小到大排列好的, 所以将剩余的元素直接复制过去就行
        // Java的数组是固定长度的, 所以没有push, append等方法
        for (int i = indexLeft; i < left.length; i++) {
            mergedArray[indexMerged] = left[i];
            indexMerged++;
        }
        for (int i = indexRight; i < right.length; i++) {
            mergedArray[indexMerged] = right[i];
            indexMerged++;
        }

        return mergedArray;
    }

    public static void quickSort(int[] array) {
        System.out.println("quickSort start" + Arrays.toString(array));
        quickRecursion(array, 0, array.length - 1);
        System.out.println("quickSort done" + Arrays.toString(array));
    }

    public static void quickRecursion(int[] array, int low, int high) {
        System.out.println("quickSort received array : " + Arrays.toString(array));

        if (low < high) {
            Random random = new Random();
            // 因为nextInt是 [low, high) 区间, 不含high, 所以给 high 加 1, 让它包含high
            int randomPivotIndex = random.nextInt(low, high + 1);
            Utils.swap(array, randomPivotIndex, high);

            int pivotIndex = partition(array, low, high);

            quickRecursion(array, low, pivotIndex - 1);
            quickRecursion(array, pivotIndex + 1, high);
        }

    }

    public static int partition(int[] array, int low, int high) {

        int pivotValue = array[high];   // 最后一个元素作为基准值
        int endIndexOfLess = low - 1;
        for (int loopIndex = low; loopIndex < high; loopIndex++) {
            // 循环最后一个基准值之前的部分
            if (array[loopIndex] <= pivotValue) {
                // 值小于等于基准值, 则小于等于序列之后的下一个元素与当前遍历到的元素交换
                // 并扩展小于等于序列的结尾, 包入最新的一个值.
                // 如果序列后的下一个元素与当前遍历到的元素相同, 则自己和自己交换
                Utils.swap(array, endIndexOfLess + 1, loopIndex);
                endIndexOfLess++;
                // 上面两行可以简写成下面这句
                // swap(array, ++endIndexOfLess, loopIndex);
            }
        }

        Utils.swap(array, endIndexOfLess + 1, high);

        return endIndexOfLess + 1;
    }

    public static void heapSort(int[] array) {

        System.out.println("heapSort received : " + Arrays.toString(array));
        // 从最后一个非叶子节点开始处理, 生成大顶堆
        int lastNonLeafIndex = array.length / 2 - 1;
        // 循环结束时会生成大顶堆
        for (int i = lastNonLeafIndex; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
        System.out.println("heapSort heap : " + Arrays.toString(array));
        // 在得到大顶堆后, 将根节点 (index = 0的节点), 与数组最后面的元素交换.
        // 这时, 数组最后的元素会是所有数字中的最大值, 相当于排好序了.
        // 那么未排序的序列的长度就比数组长度少 1
        // 然后再从根节点开始下沉, 生成大顶堆.
        // 再将根节点与未排序的序列的最后一个元素交换, 相当于未排序的序列长度又减 1
        for (int i = array.length - 1; i > 0; i--) {
            Utils.swap(array, 0, i);
            downAdjust(array, 0, i);
        }

        System.out.println("heapSort sorted : " + Arrays.toString(array));
    }

    /**
     * 只负责将节点与子节点比较, 并适当下沉
     * 迭代调用, 就可以将整棵树处理成大顶堆
     *
     * @param array
     * @param parentIndex
     * @param length
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {

        int leftChildIndex = parentIndex * 2 + 1;   // 左子节点
        int rightChildIndex = parentIndex * 2 + 2; // 右子节点
        // 默认认为当前节点是较大的
        int largerIndex = parentIndex;

        // 注意, 是当前认为较大的节点(暂时是父节点)和左子节点比较
        if (leftChildIndex < length && array[largerIndex] < array[leftChildIndex]) {
            largerIndex = leftChildIndex;
        }
        // 注意, 是当前认为较大的节点(父节点或左子节点)和右节点比较
        if (rightChildIndex < length && array[largerIndex] < array[rightChildIndex]) {
            largerIndex = rightChildIndex;
        }
        // 检查是否更大的值的索引已经从开始的parentIndex被修改成子节点的索引了
        if (largerIndex != parentIndex) {
            // 父节点比子节点小, 则交换值.
            Utils.swap(array, parentIndex, largerIndex);
            // 此时 largerIndex是两个 子节点 中的一个, 交换值后,
            // 相当于较小的父节点的值被放到子节点了, 然后继续向下比较下沉.
            // 第三个参数必须是downAdjust接受的"length"长度, 不能是array.length
            // 因为在最后排序阶段时, 这个length是未排序的序列的长度, 而不是整个数组的长度
            downAdjust(array, largerIndex, length);
        }
    }

}