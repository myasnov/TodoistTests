package todoist.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.List;

@Jacksonized
@Builder
@Getter
public class Task {
	private String id;
	private Object assigner_id;
	private Object assignee_id;
	private String project_id;
	private Object section_id;
	private Object parent_id;
	private Integer order;
	private String content;
	private String description;
	private boolean is_completed;
	private List<Object> labels;
	private Integer priority;
	private Integer comment_count;
	private String creator_id;
	private Date created_at;
	private Due due;
	private String url;
}