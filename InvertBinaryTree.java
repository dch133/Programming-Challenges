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
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
        
    }
    
    public void invert(TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            return;
        }
        else if (node.left != null && node.right == null) {
            node.right = node.left;
            node.left = null;
            invertTree(node.right);
        }
        else if (node.left == null && node.right != null) {
            node.left = node.right;
            node.right = null;
            invertTree(node.left);
        }
        else {
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            invertTree(node.left);
            invertTree(node.right);

        }
    }
        
}