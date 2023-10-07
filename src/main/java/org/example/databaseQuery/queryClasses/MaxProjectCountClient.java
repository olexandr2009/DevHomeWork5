package org.example.databaseQuery.queryClasses;

import java.util.Objects;

public class MaxProjectCountClient {
        private String name;
        private int projectCount;

    public String getName() {
        return name;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    public void setName(String name) {
        this.name = name;
    }
    public MaxProjectCountClient(String name,int projectCount){
        this.name = name;
        this.projectCount = projectCount;
    }

    @Override
    public String toString() {
        return "MaxProjectCountClient{" +
                "name='" + name + '\'' +
                ", projectCount=" + projectCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaxProjectCountClient that = (MaxProjectCountClient) o;
        return projectCount == that.projectCount && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, projectCount);
    }
}
