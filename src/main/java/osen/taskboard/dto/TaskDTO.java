package osen.taskboard.dto;

import lombok.Data;

@Data
public class TaskDTO {
    private Long id;
    private String header;
    private String description;
    private String parent;
    private String uuid;
}
