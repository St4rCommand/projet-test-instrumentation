package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romain on 03/04/17.
 */
public class TreeBuilder {

    private static PrintWriter fileWriter;
    private static TreeNode currentTreeNode;
    private static List<TreeNode> treeNodes = new ArrayList();

    public static void beginMethod(String label) {

        TreeNode newNode = new TreeNode();
        newNode.setName(label);
        newNode.setParentTreeNode(currentTreeNode);

        if (currentTreeNode != null) {
            currentTreeNode.addChildTreeNode(newNode);
        }

        treeNodes.add(newNode);
        currentTreeNode = newNode;
    }

    public static void endMethod() {
        currentTreeNode = currentTreeNode.getParentTreeNode();
    }

    public static void writeLog() {

        try {
            PrintWriter writer = getWriter();

            writer.write(TreeBuilder.buildTree(TreeBuilder.currentTreeNode, ""));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileWriter.close();
    }

    protected static String buildTree(TreeNode currentNode, String separator) {
        String raw = separator + currentNode.getName() + "\n";
        List<TreeNode> childrenTreeNode = currentNode.getChildrenTreeNodes();

        for (TreeNode child: childrenTreeNode) {
            raw += buildTree(child, separator + " | ");
        }

        return raw;
    }

    protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            ShutdownHookTree shutdownHook = new ShutdownHookTree();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }
}
