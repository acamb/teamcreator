package sanbernardo.acambieri.teamcreator.model;

public class Partecipant implements Item {

    private String name;
    private String surname;
    private Double score;

    public String getName() {
        return name;
    }

    public Partecipant setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Partecipant setSurname(String surname) {
        this.surname = surname;
        return this;
    }
    @Override
    public Double getScore() {
        return score;
    }


    public Partecipant setScore(Double score) {
        this.score = score;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partecipant partecipant = (Partecipant) o;

        if (name != null ? !name.equals(partecipant.name) : partecipant.name != null) return false;
        return surname != null ? surname.equals(partecipant.surname) : partecipant.surname == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Partecipant{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public String getDescription() {
        return surname + " " + name;
    }

    @Override
    public Partecipant clone() {
        Partecipant a = new Partecipant();
        return a.setName(this.getName()).setScore(this.getScore()).setSurname(this.surname);
    }
}
