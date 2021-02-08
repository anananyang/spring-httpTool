package util;

import java.util.List;

public abstract class ListUtil {

    public static Boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static Boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

}
