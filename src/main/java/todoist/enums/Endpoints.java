package todoist.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import todoist.utils.PropertyUtils;

@AllArgsConstructor
@Getter
public enum Endpoints {
    BASE_URL(PropertyUtils.getValue("baseUrl")),
    BASE_PATH(PropertyUtils.getValue("basePath")),
    DEPRECATED_API_VERSION_PATH(PropertyUtils.getValue("deprecatedBasePath")),
    NON_EXISTENT_TASK("/64830457622"),
    INVALID_ID("/64830457");

    private final String path;
}
