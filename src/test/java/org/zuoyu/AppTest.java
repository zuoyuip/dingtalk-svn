package org.zuoyu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private static final String LINE = "\\n";

    private static final String SPACE = "\\t";

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testString(){
        String commit = "\n1.测\t试1\n2.测\t试2\t\n3.测\t试3";
        StringBuilder content = new StringBuilder();
        for (String line : commit.split(LINE)) {
            content.append(line).append("\n");
        }
       String s = "> - #### **提交说明**：" + content.toString().replaceAll(SPACE, " ");
        System.out.println(s);
        System.out.println(SPACE);
    }
}
