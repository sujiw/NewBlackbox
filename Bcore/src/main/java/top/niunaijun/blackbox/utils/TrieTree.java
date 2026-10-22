package top.niunaijun.blackbox.utils;

import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/TrieTree.class */
public class TrieTree {
    private final TrieNode root = new TrieNode();

    /* JADX INFO: loaded from: classes.jar:top/niunaijun/blackbox/utils/TrieTree$TrieNode.class */
    private static class TrieNode {
        char content;
        String word;
        boolean isEnd = false;
        List<TrieNode> children = new LinkedList();

        public TrieNode() {
        }

        public TrieNode(char content, String word) {
            this.content = content;
            this.word = word;
        }

        public boolean equals(Object object) {
            return (object instanceof TrieNode) && ((TrieNode) object).content == this.content;
        }

        public TrieNode nextNode(char content) {
            for (TrieNode childNode : this.children) {
                if (childNode.content == content) {
                    return childNode;
                }
            }
            return null;
        }
    }

    public void add(String word) {
        TrieNode current = this.root;
        StringBuilder wordBuilder = new StringBuilder();
        for (int index = 0; index < word.length(); index++) {
            char content = word.charAt(index);
            wordBuilder.append(content);
            TrieNode node = new TrieNode(content, wordBuilder.toString());
            if (current.children.contains(node)) {
                current = current.nextNode(content);
            } else {
                current.children.add(node);
                current = node;
            }
            if (index == word.length() - 1) {
                current.isEnd = true;
            }
        }
    }

    public void addAll(List<String> words) {
        for (String word : words) {
            add(word);
        }
    }

    public String search(String word) {
        TrieNode current = this.root;
        for (int index = 0; index < word.length(); index++) {
            char content = word.charAt(index);
            TrieNode node = new TrieNode(content, null);
            if (current.children.contains(node)) {
                current = current.nextNode(content);
                if (current.isEnd) {
                    return current.word;
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
