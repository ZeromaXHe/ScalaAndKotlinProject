package com.zerox.chapter2;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:01
 */
public interface View {
    String render(Map<String, List<String>> model);
}
