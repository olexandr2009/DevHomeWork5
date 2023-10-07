package org.example.databaseQuery.queryClasses;

import java.sql.Date;
import java.util.Objects;

public class AgeWorker {
    private Type type;
    private String worker_name;
    private Date birthday;

    public AgeWorker(Type type, String worker_name, Date birthday) {
        this.type = type;
        this.worker_name = worker_name;
        this.birthday = birthday;
    }

    public Type getType() {
        return type;
    }

    public String getWorker_name() {
        return worker_name;
    }
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgeWorker ageWorker = (AgeWorker) o;
        return type == ageWorker.type && Objects.equals(worker_name, ageWorker.worker_name) && Objects.equals(birthday, ageWorker.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, worker_name, birthday);
    }

    @Override
    public String toString() {
        return "AgeWorker{" +
                "type=" + type +
                ", worker_name='" + worker_name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public enum Type{
        YOUNGEST,
        OLDEST
    }
}
