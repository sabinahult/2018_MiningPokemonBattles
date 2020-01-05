package CombatClassification;

import data.*;
import enums.PokemonEnums.*;
import java.util.*;

/**
 * @author Anders Holst, Sabina Hult & Line Kreiberg
 * Attempt at classifying if pokemon 1 or pokemon 2 wins a combat.
 */
public class ID3 {
    private Node root;
    private List<CombatD> dataset;

    public ID3(List<CombatD> combats) {
        this.dataset = combats;
        root = generateDecisionTree(dataset, CombatD.getAttributeList());
    }

    /**
     * @return the root node of the decision tree
     */
    public Node getDecisionTree() {
        return root;
    }

    /**
     * Train the decision tree on a list of discretized combat objects and a list of attributes
     * @param data a list of discretized combat objects
     * @param attributes_list list of attributes
     * @return - the decision tree
     */
    private Node generateDecisionTree(List<CombatD> data, List<Object> attributes_list) {
        Node node = new Node(data, attributes_list);
        //System.out.println("Majority Class Label: " + getMajorityClassLabel(data));

        // if all tuples have same class label, return node with that class label
        if(calculateEntropyForSet(data) == 0.0) {
            node.setLabel(getMajorityClassLabel(data));
            node.setLeaf();
            return node;
        }

        // if there's no attributes, return node with majority class label
        if(attributes_list.isEmpty()) {
            node.setLabel(getMajorityClassLabel(data));
            node.setLeaf();
            return node;
        }

        // get best attribute to split by
        Object splitting_criterion = attributeSelection(data, attributes_list);
        node.setSplittingCriterion(splitting_criterion);
        attributes_list.remove(splitting_criterion);

        List<Object> att_list = new ArrayList<>();
        att_list.addAll(attributes_list);

        // partition data into sets by their value
        Map<Object, List<CombatD>> partitions = new HashMap<>();
        for(Object value : ((Class) splitting_criterion).getEnumConstants()) {
            // looking at one value for the attribute selected as splitting criterion
            partitions.put(value, new ArrayList<>());
            for(CombatD combat : data) {
                if(combat.getAttributeValue(splitting_criterion) == value) {
                    partitions.get(value).add(combat);
                }
            }
        }

        // branch the tree
        for(Object value : partitions.keySet()) {
            if(partitions.get(value).isEmpty()) {
                // creating leaf node
                Node leaf = new Node(partitions.get(value), att_list);
                // setting class label of leaf to majority
                leaf.setLabel(getMajorityClassLabel(data));
                leaf.setLeaf();
                leaf.setValue(value);
                leaf.setParent(node);
                node.addChild(leaf);
            } else {
                // recursively continue the generation of the decision tree
                Node child = generateDecisionTree(partitions.get(value), att_list);
                child.setValue(value);
                child.setParent(node);
                node.addChild(child);
            }
        }
        return node;
    }

    /**
     * Determine the most common class label in the node
     * @param data list of CombatD objects
     * @return class label
     */
    private Object getMajorityClassLabel(List<CombatD> data) {
        int poke1Win = 0;
        int poke2Win = 0;
        for(CombatD combat : data) {
            if(combat.getAttributeValue(CombatClass.class).equals(CombatClass.FIRSTWIN)) poke1Win++;
            else poke2Win++;
        }

        if(poke1Win > poke2Win) return CombatClass.FIRSTWIN;
        return CombatClass.SECONDWIN;
    }


