package com.example.headline.bean;

import java.util.ArrayList;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class XFBean {
    public ArrayList<WS> ws;
    public class WS{
        public ArrayList<CW> cw;
    }
    public class CW{
        public String w;
    }
}
