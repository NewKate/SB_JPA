package vtb.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperaDto {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String label;
    @JsonProperty
    private String description;
    @JsonProperty
    private Integer age;
    @JsonProperty
    private String duration;
    @JsonProperty
    private Integer intermission;
    @JsonProperty
    private String type;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getIntermission() {
        return intermission;
    }

    public void setIntermission(Integer intermission) {
        this.intermission = intermission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

