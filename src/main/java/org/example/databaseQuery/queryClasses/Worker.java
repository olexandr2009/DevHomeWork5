package org.example.databaseQuery.queryClasses;

import java.sql.Date;
import java.util.Objects;

public class Worker {

    private int worker_id;
    private Date birthday;
    private Level level;
    private int salary;
    private String worker_name;

    public Worker(int worker_id, Date birthday, Level level, int salary, String worker_name) {
        this.worker_id = worker_id;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
        this.worker_name = worker_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return worker_id == worker.worker_id && salary == worker.salary && Objects.equals(birthday, worker.birthday) && level == worker.level && Objects.equals(worker_name, worker.worker_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worker_id, birthday, level, salary, worker_name);
    }

    public int getWorker_id() {
        return worker_id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getWorker_name() {
        return worker_name;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "worker_id=" + worker_id +
                ", birthday=" + birthday +
                ", level=" + level +
                ", salary=" + salary +
                ", worker_name='" + worker_name + '\'' +
                '}';
    }

    public enum Level{
        Trainee,
        Junior,
        Middle,
        Senior
    }
}
