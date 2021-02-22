package com.apollo.SyntaxAnalysis;

import java.util.Objects;

public class Produce {

    // Production head (Head) and production body (Body)
    public String Head, Body;
    // Set to true if it is Vn, set false if it is Vt
    public boolean MoveIn = false;
    // Grammar symbol after dot
    public String AfterSymbol = "";
    // Production
    public String Production = "";
    // Whether the following symbol is determined
    public boolean FollowedByOK = false;

    // PointInTargetXY 1: left, 2: right
    public int PointLocation, PointInTargetXY;

    public boolean PointExsit = false;

    public Produce(String s) {
        this.Production = s;
        String[] sp = s.split("->");
        Head = sp[0].trim();
        Body = sp[1].trim();
        String[] BodySplit = Body.split(" ");
        for (String c : BodySplit) {
            if (Objects.equals(c, "·")) {
                PointExsit = true;
                break;
            }
        }
        a(Body);
    }

    void a(String s) {
        String[] sp = s.split(" ");
        boolean have = false;
        for (int i = 0; i < sp.length; i++) {
            if (Objects.equals(sp[i],"·")) {
                PointLocation = i;
                if (sp.length - i == 1) {
                    have = true;
                    MoveIn = false;
                } else {
                    have = true;
                }
            } else if (new lowlevelcache().isVn(sp[i])) {
                if (FollowedByOK) {
                    break;
                } else if (have) {
                    AfterSymbol = sp[i];
                    MoveIn = FollowedByOK = true;
                } else MoveIn = false;
            } else if (new lowlevelcache().isVt(sp[i])) {
                if (have) {
                    if (Objects.equals(sp[i-1], "·")) {
                        MoveIn = false;
                        AfterSymbol = null;
                        FollowedByOK = true;
                        break;
                    }
                }
            }
        }

    }
}
