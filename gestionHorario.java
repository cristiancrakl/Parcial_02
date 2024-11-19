import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Materia {

    public String nombre;
    public String codigo;
    public int creditos;
    public List<String> horariosDisponibles;

    public Materia(String nombre, String codigo, int creditos) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.horariosDisponibles = new ArrayList<>();
    }

    public void agregarHorario(String horario) {
        horariosDisponibles.add(horario);
    }

    public void mostrarHorariosDisponibles() {
        System.out.println("Horarios disponibles para " + nombre + ":");
        for (String horario : horariosDisponibles) {
            System.out.println(horario);
        }
    }

    public List<String> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }
}

class Estudiante {
    public String nombre;
    public String id;
    public Map<Materia, String> materiasInscritas;

    public Estudiante(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.materiasInscritas = new HashMap<>();
    }

    public void inscribirMateria(Materia materia, String horario) throws Exception {
        if (materiasInscritas.containsValue(horario)) {
            throw new Exception("Conflicto: Ya tienes una materia inscrita en este horario.");
        }
        materiasInscritas.put(materia, horario);
        System.out.println("Materia " + materia.getNombre() + " inscrita en horario " + horario);
    }

    // para mostar las materias inscritas con un for
    public void mostrarHorarioCompleto() {
        System.out.println("Horario completo de " + nombre + ":");
        for (Map.Entry<Materia, String> entry : materiasInscritas.entrySet()) {
            System.out.println("Materia: " + entry.getKey().getNombre() + ", Horario: " + entry.getValue());
        }
    }
}

class GestionHorarios {
    public List<Materia> materias;
    public List<Estudiante> estudiantes;

    public GestionHorarios() {
        materias = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }

    public void registrarMateria(Materia materia) {
        materias.add(materia);
    }

    public void inscribirEstudiante(Estudiante estudiante, Materia materia, String horario) {
        try {
            estudiante.inscribirMateria(materia, horario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMaterias() {
        System.out.println("Materias registradas:");
        for (Materia materia : materias) {
            System.out.println("Nombre: " + materia.getNombre() + ", Código: " + materia.getCodigo());
            materia.mostrarHorariosDisponibles();
        }
    }
}

public class gestionHorario {
    public static void main(String[] args) {
        // crear la gestión de horarios
        GestionHorarios gestion = new GestionHorarios();

        // crear materias
        Materia matematicas = new Materia("Matemáticas", "MAT101", 3);
        matematicas.agregarHorario("Lunes 10:00-12:00");
        matematicas.agregarHorario("Miércoles 10:00-12:00");

        Materia fisica = new Materia("Física", "FIS101", 4);
        fisica.agregarHorario("Lunes 12:00-14:00");
        fisica.agregarHorario("Miércoles 12:00-14:00");

        // registrar materias en la gestión
        gestion.registrarMateria(matematicas);
        gestion.registrarMateria(fisica);

        // mostrar materias registradas
        gestion.mostrarMaterias();

        // crear estudiantes
        Estudiante estudiante1 = new Estudiante("Juan Pérez", "12345");
        Estudiante estudiante2 = new Estudiante("María López", "67890");

        // inscribir estudiantes
        gestion.inscribirEstudiante(estudiante1, matematicas, "Lunes 10:00-12:00"); // Inscripción exitosa
        gestion.inscribirEstudiante(estudiante1, fisica, "Lunes 12:00-14:00"); // Inscripción exitosa
        gestion.inscribirEstudiante(estudiante1, fisica, "Lunes 10:00-12:00"); // Conflicto

        // mostrar horario completo del estudiante
        estudiante1.mostrarHorarioCompleto();
        estudiante2.mostrarHorarioCompleto();
    }
}