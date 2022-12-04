package java.ru.spbstu.search;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString(of = "params")
public final class SearchParameterBox {
    private final Map<SearchParamNames, List<String>> params = new HashMap<>();

    public SearchParameterBox() {
    }

    public SearchParameterBox(SearchParamNames name, String value) throws SearchException {
        addParameter(name, value);
    }

    public void addParameter(SearchParamNames name, String value) throws SearchException {
        if (!params.containsKey(name)) {
            params.put(name, new ArrayList<>(List.of(value)));
        } else {
            if (name.acceptMultipleVales()) {
                List<String> values = params.get(name);
                values.add(value);
            } else {
                throw new SearchException("Failed to add parameter with name and value: ",
                        name.getName(), value, params.get(name).get(0));
            }
        }
    }

    public void setParameter(SearchParamNames name, String value) throws SearchException {
        params.remove(name);
        addParameter(name, value);
    }

    public void addParameter(SearchParameterBox parameterBox) throws SearchException {
        Map<SearchParamNames, List<String>> paramsMap = parameterBox.getParameterMap();
        for (SearchParamNames name : paramsMap.keySet()) {
            for (String value : paramsMap.get(name)) {
                addParameter(name, value);
            }
        }
    }

    public SearchParameterBox addParameter(ISearchParam searchParam) throws SearchException {
        addParameter(searchParam.getSearchParameters());
        return this;
    }

    public Map<SearchParamNames, List<String>> getParameterMap() {
        return params;
    }
}
