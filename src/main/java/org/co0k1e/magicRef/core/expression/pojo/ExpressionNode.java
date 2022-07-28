package org.co0k1e.magicRef.core.expression.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


/**
 * 表达式节点
 * @author co0k1e
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionNode {



    private ExpressionNode next;

}
