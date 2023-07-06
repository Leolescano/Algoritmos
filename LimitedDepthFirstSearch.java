package trabajopractico01;

import java.util.LinkedList;
import java.util.Queue;

public class LimitedDepthFirstSearch {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}
	public static TreeNode generateTree(int levels) {
		if (levels <= 0) {
			return null;
		}

		TreeNode root = new TreeNode(1);
		generateSubTree(root, levels - 1);

		return root;
	}
	private static void generateSubTree(TreeNode node, int levels) {
		if (levels <= 0) {
			return;
		}

		node.left = new TreeNode(node.val * 2);
		node.right = new TreeNode(node.val * 2 + 1);

		generateSubTree(node.left, levels - 1);
		generateSubTree(node.right, levels - 1);
	}


	public static int limitedDepthBinarySearch(TreeNode root, int target, int depthLimit) {
		return limitedDFS(root, target, depthLimit, 0);
	}

	private static int limitedDFS(TreeNode node, int target, int depthLimit, int depth) {
		if (node == null) {
			return -1;
		}

		if (node.val == target) {
			return depth;
		}

		if (depth == depthLimit) {
			return -1;
		}

		int leftResult = limitedDFS(node.left, target, depthLimit, depth + 1);
		if (leftResult != -1) {
			return leftResult;
		}

		int rightResult = limitedDFS(node.right, target, depthLimit, depth + 1);
		if (rightResult != -1) {
			return rightResult;
		}

		return -1;
	}

//	public static void printTree(TreeNode root) {
//		if (root == null) {
//			System.out.println("El árbol está vacío.");
//			return;
//		}
//
//		Queue<TreeNode> queue = new LinkedList<>();
//		queue.offer(root);
//
//		int levelSize = 1;
//		int currentSize = 0;
//
//		while (!queue.isEmpty()) {
//			for (int i = 0; i < levelSize; i++) {
//				TreeNode node = queue.poll();
//				System.out.print(node.val + " ");
//
//				if (node.left != null) {
//					queue.offer(node.left);
//					currentSize++;
//				}
//
//				if (node.right != null) {
//					queue.offer(node.right);
//					currentSize++;
//				}
//			}
//
//			System.out.println();
//			levelSize = currentSize;
//			currentSize = 0;
//		}
//	}
	public static void printTree(TreeNode root) {
		printTreeHelper(root, "", true);
	}

	private static void printTreeHelper(TreeNode node, String prefix, boolean isLeft) {
		if (node == null) {
			return;
		}

		System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);

		String childPrefix = prefix + (isLeft ? "│   " : "    ");
		printTreeHelper(node.left, childPrefix, true);
		printTreeHelper(node.right, childPrefix, false);
	}

	public static void main(String[] args) {
		int levels = 5;

		TreeNode root = generateTree(levels);

		int target = 26;
		int depthLimit = 3;

		int level = limitedDepthBinarySearch(root, target, depthLimit -1);
		if (level != -1) {
			System.out.println("El número " + target + " se encuentra en el nivel " + (level + 1));
		} else {
			System.out.println("El número " + target + " no se encuentra dentro del árbol dentro del límite de profundidad " + depthLimit);
		}
		System.out.println();
		printTree(root);
	}
}



