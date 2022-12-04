package java.ru.spbstu.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchUtil {

    public static Map<String, List<String>> mapToUrlParams(SearchParameterBox parameterBox) {
        Map<SearchParamNames, List<String>> paramMap = parameterBox.getParameterMap();
        Map<String, List<String>> params = new HashMap<>();
        for (SearchParamNames key : paramMap.keySet()) {
            for (String value : paramMap.get(key)) {
                params.compute(key.getName(), (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(value);
                    return v;
                });
            }
        }
        return params;
    }

}
