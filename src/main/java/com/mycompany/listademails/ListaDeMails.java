package com.mycompany.listademails;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author AlanB
 */
public class ListaDeMails {

    static ArrayList<Mail> mails = new ArrayList();

    public static void main(String[] args) {

        int opcion = 0;

        String mail = "", flag = "", fecha = "", elementos = "";

        do {

            try {
                // Menú
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Menú de opciones\n\n"
                        + "1. Insertar mail y su flag\n"
                        + "2. Insertar elementos de orden\n"
                        + "3. Ordenar mails\n"
                        + "4. Mostrar los mails\n"
                        + "5. Vaciar lista de mails\n"
                        + "6. Salir\n\n"));

                switch (opcion) {
                    case 1:
                        // Le pido al usuario el mail, flag y fecha
                        mail = JOptionPane.showInputDialog(null, "Ingresar mail: \nFormato: MailA");
                        flag = JOptionPane.showInputDialog(null, "Ingresar flag para " + mail + ": \nFormatos:\nA\nA B\nFlags disponibles: A B C");
                        fecha = JOptionPane.showInputDialog(null, "Ingresar fecha de recepción para " + mail + ": \nFormatos: DD/MM/AA");
                        // Guardo los datos proporcionados por el usuario
                        Mail m = new Mail();
                        m.setMail(mail);
                        m.setFlag(flag);
                        m.setFecha(fecha);
                        mails.add(m);
                        break;
                    case 2:
                        // Verifico que no haya elementos de orden almacenados para poder almacenarlos
                        if (elementos.isEmpty()) {
                            // Verifico que la lista de mails no esté vacía
                            if (!mails.isEmpty()) {
                                // Le pido al usuario los elementos de orden para ordenar la lista de mails
                                elementos = JOptionPane.showInputDialog(null, "Ingresar flags para el orden:  \nFormato:  [!]<FLAG>-(FIFO|LIFO)\nEjemplo:  B-LIFO|!C-FIFO|C-LIFO");
                                JOptionPane.showMessageDialog(null, elementos);
                            } else {
                                // Si no hay correos almacenados no se pueden ordenar
                                JOptionPane.showMessageDialog(null, "Debe haber correos para poder ordenarlos.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, elementos);
                        }
                        break;
                    case 3:
                        // Verifico que el cadena con los elementos de orden y la lista de mails no estén vacíos para así poder ordenarlos
                        if (!elementos.isEmpty()) {
                            if (!mails.isEmpty()) {
                                Ordenar(elementos);
                            } else {
                                // Si no hay correos almacenados no se pueden ordenar
                                JOptionPane.showMessageDialog(null, "La lista de mails no se puede ordenar porque está vacía.");
                            }
                        } else {
                            // Si no hay elementos de orden almacenados no se pueden ordenar los mails
                            JOptionPane.showMessageDialog(null, "No hay flags.");
                        }
                        break;
                    case 4:
                        // Muestro los mails
                        MostrarMails();
                        break;
                    case 5:
                        // Vacío la lista de mails
                        mails.removeAll(mails);
                        // Si no hay mails le muestro al usuario que la lista de mails está vacía
                        if (mails.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "La lista de mails está vacía.");
                        }
                        break;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            // Cuando el usuario oprima la tecla "6" finaliza el programa    
        } while (opcion != 6);
    }

    // Método para mostrar los mails
    public static void MostrarMails() {

        String lista = "";

        // Recorro la lista de mails para mostrarsela al usuario
        for (int i = 0; i < mails.size(); i++) {
            lista += mails.get(i);
        }

        // Verifico si la lista de mails está vacía
        if (mails.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista de mails está vacía.");
        } else {
            JOptionPane.showMessageDialog(null, lista);
        }
    }

    // Método para contar la candidad de veces que se repite un flag en los elementos de orden que introdujo el usuario
    public static int Contador(String cadena, String caracter) {

        int posicion, contador = 0;

        posicion = cadena.indexOf(caracter);
        while (posicion != -1) {
            contador++;
            posicion = cadena.indexOf(caracter, posicion + 1);
        }

        return contador;
    }

    // Método para ordenar la lista de mails
    public static void Ordenar(String flags) {

        // Los elementos de orden proporcionados por el usuario se separan por "|" y se almacenan en 3 partes por separado
        String[] parts = flags.split("\\|");
        String parte1 = parts[0];
        String parte2 = parts[1];
        String parte3 = parts[2];

        // Llamo al contador para contar cuantas veces aparecen "A", "B" y "C" en los flags
        int totalA = Contador(flags, "A");
        int totalB = Contador(flags, "B");
        int totalC = Contador(flags, "C");

        // Si alguno de los contador devuelve 0 es porque falta ese flag, osea que hay alguno que se repite y contiene "!", por lo tanto se reemplaza por el flag que falta
        // Esté procedimiento se repite 3 veces para las 3 distintas partes de los elementos de orden
        // Parte 1
        if (totalA == 0) {
            if (parte1.contains("!")) {
                if (parte1.contains("LIFO")) {
                    parte1 = "A-LIFO";
                } else {
                    parte1 = "A-FIFO";
                }
            }
        } else if (totalB == 0) {
            if (parte1.contains("!")) {
                if (parte1.contains("LIFO")) {
                    parte1 = "B-LIFO";
                } else {
                    parte1 = "B-FIFO";
                }
            }
        } else if (totalC == 0) {
            if (parte1.contains("!")) {
                if (parte1.contains("LIFO")) {
                    parte1 = "C-LIFO";
                } else {
                    parte1 = "C-FIFO";
                }
            }
        }

        // Parte 2
        if (totalA == 0) {
            if (parte2.contains("!")) {
                if (parte2.contains("LIFO")) {
                    parte2 = "A-LIFO";
                } else {
                    parte2 = "A-FIFO";
                }
            }
        } else if (totalB == 0) {
            if (parte2.contains("!")) {
                if (parte2.contains("LIFO")) {
                    parte2 = "B-LIFO";
                } else {
                    parte2 = "B-FIFO";
                }
            }
        } else if (totalC == 0) {
            if (parte2.contains("!")) {
                if (parte2.contains("LIFO")) {
                    parte2 = "C-LIFO";
                } else {
                    parte2 = "C-FIFO";
                }
            }
        }

        // Parte 3
        if (totalA == 0) {
            if (parte3.contains("!")) {
                if (parte3.contains("LIFO")) {
                    parte3 = "A-LIFO";
                } else {
                    parte3 = "A-FIFO";
                }
            }
        } else if (totalB == 0) {
            if (parte3.contains("!")) {
                if (parte3.contains("LIFO")) {
                    parte3 = "B-LIFO";
                } else {
                    parte3 = "B-FIFO";
                }
            }
        } else if (totalC == 0) {
            if (parte3.contains("!")) {
                if (parte3.contains("LIFO")) {
                    parte3 = "C-LIFO";
                } else {
                    parte3 = "C-FIFO";
                }
            }
        }

        // Almacenan los mails en 3 partes, cada cual con sus elementos de orden
        Deque<String> p1 = new LinkedList<>();
        Deque<String> p2 = new LinkedList<>();
        Deque<String> p3 = new LinkedList<>();

        // Duplico la lista de mails para al momento de ordenarlos, poder eliminar el que ya se ordenó
        ArrayList<Mail> mailsCopy = new ArrayList(mails);

        int partesPorParte = 1;

        // Utilizo el entero "partesPorParte" para establecer un orden de prioridad
        // "partePorParte" comienza siendo igual a 1 para priorizar que se realize el orden de la primera parte de los elementos de orden proporcionados por el usuario
        // Ejemplo de elemento de orden: B-LIFO|!C-FIFO|C-LIFO
        // B-LIFO = parte 1
        // !C-FIFO = parte 2 (anteriormente este elemento se reemplazo por A-FIFO)
        // C-LIFO = parte 3
        // Parte 1 (Ejemplo: B-LIFO)
        if (partesPorParte == 1) {
            // Verifico si coincidencias en los elementos de orden (si el elemento de orden de la parte 1 no coincide con "A-FIFO", continúa)
            if (parte1.equals("A-FIFO")) {
                // Recorro los mails
                for (int i = 0; i < mailsCopy.size(); i++) {
                    // Verifico que en los mails de "mailsCopy" contenga algún flag "A" para poder continuar,
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        // Agrego los mails respecto el índice a su respectivo Deque (en este caso "p1")
                        p1.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                    }
                }
            } else if (parte1.equals("A-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        p1.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                    }
                }
            }
            if (parte1.equals("B-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        p1.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                    }
                }
            } else if (parte1.equals("B-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        p1.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                    }
                }
            }
            if (parte1.equals("C-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        p1.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                    }
                }
            } else if (parte1.equals("C-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        p1.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                    }
                }
            }
            // Una vez que terminó, suma 1 a "partesPorPartes" para que continúe con la siguiente etapa
            partesPorParte++;
        }

        // Esta sección de código es igual que la anterior
        // Parte 2 (Ejemplo: !C-FIFO = A-FIFO)
        if (partesPorParte == 2) {
            if (parte2.equals("A-FIFO")) {

                // Recorro "p1" para almacenar en "p1Tiene" los mails que ya se agregaron y así no repetir ninguno
                String p1Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail())) {
                            p2.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }

            } else if (parte2.equals("A-LIFO")) {

                String p1Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail())) {
                            p2.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            }
            if (parte2.equals("B-FIFO")) {

                String p1Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail())) {
                            p2.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            } else if (parte2.equals("B-LIFO")) {

                String p1Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail())) {
                            p2.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            }
            if (parte2.equals("C-FIFO")) {

                String p1Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail())) {
                            p2.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            } else if (parte2.equals("C-LIFO")) {

                String p1Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail())) {
                            p2.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            }
            partesPorParte++;
        }

        // Esta sección de código es igual que la anterior
        // Parte 3 (Ejemplo: C-LIFO)
        if (partesPorParte == 3) {
            if (parte3.equals("A-FIFO")) {

                String p1Tiene = "", p2Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }
                for (String index : p2) {
                    p2Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail()) && !p2Tiene.contains(mailsCopy.get(i).getMail())) {
                            p3.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            } else if (parte3.equals("A-LIFO")) {

                String p1Tiene = "", p2Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }
                for (String index : p2) {
                    p2Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail()) && !p2Tiene.contains(mailsCopy.get(i).getMail())) {
                            p3.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }

            }
            if (parte3.equals("B-FIFO")) {

                String p1Tiene = "", p2Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }
                for (String index : p2) {
                    p2Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail()) && !p2Tiene.contains(mailsCopy.get(i).getMail())) {
                            p3.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            } else if (parte3.equals("B-LIFO")) {

                String p1Tiene = "", p2Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }
                for (String index : p2) {
                    p2Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail()) && !p2Tiene.contains(mailsCopy.get(i).getMail())) {
                            p3.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            }
            if (parte3.equals("C-FIFO")) {

                String p1Tiene = "", p2Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }
                for (String index : p2) {
                    p2Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail()) && !p2Tiene.contains(mailsCopy.get(i).getMail())) {
                            p3.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            } else if (parte3.equals("C-LIFO")) {

                String p1Tiene = "", p2Tiene = "";
                for (String index : p1) {
                    p1Tiene += index;
                }
                for (String index : p2) {
                    p2Tiene += index;
                }

                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        if (!p1Tiene.contains(mailsCopy.get(i).getMail()) && !p2Tiene.contains(mailsCopy.get(i).getMail())) {
                            p3.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        }
                    }
                }
            }
        }

        // Imprimo por consola las partes donde se almacenaron los mails por orden
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p3: " + p3);

        String resultadoFinal = "";

        int i = 1;

        // Por orden de prioridad agrego los mails al String "resultadoFinal" para poder mostrarlos todos juntos
        if (i == 1) {
            for (String v : p1) {
                resultadoFinal += v + "\n";
            }
            i++;
        }
        if (i == 2) {
            for (String v : p2) {
                resultadoFinal += v + "\n";
            }
            i++;
        }
        if (i == 3) {
            for (String v : p3) {
                resultadoFinal += v + "\n";
            }
            i++;
        }

        // Muesto el resultado final
        JOptionPane.showMessageDialog(null, resultadoFinal);

    }
}
