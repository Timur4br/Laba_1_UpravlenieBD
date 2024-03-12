package example;

public class Clients {
    private Boolean isDelete;
    private Integer id;
    private String surname;
    private String name;
    private Masters id_masters;

    public Clients(Integer id, String surname, String name, Masters id_masters, Boolean isDelete) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.id_masters = id_masters;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Masters getIdMasters() {
        return id_masters;
    }

    public void setIdMasters(Masters id_masters) {
        this.id_masters = id_masters;
    }

    public boolean isDelete() {
        return isDelete;
    }
    public void setDelete(boolean deleted) {
        isDelete = deleted;
    }
}