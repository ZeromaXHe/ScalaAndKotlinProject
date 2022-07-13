package com.zerox.chapter2;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:02
 */
public interface RenderingStrategy {
    String renderView(Map<String, List<String>> model);
}