    /**
     * Determines the best splitting attribute byt calculating information  gain
     * @param dataset list of CombatD objects
     * @param attributes the list of attributes
     * @return split attribute with maximum information gain
     */
    private Object attributeSelection(List<CombatD> dataset, List<Object> attributes) {
        double entropy = calculateEntropyForSet(dataset);

        HashMap<Double, Object> gainPerAttribute = new HashMap<>();
        double maxGain = -Double.MAX_VALUE;
        for(Object att : attributes) {
                // looking at one attribute
                double infoForAttribute = 0;
                for (Object value : ((Class) att).getEnumConstants()) {
                    // looking at one attribute value and at their class label
                    double poke1Win = CountClassInstancesBasedOnAttributeValue(dataset,
                            att, value, CombatClass.FIRSTWIN);
                    double poke2Win = CountClassInstancesBasedOnAttributeValue(dataset,
                            att, value, CombatClass.SECONDWIN);

                    // how many respondents with this specific attribute value in total ( p. 338 Info-attribute(D) )
                    double totalAmountWithAttValue = poke1Win + poke2Win;

                    double infoForPoke1Win = 0.0;
                    if (poke1Win > 0) { // if the number is 0 then there's no information to be gained from this
                        infoForPoke1Win = (-(poke1Win / totalAmountWithAttValue))
                                * logBase2(poke1Win / totalAmountWithAttValue);
                    }

                    double infoForPoke2Win = 0.0;
                    if (poke2Win > 0) { // if the number is 0 then there's no information to be gained from this
                        infoForPoke2Win = (-(poke2Win / totalAmountWithAttValue))
                                * logBase2(poke2Win / totalAmountWithAttValue);
                    }

                    double infoPerValue = (totalAmountWithAttValue / dataset.size()) * (infoForPoke1Win + infoForPoke2Win);
                    infoForAttribute += infoPerValue;
                }

                // p. 339 Gain(attribute) - how much will splitting by this attribute lower the amount of entropy
                double infoGain = entropy - infoForAttribute;
                gainPerAttribute.put(infoGain, att);

                // set the max gain for easy retrieval in the map
                if(infoGain > maxGain)  { maxGain = infoGain; }

            }
        // return the attribute with the highest information gain
        return gainPerAttribute.get(maxGain);
    }

    /**
     * Counts the number of instances where the respondents has value in attribute and class label
     * @param dataset list of combatD objects
     * @param att the relevant attribute
     * @param value the given value
     * @param classLabel the class label
     * @return the count of instances with the given attribute value and class label
     */
    private double CountClassInstancesBasedOnAttributeValue(List<CombatD> dataset, Object att, Object value, CombatClass classLabel) {
        int count = 0;
        for(CombatD combat : dataset) {
            if(combat.getAttributeValue(att).equals(value) && combat.getClassLabel().equals(classLabel)) count++;
        }
        return count;
    }

    /**
     * For use in calculating information gain we need the entropy for the entire set on that class label (p. 338 Info(D))
     * @param dataset list of CombatD objects
     * @return entropy as a double
     */
    private static double calculateEntropyForSet(List<CombatD> dataset) {
        int size = dataset.size();
        double poke1Win = 0.0;
        double poke2Win = 0.0;

        for(CombatD tuple : dataset) {
            if(tuple.getAttributeValue(CombatClass.class).equals(CombatClass.FIRSTWIN)) {
                poke1Win++;
            } else poke2Win++;
        }

        // if one is 0, then don't do calculation
        if(poke1Win == 0.0 || poke2Win == 0.0) return 0.0;

        return (-(poke1Win / size)) * logBase2(poke1Win / size) +
                (-(poke2Win / size)) * logBase2(poke2Win / size);
    }

    private static double logBase2(double amount) {
        return Math.log(amount) / Math.log(2);
    }

    /**
     * Classifies an unknown respondent based on the trained decision tree.
     * @param node root node of decision tree
     * @param combat to classify experience of
     * @return the predicted class label of the respondents experience
     */
    public static Object classify(Node node, CombatD combat){
        Object classLabel = findClass(combat, node);
        //System.out.println("Class : " + classLabel);
        return classLabel;
    }

