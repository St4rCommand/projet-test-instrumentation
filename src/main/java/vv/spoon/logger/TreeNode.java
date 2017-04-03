package vv.spoon.logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romain on 03/04/17.
 */
public class TreeNode {

    private String name;
    private List<TreeNode> childrenTreeNodes = new ArrayList();
    private TreeNode parentTreeNode;

    public void addChildTreeNode(TreeNode treeNode) {
        this.childrenTreeNodes.add(treeNode);
    }

    public TreeNode getParentTreeNode() {
        return parentTreeNode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentTreeNode(TreeNode parentTreeNode) {
        this.parentTreeNode = parentTreeNode;
    }

    public String getName() {
        return name;
    }

    public List<TreeNode> getChildrenTreeNodes() {
        return childrenTreeNodes;
    }
}
