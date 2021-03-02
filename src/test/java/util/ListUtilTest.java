package util;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUtilTest {

    @Test
    public void isEmptyTest() {
        List<Integer> list = null;
        Assert.assertTrue(ListUtil.isEmpty(list));
        list = new ArrayList<>();
        Assert.assertTrue(ListUtil.isEmpty(list));
        list.add(1);
        Assert.assertFalse(ListUtil.isEmpty(list));
    }


}
