package CombatClassification;

import data.CombatD;
import enums.PokemonEnums.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anders Holst, Sabina Hult & Line Kreiberg
 * Representing a node in the decision tree
 */
public class Node {
    private Node parent = null;
    private List<Node> children;
    // the partitioned data set
    private List<CombatD> data;
    // list of attributes minus the attribute we split on to get here
    private List<Object> attributes;
    private CombatClass classLabel = null;
    // the attribute we split on to get to this node
    private Object splitting_criterion;
    // tha attribute value that got us to this node
    private Object value;

    private boolean isLeaf;

    public Node(List<CombatD> data, List<Object> attributes) {
        this.attributes = attributes;
        this.data = data;
        children = new ArrayList<>();
        isLeaf = false;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    // set class label
    public void setLabel(Object classLabel) {
        if(classLabel.equals(CombatClass.FIRSTWIN)) {
            this.classLabel = CombatClass.FIRSTWIN;
        } else if(classLabel.equals(CombatClass.SECONDWIN))this.classLabel = CombatClass.SECONDWIN;
    }

    // label node with splitting criterion
    public void setSplittingCriterion(Object attribute) {
        splitting_criterion = attribute;
    }

    public Object getSplitting_criterion() {
        return splitting_criterion;
    }

    public List<Object> getAttributes() { return attributes; }

    public void addChild(Node child) {
        children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    public List<CombatD> getData() {
        return data;
    }

    public CombatClass getClassLabel() {
        return classLabel;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setLeaf() { isLeaf = true; }

    public boolean isLeaf() { return isLeaf; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(parent != null) sb.append("Attribute: ").append(parent.getSplitting_criterion().toString()
                .replace("class enums.CombatEnums.Combat", "")).append("\n");

        sb.append("Value: ").append(value).append("\n");
        sb.append("Data Size: ").append(data.size()).append("\n");
        sb.append("Children: ").append(children.size()).append("\n");

        if(isLeaf) sb.append("CLASS LABEL: ").append(classLabel).append("\n");
        else {
            sb.append("Att List:");
            String del = "";
            for(Object att : attributes) {
                sb.append(del).append(" ").append(att.toString().replace("class enums.CombatEnums.", ""));
                del = ", ";
            }
            sb.append("\n").append("Next SplitCrit: ").append(splitting_criterion.toString()
                    .replace("class enums.CombatEnums.Combat", "")).append("\n");
        }

        return sb.toString();
    }
}
