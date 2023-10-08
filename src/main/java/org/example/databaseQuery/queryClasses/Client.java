package org.example.databaseQuery.queryClasses;

import java.util.Objects;

public class Client {
    private final int client_id;
    private final String name;

    public Client(int client_id, String name) {
        this.client_id = client_id;
        this.name = name;
    }

    public int getClient_id() {
        return client_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return client_id == client.client_id && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id, name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + client_id +
                ", name='" + name + '\'' +
                '}';
    }
}
