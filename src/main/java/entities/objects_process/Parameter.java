package entities.objects_process;

public class Parameter {
    private int id;
    private String name;
    private double value;

    public Parameter() {
    }

    public Parameter(int id) {
        this.id = id;
    }

    public Parameter(String parameter, double value) {
        this.name = parameter;
        this.value = value;
    }

    public Parameter(int id, String parameter, double value) {
        this.id = id;
        this.name = parameter;
        this.value = value;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "id= " + id +
                ", parameter= " + name + '\'' +
                ", value= " + value +
                '}';
    }
}