    /**
     * Moves through the decision tree in order to classify the combat as to whether pokemon 1 or pokemon 2 wins
     * @param combat an unknown combatD object
     * @param node the root node of the decision tree
     * @return - predicted class label (first wins or second wins)
     */
    private static CombatClass findClass(CombatD combat, Node node) {
        Object splitAttribute = node.getSplitting_criterion();

        if(splitAttribute != null) {
                Object value = combat.getAttributeValue(splitAttribute);
            for (Node child : node.getChildren()) {
                if (value.equals(child.getValue()) && !node.isLeaf()) {
                    //System.out.println("Attribute : " + splitAttribute + "\nValue : " + value);
                    return findClass(combat, child);
                }
            }
        }
        return node.getClassLabel();
    }


    public static void printTree(Node node, Object attribute) {
        System.out.println(attribute + " is : " + node.getValue());
        if(node.isLeaf()) System.out.println("Leaf node!");
        for(Node child : node.getChildren()) {
            printTree(child, attribute);
        }
    }

    /**
     * Method for printing the data of nodes level by level
     * @param root the root node of the generated decision tree
     * @return a string for printing out
     */
    public static String nodesByLevel(Node root) {
        //System.out.println("Printing decision tree nodes per level");
        StringBuilder sb = new StringBuilder();

        int level = 0;
        List<Node> children = root.getChildren();
        TreeMap<Integer, List<Node>> nodesPerLevel = new TreeMap<>();

        while(!children.isEmpty()) {
            List<Node> newChildren = new ArrayList<>();
            level++;
            for(Node child : children) {
                if(!child.getChildren().isEmpty()) newChildren.addAll(child.getChildren());
                if(nodesPerLevel.containsKey(level)) {
                    nodesPerLevel.get(level).add(child);
                } else {
                    nodesPerLevel.put(level, new ArrayList<>());
                    nodesPerLevel.get(level).add(child);
                }
            }

            children = newChildren;
        }

        for(Integer i : nodesPerLevel.keySet()) {
            sb.append("\n");
            sb.append("*** LEVEL ").append(i).append(" ***\n");
            for(Node n : nodesPerLevel.get(i)) {
                sb.append(n.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Get the data partition with a given attribute value
     * @param root decision tree root
     * @param splitCrit the attribute
     * @param value the value of the attribute
     * @return a list of CombatD objects that have the given attribute value
     */
    public static List<CombatD> getNodePartition(Node root, Object splitCrit, Object value) {
        Node found = findNodeWithAttValue(root, splitCrit, value);

        if(found == null) return new ArrayList<>();
        else return found.getData();

    }

    public static List<Object> getAttributeList(Node root, Object splitCrit, Object value) {
        Node found = findNodeWithAttValue(root, splitCrit, value);

        if(found == null) return new ArrayList<>();
        else return found.getAttributes();
    }

    private static Node findNodeWithAttValue(Node root, Object splitCrit, Object value) {
        List<Node> children = root.getChildren();
        if(!children.isEmpty()) {
            for(Node n : children) {
                if(root.getSplitting_criterion().equals(splitCrit) && n.getValue().equals(value)) return n;
                else findNodeWithAttValue(n, splitCrit, value);
            }
        }
        return null;
    }

    /**
     * Creates the attribute list to be used in generating the decision tree
     * @return a list of attribute classes
     */
    private List<Object> getAttributeList() {
        List<Object> attributeList = new ArrayList<>();

        Object att = dataset.get(0).getAttack().getClass();
        attributeList.add(att);

        Object def = dataset.get(0).getDefence().getClass();
        attributeList.add(def);

        Object hps = dataset.get(0).getHp().getClass();
        attributeList.add(hps);

        Object specAtt = dataset.get(0).getSpecialAttack().getClass();
        attributeList.add(specAtt);

        Object specDefence = dataset.get(0).getSpecialDefence().getClass();
        attributeList.add(specDefence);

        //Object sped = dataset.get(0).getSpeed().getClass();
        //attributeList.add(sped);

        Object legdary = dataset.get(0).getLegendary().getClass();
        attributeList.add(legdary);

        Object stge = dataset.get(0).getStage().getClass();
        attributeList.add(stge);

        Object typ = dataset.get(0).getType().getClass();
        attributeList.add(typ);

        return attributeList;
    }
}