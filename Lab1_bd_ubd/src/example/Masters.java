package example;

public class Masters {
    private Boolean isDelete;
    private Integer id;
    private String name;
    private String surname;

    public Masters(Integer id, String surname, String name, Boolean isDelete) {
        this.id = id;
        this.surname = surname;
        this.name = name;
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

    public boolean isDelete() {
        return isDelete;
    }
    public void setDelete(boolean deleted) {
        isDelete = deleted;
    }
}
