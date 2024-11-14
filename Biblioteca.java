import javax.swing.JOptionPane;

public class Biblioteca {

    public static void main(String[] args) {
        int opcion = 0;
        ABCUsuarios abcUsuarios = new ABCUsuarios();
        ABCLibros abcLibros = new ABCLibros();
        ABCPrestamos abcPrestamos = new ABCPrestamos();

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(mostrarMenu()));

                switch (opcion) {
                    case 1:
                        abcUsuarios.registrarUsuario();
                        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                        break;
                    case 2:
                        abcLibros.registrarLibro();
                        JOptionPane.showMessageDialog(null, "Libro registrado exitosamente.");
                        break;
                    case 3:
                        abcPrestamos.registrarPrestamos(abcUsuarios.getListaDeUsuarios(), abcLibros.getListaDeLibros());
                        abcPrestamos.mostrarPrestamos();
                        break;
                    case 4:
                        abcPrestamos.devolverPrestamo();
                        abcPrestamos.mostrarPrestamos();
                        break;
                    case 5:
                        String listaUsuarios = abcUsuarios.mostrarUsuarios();
                        JOptionPane.showMessageDialog(null, listaUsuarios);
                        break;
                    case 6:
                        String listaLibros = abcLibros.mostrarLibros();
                        JOptionPane.showMessageDialog(null, listaLibros);
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Gracias, agradecemos su preferencia.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: por favor ingrese un número válido.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } while (opcion != 7);
    }

    private static String mostrarMenu() {
        return "Menú de Biblioteca:\n" +
                "1 - Alta de Usuario\n" +
                "2 - Alta de Libro\n" +
                "3 - Realizar un Préstamo\n" +
                "4 - Realizar una Devolución\n" +
                "5 - Consultar Usuarios Registrados\n" +
                "6 - Consultar Libros registrados \n"+
                "7 - Salir\n\n" +
                "Seleccione una opción:";
    }
}