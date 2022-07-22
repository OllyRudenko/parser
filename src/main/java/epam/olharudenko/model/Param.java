package epam.olharudenko.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Olha Rudenko
 * @verson 1.0, 21.07.2022
 * Param
 * Class contain params for loading it in Commands
 */
public class Param implements Serializable {
    private static final long serialVersionUID = 4416143620154985057L;

    private String paramName;
    private String filePath;

    public Param() {
    }

    public Param(String paramName, String filePath) {
        this.paramName = paramName;
        this.filePath = filePath;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Param param = (Param) o;
        return Objects.equals(paramName, param.paramName) && Objects.equals(filePath, param.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paramName, filePath);
    }

    @Override
    public String toString() {
        return "Param{" +
                "paramName='" + paramName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
