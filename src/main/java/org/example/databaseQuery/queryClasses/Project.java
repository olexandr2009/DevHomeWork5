package org.example.databaseQuery.queryClasses;

import java.util.Date;
import java.util.Objects;

public class Project {
    private int project_id;
    private int client_id;
    private Date start_date;
    private Date finish_date;

    public Project(int project_id, int client_id, Date start_date, Date finish_date) {
        this.project_id = project_id;
        this.client_id = client_id;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public int getProject_id() {
        return project_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return project_id == project.project_id && client_id == project.client_id && Objects.equals(start_date, project.start_date) && Objects.equals(finish_date, project.finish_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project_id, client_id, start_date, finish_date);
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", client_id=" + client_id +
                ", start_date=" + start_date +
                ", finish_date=" + finish_date +
                '}';
    }
}
