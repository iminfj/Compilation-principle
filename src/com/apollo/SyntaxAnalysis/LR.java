package com.apollo.SyntaxAnalysis;

import com.apollo.lexical.LexAttribute;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class LR extends Syntax {

    // LR analysis table
    private static final String[][] Action = {
//              id         +        *         (           )         $
            {   "s5"   ,   ""   ,   ""    ,   "s4"    ,   ""    ,   ""      },
            {   ""     ,   "s6" ,   ""    ,   ""      ,   ""    ,   "acc"   },
            {   ""     ,   "r2" ,   "s7"  ,   ""      ,   "r2"  ,   "r2"    },
            {   ""     ,   "r4" ,   "r4"  ,   ""      ,   "r4"  ,   "r4"    },
            {   "s5"   ,   ""   ,   ""    ,   "s4"    ,   ""    ,   ""      },
            {   ""     ,   "r6" ,   "r6"  ,   ""      ,   "r6"  ,   "r6"    },
            {   "s5"   ,   ""   ,   ""    ,   "s4"    ,   ""    ,   ""      },
            {   "s5"   ,   ""   ,   ""    ,   "s4"    ,   ""    ,   ""      },
            {   ""     ,   "s6" ,   ""    ,   ""      ,   "s11" ,   ""      },
            {   ""     ,   "r1" ,   "s7"  ,   ""      ,   "r1"  ,   "r1"    },
            {   ""     ,   "r3" ,   "r3"  ,   ""      ,   "r3"  ,   "r3"    },
            {   ""     ,   "r5" ,   "r5"  ,   ""      ,   "r5"  ,   "r5"    },
    };
    private static final int[][] Goto = {
//              E       T       F
            {   1,      2,      3   },
            {   -1,     -1,     -1  },
            {   -1,     -1,     -1  },
            {   -1,     -1,     -1  },
            {   8,      2,      3   },
            {   -1,     -1,     -1  },
            {   -1,      9,      3  },
            {   -1,     -1,     10  },
            {   -1,     -1,     -1  },
            {   -1,     -1,     -1  },
            {   -1,     -1,     -1  },
            {   -1,     -1,     -1  },
    };

    private Hashtable<Integer, LexAttribute> lexlist = new Hashtable<Integer, LexAttribute>();
    private final Stack<Integer> stateStack = new Stack<>();
    private final Stack<LexAttribute> tokenStack = new Stack<>();

    public LR () {
        // pass
    }
    public LR(Hashtable<Integer, LexAttribute> lex) {
        this.lexlist = (Hashtable<Integer, LexAttribute>) lex.clone();

        for (int i = 0; i < this.lexlist.size(); i++) {
            if (VtSequence.containsKey(this.lexlist.get(i).value)) {
                this.lexlist.get(i).a = VtSequence.get(this.lexlist.get(i).value);
            } else {
                this.lexlist.get(i).a = VtSequence.get("id");
            }
        }

        for (int i = 0; i < P.size(); i++) {
            if (VnSequence.containsKey(P.get(i).PrdctHead)) {
                P.get(i).a = VnSequence.get(P.get(i).PrdctHead);
            } else {
                print("居然没有这个产生式？？{0}", P.get(i).PrdctHead);
            }
        }

        stateStack.push(0);
        tokenStack.push(this.lexlist.get(0));

        analysis();
    }

    private void analysis() {
        int i = 0;
        while (true) {
            LexAttribute nextToken = this.lexlist.get(++i);
            if (Action[stateStack.peek()][tokenStack.peek().a].trim() == null || Action[stateStack.peek()][tokenStack.peek().a].isEmpty()) {
                print("[{date}] Cannot analyze empty entries Action[{0}][{1}] * {2}", stateStack.peek(), tokenStack.peek().a, tokenStack.peek().get);
                break;
            }

            if (Action[stateStack.peek()][tokenStack.peek().a].charAt(0) == 's') {
                stateStack.push(Integer.parseInt(Action[stateStack.peek()][tokenStack.peek().a].split("s")[1]));
                tokenStack.push(nextToken);
                print("sss");
            } else if (Action[stateStack.peek()][tokenStack.peek().a].charAt(0) == 'r') {
                int rj = Integer.parseInt(Action[stateStack.peek()][tokenStack.peek().a].split("r")[1]);
                Production beta = P.get(rj);
                for (int i1 = 0; i1 < beta.bodysize; i1++) {
                    stateStack.pop();
                    tokenStack.pop();
                }
                stateStack.push(Goto[rj][P.get(rj).a]);
                print("{0}", P.get(rj).Prdct);
            } else if (Action[stateStack.peek()][tokenStack.peek().a] == "acc") break; /* 语法分析完成 */
            else {
                // TODO 调用错误恢复例程
                print("error!");
                break;
            }
        }
    }

}
