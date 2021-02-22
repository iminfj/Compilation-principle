package com.apollo.SyntaxAnalysis;

import java.util.ArrayList;
import java.util.Objects;

public class ItemSet {
    public ArrayList<Produce> production = new ArrayList<Produce>();

    public int onlyhash() {
        String tmp = "";
        for (int i = 0; i < production.size(); i++) {
            tmp += get(i).Head;
        }
        return tmp.hashCode();
    }

    public ItemSet() {
        // default
    }
    public ItemSet(String s) {
        production.add(new Produce(s));
    }
    public ItemSet(String[] s) {
        for (String e : s) {
            production.add(new Produce(e));
        }
    }

    public int size() {
        return production.size();
    }

    public Produce get(int index) {
        return production.get(index);
    }

    public boolean add(Produce p) {
        return production.add(p);
    }
    public boolean add(String p) {
        return production.add(new Produce(p));
    }

    public ItemSet containsXandPointInLeft(String X) {
        ItemSet ContainsX = new ItemSet();
        for (Produce produce : production) {
            boolean is = false;
            String[] sp = produce.Body.split(" ");
            for (String e : sp) {
                if (Objects.equals(e, X)) {
                    is = true;
                    break;
                }
            }
            if (is) {
                if (produce.PointExsit) {
                    boolean point = false, x = false;
                    boolean CanMoveIn = false;
                    String[] tempBodySplit = produce.Body.split(" ");
                    for (int i = 0; i < tempBodySplit.length; i++) {
                        if (Objects.equals(tempBodySplit[i], "·")) {
                            point = true;
                            if (tempBodySplit.length - i > 1) {
                                if (Objects.equals(tempBodySplit[i + 1], X)) {
                                    if (x) {
                                        CanMoveIn = false;
                                        produce.PointInTargetXY = 2;
                                    } else {
                                        CanMoveIn = true;
                                        produce.PointInTargetXY = 1;
                                    }
                                } else {
                                    CanMoveIn = false;
                                    break;
                                }
                            }
                        } else if (Objects.equals(tempBodySplit[i], X)) {
                            x = true;
                            if (point) {
                                CanMoveIn = true;
                                produce.PointInTargetXY = 1;
                            } else {
                                CanMoveIn = false;
                                produce.PointInTargetXY = 2;
                            }
                        }
                        if (!CanMoveIn && produce.PointInTargetXY == 2) {
                            break;
                        }
                    }
                    if (CanMoveIn && produce.PointInTargetXY == 1) {
                        ContainsX.add(produce);
                    }
                }
            }
        }
        return ContainsX;
    }
}
