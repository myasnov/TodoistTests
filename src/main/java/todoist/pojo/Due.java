package todoist.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Jacksonized
@Builder
@Getter
public class Due {
    private String date;
    private String string;
    private String lang;
    private boolean is_recurring;
    private Date datetime;
}
